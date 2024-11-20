package ss.user.domain.co.changepassword;

// 验证码上下文
public record CodeContext(
        String code,
        String type,
        String operation,
        String to
) {
    /**
     * 自检逻辑
     *
     * @param code      验证码
     * @param type      类型
     * @param operation 关联操作
     * @param to 收码人
     */
    public CodeContext {

    }

}
