package ss.user.controller.query;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ss.user.domain.UserSummary;
import ss.user.service.UserSummaryQueryService;

@RestController
public class UserSummaryController {
    private final UserSummaryQueryService userSummaryQueryService;

    public UserSummaryController(UserSummaryQueryService userSummaryQueryService) {
        this.userSummaryQueryService = userSummaryQueryService;
    }

    /**
     * 查询用户概览信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserSummary> query(@PathVariable("id") long id ){
        UserSummary userSummary = userSummaryQueryService.findUserSummaryById(id);
        return ResponseEntity.ok(userSummary);
    }
}
