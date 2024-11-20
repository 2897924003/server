package ss.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import ss.user.atcl.UserConverter;
import ss.user.domain.User;
import ss.user.infrastructure.persistance.UserRepository;

/**
 * 应用服务
 */
@Component
public class DBUserDetailsManager implements UserDetailsService, UserDetailsPasswordService {
    private final UserRepository userRepository;
    public DBUserDetailsManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 升级密码编码，密码编码迁移时被调用。
     * 实际结果也是修改密码
     *
     * @param user        数据库中用户数据
     * @param newPassword 新编码密码
     */
    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        //UserConverter.userToUserDetails(
        //                userRepository.updatePasswordByUsername(user.getUsername(), newPassword));
        return null;
    }

     /**
     * 将数据库的用户加载到内存中
     * @param username 用户名（手机号）
     */

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        //将用户id存入请求容器中
        RequestContextHolder.currentRequestAttributes().setAttribute("userId",user.getId(), RequestAttributes.SCOPE_REQUEST);
        return UserConverter.userToUserDetails(user);
    }

}
