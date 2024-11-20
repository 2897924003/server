package ss.user.usecase;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ss.user.atcl.UserConverter;
import ss.user.usecase.exception.InvalidVerificationCodeException;
import ss.user.usecase.exception.UserNotFoundException;
import ss.user.domain.co.AccountChangeCO;
import ss.user.domain.co.changepassword.ChangePasswordCO;
import ss.user.domain.User;
import ss.user.domain.service.UserBusiness;
import ss.user.infrastructure.persistance.UserRepository;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;

@Service
public class SecurityAccountSettingBusinessCase {

    private static final Logger logger = LoggerFactory.getLogger(SecurityAccountSettingBusinessCase.class);

    private final UserRepository userRepository;
    private final RabbitTemplate rabbitTemplate;
    private final UserBusiness userBusiness;

    public SecurityAccountSettingBusinessCase(UserRepository userRepository, RabbitTemplate rabbitTemplate, UserBusiness userBusiness) {
        this.userRepository = userRepository;
        this.rabbitTemplate = rabbitTemplate;
        this.userBusiness = userBusiness;
    }

    /**
     * 修改密码
     * 支持：1. 手机验证 2. 邮箱验证
     */
    @Transactional
    public void changePassword(ChangePasswordCO co) {
        // 1. 校验验证码
        if (!isCodeValid(co)) {
            logger.error("验证码验证失败: {}", co.codeContext());
            throw new InvalidVerificationCodeException("验证码无效或过期");
        }

        // 2. 获取用户信息
        User user = getUserByContext(co);

        // 3. 修改密码
        userBusiness.changePassword(user, co.changePasswordContext().newPassword());

        // 4. 持久化更新用户
        userRepository.save(user);
        logger.info("密码修改成功，用户: {}", user.getUsername());
    }

    /**
     * 调用消息中间件远程校验验证码
     * @param co
     * @return
     */
    private boolean isCodeValid(ChangePasswordCO co) {
        byte[] verifiedResult = (byte[]) rabbitTemplate.convertSendAndReceive("code", "verify.code", co.codeContext());
        return verifiedResult != null && Boolean.parseBoolean(new String(verifiedResult, StandardCharsets.UTF_8));
    }

    private User getUserByContext(ChangePasswordCO co) {
        User user = co.codeContext().type().equals("Email")
                ? userRepository.findByEmail(co.changePasswordContext().username())
                : userRepository.findByUsername(co.changePasswordContext().username());

        if (user == null) {
            throw new UserNotFoundException("用户不存在: " + co.changePasswordContext().username());
        }
        return user;
    }

    /**
     * 发送验证码
     * @param co 命令对象
     */
    public void sendVerificationCode(ChangePasswordCO co) {
        try {
            rabbitTemplate.convertAndSend("code", "send.code", co.codeContext());
        } catch (Exception e) {
            logger.error("验证码发送失败: {}", co.codeContext(), e);
            throw new RuntimeException("验证码发送失败");
        }
    }

    /**
     * 修改邮箱
     */
    @Transactional
    public void changeEmail(AccountChangeCO co) {

    }

    /**
     * 修改手机号
     */
    @Transactional
    public void changePhoneNumber(User user, String newPhoneNumber) {

        userRepository.save(user);
        logger.info("手机号修改成功，用户: {}, 新手机号: {}", user.getUsername(), newPhoneNumber);
    }

    // 其他安全相关设置的用例
    /**
     * 启用二次认证
     */
    @Transactional
    public void enableTwoFactorAuthentication(User user) {

        userRepository.save(user);
        logger.info("二次认证已启用，用户: {}", user.getUsername());
    }

    /**
     * 禁用二次认证
     */
    @Transactional
    public void disableTwoFactorAuthentication(User user) {

        userRepository.save(user);
        logger.info("二次认证已禁用，用户: {}", user.getUsername());
    }

    @Transactional
    public void createUser(User user) {
        //判断用户名是否已存在
        // 创建 ExampleMatcher，指定对 username 进行精确匹配
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.exact());
        if (userRepository.exists(Example.of(user,matcher))){
            return;
        }
        userBusiness.changePassword(user, user.getPassword());
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .password(user.getPassword())
                .username(user.getUsername())
                .build();

        userRepository.save(UserConverter.userDetailsToUser(userDetails));


    }
}
