package ss.user.infrastructure.util;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ss.user.infrastructure.persistance.UserSummaryRepository;
import java.nio.charset.StandardCharsets;

/**
 * 布隆过滤器服务
 */
@Service
public class GoogleBloomFilterService {

    private static final Logger logger = LoggerFactory.getLogger(GoogleBloomFilterService.class);

    public final UserSummaryRepository userSummaryRepository;

    private final BloomFilter<String> idFilter = BloomFilter.create(
            Funnels.stringFunnel(StandardCharsets.UTF_8),
            1_000_000,  // 预设容量，根据实际数据量调整
            0.01        // 误判率
    );

    public GoogleBloomFilterService(UserSummaryRepository userSummaryRepository) {
        this.userSummaryRepository = userSummaryRepository;
    }

    @Async
    @PostConstruct
    public void warming() {
        try {
            userSummaryRepository.findAll().parallelStream()
                    .forEach(userSummary -> idFilter.put(userSummary.getId().toString()));
            logger.info("布隆过滤器预热完成");
        } catch (Exception e) {
            logger.error("布隆过滤器预热失败", e);
        }
    }

    public boolean mightContain(String id) {
        return idFilter.mightContain(id);
    }

    public void put(String id) {
        idFilter.put(id);
    }
}
