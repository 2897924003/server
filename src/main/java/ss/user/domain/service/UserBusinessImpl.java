package ss.user.domain.service;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ss.user.domain.User;

/**
 * 用户领域服务
 */
@Service
public class UserBusinessImpl implements UserBusiness {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public void changePassword(User user, String rawNewPassword) {
        user.setPassword(passwordEncoder.encode(rawNewPassword));
    }

    @Override
    public void changeEmail(User user, String newEmail) {
        user.setEmail(newEmail);
    }

    @Override
    public void changePhoneNumber(User user, String newPhoneNumber) {

    }

    @Override
    public void enableTwoFactorAuthentication(User user) {

    }

    @Override
    public void disableTwoFactorAuthentication(User user) {

    }

}
