package ss.springsecurity.ohs;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ss.springsecurity.utils.JwtUtils;

@RestController
public class AuthorizeServiceController {

    @PostMapping("/authorize")
    public String authorizeService(@RequestBody String accessToken) {
        DecodedJWT decodedJWT = JwtUtils.verifyToken(accessToken);
        System.out.println(decodedJWT.toString());
        System.out.println(decodedJWT.getToken());
        return "true";
    }

    @PostMapping("/authorize/actorid")
    public String bauthorizeService(@RequestBody String accessToken) {
        DecodedJWT decodedJWT = JwtUtils.verifyToken(accessToken);
        String userId = decodedJWT.getClaim("userId").toString();
        System.out.println(userId);
        return userId;
    }


}
