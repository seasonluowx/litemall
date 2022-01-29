package www.qingxiangyx.litemall.db.cache.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author: chenghao.chen
 * @date: 2019/3/11 10:14
 * @description:
 */
@Slf4j
@Configuration
public class RedisConfig {

    @Value("${spring.cache.pool.min-idle}")
    private int minIdle;

    @Value("${spring.cache.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.cache.pool.max-total}")
    private int maxTotal;

    @Value("${spring.cache.pool.max-wait-millis}")
    private int maxWaitMillis;

    @Value("${spring.cache.pool.test-on-borrow}")
    private boolean testOnBorrow;

    @Value("${spring.cache.pool.test-on-return}")
    private boolean testOnReturn;

    @Value("${spring.cache.pool.test-while-idle}")
    private boolean testWhileIdle;

    @Value("${spring.cache.pool.test-between-eviction-runs-millis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.cache.pool.num-tests-pre-eviction-run}")
    private int numTestsPerEvictionRun;

    @Value("${spring.cache.pool.min-evictable-idle-time-millis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.cache.pool.soft-min-evictable-time-millis}")
    private int softMinEvictableIdleTimeMillis;

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.database}")
    private int database;

    @Bean("jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMinIdle(minIdle);
        config.setMaxIdle(maxIdle);
        config.setMaxTotal(maxTotal);
        config.setMaxWaitMillis(maxWaitMillis);
        config.setTestOnBorrow(testOnBorrow);
        config.setTestOnReturn(testOnReturn);
        config.setTestWhileIdle(testWhileIdle);
        config.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        config.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        config.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        config.setSoftMinEvictableIdleTimeMillis(softMinEvictableIdleTimeMillis);
        return config;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory(){

        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setPoolConfig(jedisPoolConfig());
        jedisConnectionFactory.setHostName(host);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setTimeout(timeout);
        jedisConnectionFactory.setUsePool(true);
        jedisConnectionFactory.setDatabase(database);
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate redisTemplate(){
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(jedisConnectionFactory());
        redisTemplate.setDefaultSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new ProtoStuffRedisSerializer());
        return redisTemplate;
    }
}
