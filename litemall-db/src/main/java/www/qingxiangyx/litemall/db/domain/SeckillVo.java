package www.qingxiangyx.litemall.db.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

@Data
public class SeckillVo {

    private Long id;

    private LitemallGoods goods;

    private String name;

    private Integer inventory;
    /**秒杀价格**/
    private BigDecimal seckillPrice;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime createTime;

    private Long version;
    // 0-未开始，1-进行中，2-已结束
    private int status;

    public int getStatus() {
        LocalDateTime now = LocalDateTime.now();
        return startTime.isBefore(now)?endTime.isAfter(now)?1:2:0;
    }
    public void setStatus(){
        return;
    }
}