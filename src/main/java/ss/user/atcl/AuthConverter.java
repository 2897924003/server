package ss.user.atcl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import ss.user.domain.AuthRoleEnum;

import java.util.ArrayList;
import java.util.Collection;

public class AuthConverter {


    /**
     * 将位值与对应角色组合存入 roles 集合
     * @param authBit 输入的权限位
     * @return 返回包含所有匹配角色的集合
     */
    public static Collection<? extends GrantedAuthority> mapBitToRole(int authBit) {
        Collection<GrantedAuthority> roles = new ArrayList<>();

        // 遍历枚举，检查每个角色的位是否与 authBit 匹配
        for (AuthRoleEnum role : AuthRoleEnum.values()) {
            if ((authBit & role.getRoleBit()) != 0) {
                roles.add(new SimpleGrantedAuthority(role.name()));
            }
        }

        return roles;
    }
    /**
     * 将角色集合转换为对应的权限位
     * @param roles 输入的角色集合
     * @return 返回权限位的整数表示
     */
    public static int mapRoleToBit(Collection<? extends GrantedAuthority> roles) {
        int authBit = 0;

        for (GrantedAuthority role : roles) {
            try {
                // 获取角色对应的枚举
                AuthRoleEnum authRole = AuthRoleEnum.valueOf(role.getAuthority());
                // 按位或操作，将该角色的位值加入 authBit
                authBit |= authRole.getRoleBit();
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid role: " + role.getAuthority());
            }
        }

        return authBit;
    }

}
