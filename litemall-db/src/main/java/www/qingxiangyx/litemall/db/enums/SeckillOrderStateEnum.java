package www.qingxiangyx.litemall.db.enums;

/**
 * 秒杀订单状态枚举
 */
public enum SeckillOrderStateEnum {
    UNVALID((byte)-1, "无效"),
    SUCCESS((byte)0, "成功"),
    PAID((byte)1, "已付款"),
    DELIVERED((byte)2, "已发货");


    private byte state;
    private String stateInfo;

    SeckillOrderStateEnum(byte state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public byte getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static SeckillOrderStateEnum stateOf(byte index) {
        for (SeckillOrderStateEnum state : values()) {
            if (state.getState() == index) {
                return state;
            }
        }
        return null;
    }
}
