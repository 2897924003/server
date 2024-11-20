package ss.user.domain;


/**
 * 权限映射
 */
public enum AuthRoleEnum {
    ROLE_ANONYMOUS(1),
    ROLE_STUDENT(1<<1),
    ROLE_TEACHER(1<<2),
    ROLE_ADMIN(1<<3),
    ;

    private final int roleBit;

    AuthRoleEnum(int authBit) {
        this.roleBit = authBit;
    }

    public int getRoleBit() {
        return roleBit;
    }

}
