package ss.springsecurity.utils;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

public interface SecurityConfigUtil {

    /**
     * 实施过滤器的建造策略
     * @param http 待建造的过滤器链的建造者
     */
    void customize(HttpSecurity http);

    /**
     * @param http 待建造的过滤器链的建造者
     * @param ssConfigUtil 过滤器的建造策略
     */
    static void config(HttpSecurity http, SecurityConfigUtil ssConfigUtil) {
        ssConfigUtil.customize(http);

    }

}
