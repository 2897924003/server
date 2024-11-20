package ss.springsecurity.cors;


import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
public class ACorsConfigurationSource {

    private static final CorsConfigurationSource INSTANCE;

    static {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.addAllowedOrigin("http://localhost:8100");
        corsConfiguration.addAllowedOrigin("http://localhost:9000");
        corsConfiguration.addAllowedOrigin("https://test.opensun.asia");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.addExposedHeader("access_token");
        corsConfiguration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        INSTANCE = urlBasedCorsConfigurationSource;
    }


    /**
     * 不应修改实例内部字段
     */

    public static CorsConfigurationSource getInstance() {
        return INSTANCE;
    }


}
