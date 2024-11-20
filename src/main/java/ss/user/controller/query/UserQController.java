package ss.user.controller.query;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ss.user.domain.ro.UserRO;
import ss.user.service.UserQueryService;



@RestController
public class UserQController {

    private final UserQueryService queryService;

    public UserQController(UserQueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * 根据用户名查询用户个人信息
     * @param username
     * @return
     */
    @GetMapping("/user/{username}")
    public ResponseEntity<UserRO> getUser(@PathVariable("username") String username) {
        UserRO user = queryService.findByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

/**
 * 查询所有用户信息
 * @return
 */
    @GetMapping("/users")
    public ResponseEntity<Page<UserRO>> getUsersWithoutVirtualThread() {
        Page<UserRO> resultWithPageInfo = queryService.findAllUsers(PageRequest.of(0, 10));
        if (resultWithPageInfo.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(resultWithPageInfo);
    }

}
