/*
package ss.user.infrastructure.util;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class BloomFilterService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String FILTER_KEY = "user:summary";

    */
/**
     * 初始化布隆过滤器
     * 设置误差率和预计容量
     *//*

    @PostConstruct
    public void createBloomFilter() {
        // 先检查布隆过滤器是否已经存在
        Boolean exists = redisTemplate.execute((RedisCallback<Boolean>) connection ->
                (Boolean) connection.execute("EXISTS", FILTER_KEY.getBytes()));

        if (exists != null && exists) {
            // 如果布隆过滤器已存在，跳过创建
            System.out.println("Bloom filter already exists.");
        } else {
            // 使用 Redis 命令 BF.RESERVE 来创建布隆过滤器
            Boolean execute = redisTemplate.execute((RedisCallback<Boolean>) connection ->
                    (Boolean) connection.execute("BF.RESERVE", FILTER_KEY.getBytes(), "0.01".getBytes(), "1000".getBytes()));

            if (execute != null && execute) {
                System.out.println("Bloom filter created successfully!");
            } else {
                System.out.println("Failed to create Bloom filter.");
            }
        }
    }


    */
/**
     * 添加元素到布隆过滤器
     * @param element 元素
     *//*

    public void addElement(String element) {
        // 使用 Redis 命令 BF.ADD 来添加元素
        Boolean execute = redisTemplate.execute((RedisCallback<Boolean>) connection ->
                (Boolean) connection.execute("BF.ADD", FILTER_KEY.getBytes(), element.getBytes()));

        if (execute != null && execute) {
            System.out.println("Element added to Bloom filter: " + element);
        } else {
            System.out.println("Failed to add element to Bloom filter: " + element);
        }
    }

    */
/**
     * 检查元素是否存在于布隆过滤器中
     * @param element 元素
     * @return 是否可能包含该元素
     *//*

    public boolean mightContainElement(String element) {
        // 使用 Redis 命令 BF.MEXISTS 来检查元素是否可能存在
        List<Boolean> result = redisTemplate.execute((RedisCallback<List<Boolean>>) connection ->
                (List<Boolean>) connection.execute("BF.MEXISTS", FILTER_KEY.getBytes(), element.getBytes()));

        return result != null && !result.isEmpty() && result.get(0);
    }
}
*/
