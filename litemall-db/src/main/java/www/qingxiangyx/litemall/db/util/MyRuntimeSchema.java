package www.qingxiangyx.litemall.db.util;

import io.protostuff.runtime.RuntimeSchema;
import www.qingxiangyx.litemall.db.domain.Seckill;

public class MyRuntimeSchema {
    private static MyRuntimeSchema ourInstance = new MyRuntimeSchema();

    private RuntimeSchema<Seckill> goodsRuntimeSchema;


    public static MyRuntimeSchema getInstance() {
        return ourInstance;
    }

    private MyRuntimeSchema() {
        RuntimeSchema<Seckill> seckillSchemaVar = RuntimeSchema.createFrom(Seckill.class);
        setGoodsRuntimeSchema(seckillSchemaVar);
    }

    public RuntimeSchema<Seckill> getGoodsRuntimeSchema() {
        return goodsRuntimeSchema;
    }

    private void setGoodsRuntimeSchema(RuntimeSchema<Seckill> goodsRuntimeSchema) {
        this.goodsRuntimeSchema = goodsRuntimeSchema;
    }
}
