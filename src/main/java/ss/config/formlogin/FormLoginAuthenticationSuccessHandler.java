package ss.config.formlogin;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class FormLoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        // 设置返回的响应状态码
        response.setStatus(HttpServletResponse.SC_OK);
        // 设置响应的内容类型为JSON
        response.setContentType("application/json;charset=UTF-8");
        // 根据用户的目标系统生成访问令牌
        String system = request.getHeader("system");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        String name = authentication.getName();
        //String jwt = JwtUtils.generateToken(system, name, authorities);
        //response.setHeader("Authorization", "Bearer " + jwt);
        // 获取认证的用户信息
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // 准备要返回的数据
        Map<String, Object> data = new HashMap<>();
        data.put("status", 200);
        data.put("message", "AuthenticationSuccess");
        data.put("username", userDetails.getUsername());
        data.put("roles", userDetails.getAuthorities());
        // 将数据转换为JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonResponse = objectMapper.writeValueAsString(data);
        // 返回JSON数据
        response.getWriter().write(jsonResponse);
        //执行Oauth2认证成功的重定向操作
        if (authentication.getAuthorities().toString().contains("OAUTH2_USER")){
            response.sendRedirect("https://test.opensun.asia/?email=2897924003@qq.com#/Oauth2WaitingPage");
        }

        clearAuthenticationAttributes(request);
    }
    protected final void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
