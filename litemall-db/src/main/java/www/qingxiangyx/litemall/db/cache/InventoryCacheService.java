package www.qingxiangyx.litemall.db.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import www.qingxiangyx.litemall.db.util.RedisKey;

import javax.annotation.Resource;

/**
 * @author wenxing.luo
 * @date 2022/1/29 8:53 上午
 * @Description:
 */
@Slf4j
@Service
public class InventoryCacheService {
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    public Integer getInventory(long seckillId) {
        String inventoryKey = RedisKey.SECKILL_INVENTORY + seckillId;
        Object inventoryStr = redisTemplate.opsForValue().get(inventoryKey);
        if(inventoryStr == null){
            return 0;
        }
        return (int)inventoryStr;
    }

    public void setInventory(long seckillId,int inventory) {
        String inventoryKey = RedisKey.SECKILL_INVENTORY + seckillId;
        redisTemplate.opsForValue().set(inventoryKey,inventory);
    }

    public void outOfWarehouse(long seckillId) {
        String inventoryKey = RedisKey.SECKILL_INVENTORY + seckillId;
        redisTemplate.opsForValue().decrement(inventoryKey);
    }
}
