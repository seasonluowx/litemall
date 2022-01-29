package www.qingxiangyx.litemall.db.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import www.qingxiangyx.litemall.db.util.RedisKey;

import javax.annotation.Resource;

/**
 * @author wenxing.luo
 * @date 2022/1/29 8:53 上午
 * @Description: 已秒杀到商品的用户列表
 */
@Slf4j
@Service
public class BoughtUserCacheService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public Boolean isBought(long seckillId,long userId) {
        String boughtKey = RedisKey.BOUGHT_USERS + seckillId;
        return redisTemplate.opsForSet().isMember(boughtKey, userId);
    }

    public void bought(long seckillId, long userId) {
        String boughtKey = RedisKey.BOUGHT_USERS + seckillId;
        redisTemplate.opsForSet().add(boughtKey, userId);
        remQueue(seckillId,userId);
    }

    public Boolean inQueue(long seckillId,long userId){
        return redisTemplate.opsForSet().isMember(RedisKey.QUEUE_PRE_SECKILL, seckillId + "@" + userId);
    }

    public void addQueue(long seckillId,long userId){
        redisTemplate.opsForSet().add(RedisKey.QUEUE_PRE_SECKILL, seckillId + "@" + userId);
    }

    public void remQueue(long seckillId,long userId){
        redisTemplate.opsForSet().remove(RedisKey.QUEUE_PRE_SECKILL, seckillId + "@" + userId);
    }
}
