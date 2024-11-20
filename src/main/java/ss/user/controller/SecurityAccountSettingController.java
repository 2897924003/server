package ss.user.controller;




import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ss.user.domain.User;
import ss.user.domain.co.changepassword.ChangePasswordCO;
import ss.user.usecase.SecurityAccountSettingBusinessCase;


@RestController
@RequestMapping("/users")
public class SecurityAccountSettingController {

    private final SecurityAccountSettingBusinessCase settingBusinessCase;

    public SecurityAccountSettingController(SecurityAccountSettingBusinessCase settingBusinessCase) {
        this.settingBusinessCase = settingBusinessCase;
    }


    /*------------------------修改密码业务入口-------------------------------*/

    /**
     * 修改密码
     * @param command 命令对象
     * @return null
     */
    @PostMapping("/change_password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordCO command) {
        settingBusinessCase.changePassword(command);
        return ResponseEntity.ok().build();
    }

    /**
     * 发送验证码
     * @param command 命令对象
     */
    @PostMapping("/change_password/code")
    public void sendChangePasswordCode(@RequestBody ChangePasswordCO command) {
        settingBusinessCase.sendVerificationCode(command);
    }
    /*------------------------------------------------------------------------*/

    @PostMapping("/register")
    public void createUser(@RequestBody User user){
        settingBusinessCase.createUser(user);
    }


}
