package www.qingxiangyx.litemall.db.enums;

/**
 * @author wenxing.luo
 * @date 2022/1/28 10:10 上午
 * @Description:
 */
public enum GoodSellTypeEnum {
    NORMAL((byte)1, "普通商品"),
    SECKILL((byte)2, "秒杀"),
    GROUP((byte)3,"团购"),
    BARGAIN((byte)4,"砍价");

    private byte code;
    private String msg;

    public byte getCode() {
        return code;
    }

    public void setCode(byte code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    GoodSellTypeEnum(byte code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GoodSellTypeEnum getByCode(byte code) {
        for (GoodSellTypeEnum typeEnum : GoodSellTypeEnum.values()) {
            if (typeEnum.code == code) {
                return typeEnum;
            }
        }
        return null;
    }
}
