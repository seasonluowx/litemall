package www.qingxiangyx.litemall.db.dto;


import lombok.Data;
import www.qingxiangyx.litemall.db.domain.SeckillOrder;
import www.qingxiangyx.litemall.db.enums.SeckillStateEnum;

/**
 * 封装秒杀执行后结果
 * Created by liushaoming on 2019-01-14.
 */
@Data
public class SeckillExecution {

    private long seckillId;

    //秒杀执行结果状态
    private int state;

    //状态表示
    private String stateInfo;

    //秒杀成功对象
    private SeckillOrder seckillOrder;

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + seckillOrder +
                '}';
    }

    public SeckillExecution(long seckillId, SeckillStateEnum stateEnum, SeckillOrder seckillOrder) {
        this.seckillId = seckillId;
        this.state = stateEnum.getState();
        this.stateInfo = stateEnum.getStateInfo();
        this.seckillOrder = seckillOrder;
    }

    public SeckillExecution(long seckillId, SeckillStateEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.stateInfo = statEnum.getStateInfo();
    }
}
