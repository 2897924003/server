package ss.user.domain.co.changepassword;

// 修改密码上下文
public record ChangePasswordContext(
        String username,
        String newPassword
) {
    /**
     * 自检逻辑
     *
     * @param username   要修改的用户
     * @param newPassword 新密码
     */
    public ChangePasswordContext {

    }
}
