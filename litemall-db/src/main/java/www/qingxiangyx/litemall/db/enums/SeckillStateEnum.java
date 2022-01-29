package www.qingxiangyx.litemall.db.enums;

/**
 * 秒杀状态枚举
 */
public enum SeckillStateEnum {
    ENQUEUE_PRE_SECKILL((byte)6, "排队中..."),

    /**
     * 释放分布式锁失败，秒杀被淘汰
     */
    DISTLOCK_RELEASE_FAILED((byte)5, "没抢到"),

    /**
     * 获取分布式锁失败，秒杀被淘汰
     */
    DISTLOCK_ACQUIRE_FAILED((byte)4, "没抢到"),

    /**
     * Redis秒杀没抢到
     */
    REDIS_ERROR((byte)3, "没抢到"),
    SOLD_OUT((byte)2, "已售罄"),
    SUCCESS((byte)1, "秒杀成功"),
    END((byte)0, "秒杀结束"),
    REPEAT_KILL((byte)-1, "重复秒杀"),
    /**
     * 运行时才能检测到的所有异常-系统异常
     */
    INNER_ERROR((byte)-2, "没抢到"),
    /**
     * md5错误的数据篡改
     */
    DATA_REWRITE((byte)-3, "数据篡改"),

    DB_CONCURRENCY_ERROR((byte)-4, "没抢到"),
    /**
     * 被AccessLimitService限流了
     */
    ACCESS_LIMIT((byte)-5, "没抢到");


    private byte state;
    private String stateInfo;

    SeckillStateEnum(byte state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public byte getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillStateEnum stateOf(byte index) {
        for (SeckillStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
