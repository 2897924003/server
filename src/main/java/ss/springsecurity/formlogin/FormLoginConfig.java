package ss.springsecurity.formlogin;


import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ss.springsecurity.utils.SecurityConfigUtil;


public class FormLoginConfig implements SecurityConfigUtil {

    /**
     * 实施表单登录过滤器建造策略
     * @param http 待建造的过滤器链的建造者
     */
    @Override
    public void customize(HttpSecurity http) {

        try {
            http.formLogin(config -> config
                            .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                            .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                            .loginProcessingUrl("/login")
                            .successHandler(new FormLoginAuthenticationSuccessHandler())
                            .failureHandler(new FormLoginAuthenticationFailureHandler())

                            //.authenticationDetailsSource()

                            //.loginPage("/login.html")
                            //.successForwardUrl("/mlogin")
                            //.failureForwardUrl("/abc")
                            //.defaultSuccessUrl("http://localhost:8080/mainlayout")
                            //.failureUrl("http://localhost:9000/")
                            .addObjectPostProcessor(new ObjectPostProcessor<UsernamePasswordAuthenticationFilter>() {
                                @Override
                                public <O extends UsernamePasswordAuthenticationFilter> O postProcess(O object) {
                                    UsernamePasswordAuthenticationFilter filter = object;
                                    System.out.println("这里可以对过滤器对象进行后加工");
                                    return ((O) filter);
                                }
                            })
            );

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
