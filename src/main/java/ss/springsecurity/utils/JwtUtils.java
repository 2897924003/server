package ss.springsecurity.utils;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtils {



    // 生成JWT的密钥（可以自定义更安全的密钥管理）
    private static final String SECRET = "opensun";

    // 生成JWT令牌
    public static String generateToken(String system, String name, Collection<? extends GrantedAuthority> authorities) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 将权限列表转换为以逗号分隔的字符串
        String permissions = authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        // 设置JWT的过期时间
        long expirationTime = System.currentTimeMillis() + 60 * 60 * 1000; // 1小时有效期
        //int tokenStatus = redisTemplate.opsForValue().get("");
        //获取当前认证用户的id
        Object userId = RequestContextHolder.currentRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_REQUEST);
        // 创建JWT
        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())  // 使用 UUID 生成唯一的 JWT ID
                .withClaim("userId",(Long) userId)//用户id
                .withIssuer("auth-center")            // 认证中心签发者
                .withSubject(name)                    // 用户名
                .withClaim("system", system)          // 目标系统
                .withClaim("permissions", permissions)// 对应系统的权限
                //.withClaim("token_status", tokenStatus) // 令牌状态密码
                .withExpiresAt(new Date(expirationTime)) // 过期时间
                .sign(algorithm);                     // 签名
    }

    // 校验JWT并解析（用户网关时使用）
    public static DecodedJWT verifyToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.require(algorithm)
                .withIssuer("auth-center")
                .build()
                .verify(token); // 验证并返回解码后的JWT
    }
}
