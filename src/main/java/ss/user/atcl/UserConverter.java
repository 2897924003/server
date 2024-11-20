package ss.user.atcl;

import org.springframework.security.core.userdetails.UserDetails;
import ss.user.domain.User;
import ss.user.domain.ro.UserRO;

public class UserConverter {

    /**
     * User -> UserRO
     */
    public static UserRO userToUserRO(User user) {

        return new UserRO(
                user.getId(),
                user.getUsername(),
                null,
                user.getAuthorities(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isEnabled()
        );
    }

    /**
     * UserDetails -> User
     */
    public static User userDetailsToUser(UserDetails userDetails){
        return new User(
                null,
                userDetails.getUsername(),
                userDetails.getPassword(),
                AuthConverter.mapRoleToBit(userDetails.getAuthorities()),
                userDetails.isAccountNonExpired(),
                userDetails.isAccountNonExpired(),
                userDetails.isEnabled()
        );
    }

    /**
     * User -> UserDetails
     */
    public static UserDetails userToUserDetails(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.isEnabled(),
                user.isAccountNonExpired(),  // Account非过期
                user.isEnabled(),
                user.isAccountNonLocked(),
                AuthConverter.mapBitToRole(user.getAuthorities())
        );
    }

}
