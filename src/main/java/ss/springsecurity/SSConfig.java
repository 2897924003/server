package ss.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import ss.springsecurity.cors.ACorsConfigurationSource;
import ss.springsecurity.formlogin.FormLoginConfig;
import ss.springsecurity.utils.SecurityConfigUtil;

@EnableWebSecurity(debug=false)
@Configuration
public class SSConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config->config
                .anyRequest().permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.cors(config->config.configurationSource(ACorsConfigurationSource.getInstance()));

        SecurityConfigUtil.config(http,new FormLoginConfig());

        return http.build();
    }
}
