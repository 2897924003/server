package ss.user.domain.event;

import java.text.MessageFormat;
import java.time.LocalDateTime;

/**
 * user-领域事件-修改密码
 */
public class PasswordChangedEvent {
    public final String message;
    public final String email;
    public final String phone;
    public final LocalDateTime timestamp = LocalDateTime.now();
    public PasswordChangedEvent(String email,String phone) {
        this.message = MessageFormat.format("{0} {1} {2}", "您的账户于", timestamp, "执行了修改密码操作，如果不是本人操作，请尽快冻结账户，避免造成损失。");
        this.email = email;
        this.phone = phone;
    }


}
