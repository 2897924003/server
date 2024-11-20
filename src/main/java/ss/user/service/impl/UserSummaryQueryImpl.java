package ss.user.service.impl;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ss.user.usecase.exception.UserNotFoundException;
import ss.user.domain.UserSummary;
import ss.user.infrastructure.persistance.UserSummaryRepository;
import ss.user.infrastructure.util.GoogleBloomFilterService;
import ss.user.service.UserSummaryQueryService;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

/**
 * 查询用户概览信息-访问量可能较大
 */
@Service
public class UserSummaryQueryImpl implements UserSummaryQueryService {
    private final UserSummaryRepository userSummaryRepository;
    private final RedisTemplate<String, UserSummary> redisTemplate;
    private final GoogleBloomFilterService bloomFilterService;

    public UserSummaryQueryImpl(UserSummaryRepository userSummaryRepository,
                                RedisTemplate<String, UserSummary> redisTemplate, GoogleBloomFilterService bloomFilterService
    ) {
        this.userSummaryRepository = userSummaryRepository;
        this.redisTemplate = redisTemplate;
        this.bloomFilterService = bloomFilterService;
    }

    /**
     * 获取用户概览信息
     * @param id 用户id
     * @return 用户概览信息-用户名-头像
     */
    @Override
    public UserSummary findUserSummaryById(Long id) {


        // 使用布隆过滤器先判断该用户ID是否不存在
        if (!bloomFilterService.mightContain(id.toString())) {
            throw new UserNotFoundException("用户不存在");
        }

        // 检查 redis 缓存中是否存在
        UserSummary redisSummary = redisTemplate.opsForValue().get("usersummary:" + id);
        if (redisSummary != null) {
            return redisSummary;
        }

        // 从数据库中查询用户
        Optional<UserSummary> dbSummary = userSummaryRepository.findById(id);

        // 1. 存在：将userSummary缓存到redis
        // 2. 不存在： 设置默认值，避免缓存穿透。（测试很难覆盖，除非布隆过滤器出现了误差）
        dbSummary.ifPresentOrElse(
                db-> redisTemplate.opsForValue().set("usersummary:" + id, db, 10, TimeUnit.MINUTES),
                ()-> {
                    redisTemplate.opsForValue().set("usersummary:" + id, new UserSummary(), 1, TimeUnit.MINUTES);
                    throw new UserNotFoundException("用户不存在");
                }
        );

        // 运行至此->用户确实存在->更新布隆过滤器
        bloomFilterService.put(id.toString());

        return dbSummary.get();
    }
}



