package ss.user.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ss.user.domain.ro.UserRO;


public interface UserQueryService {
    UserRO findByUsername(String username);
    Page<UserRO> findAllUsers(Pageable pageable);

}
