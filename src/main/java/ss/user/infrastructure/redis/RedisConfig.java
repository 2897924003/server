package ss.user.infrastructure.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import ss.user.domain.UserSummary;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, UserSummary> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, UserSummary> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        // 配置键和值的序列化器
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new Jackson2JsonRedisSerializer<>(UserSummary.class));

        return template;
    }
}
