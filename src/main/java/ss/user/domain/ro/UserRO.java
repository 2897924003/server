package ss.user.domain.ro;

public record UserRO(
        Long id,
        String username,
        String password,
        int authorities,
        boolean accountNonExpired,
        boolean accountNonLocked,
        boolean enabled
) {
    // 可以添加额外的方法，如果需要的话
}
