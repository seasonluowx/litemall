package www.qingxiangyx.litemall.db.cache.config;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author wenxing.luo
 * @date 2022/1/28 10:22 下午
 * @Description:
 */
public class ProtoStuffRedisSerializer implements RedisSerializer<Object> {

    private static final Schema<ObjectWrapper> schema = RuntimeSchema.getSchema(ObjectWrapper.class);

    @Override
    public byte[] serialize(Object t) {
        LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        byte[] bytes;
        try {
            bytes = ProtostuffIOUtil.toByteArray(new ObjectWrapper(t), schema, buffer);
        } finally {
            buffer.clear();
        }
        return bytes;
    }

    @Override
    public Object deserialize(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        try {
            ObjectWrapper objectWrapper = new ObjectWrapper();
            ProtostuffIOUtil.mergeFrom(bytes, objectWrapper, schema);
            return objectWrapper.getObject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static class ObjectWrapper {
        private Object object;

        ObjectWrapper() {
        }

        ObjectWrapper(Object object) {
            this.object = object;
        }

        public Object getObject() {
            return object;
        }

        public void setObject(Object object) {
            this.object = object;
        }
    }
}
