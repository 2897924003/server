package ss.user.domain.service;
import ss.user.domain.User;

public interface UserBusiness{

    void changePassword(User user, String newPassword);
    void changeEmail(User user, String newEmail);
    void changePhoneNumber(User user, String newPhoneNumber);
    void enableTwoFactorAuthentication(User user);
    void disableTwoFactorAuthentication(User user);
}
