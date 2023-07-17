package cn.sincerity.webservice.document.resolver.generator;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/13
 */
public abstract class AbstractTypeGenerator {

    public static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new ConcurrentHashMap<>();

    private AbstractTypeGenerator next;

    static {
        DEFAULT_VALUE_MAP.put(String.class, "string");
        DEFAULT_VALUE_MAP.put(Byte.class, (byte) 0);
        DEFAULT_VALUE_MAP.put(Short.class, (short) 0);
        DEFAULT_VALUE_MAP.put(Integer.class, 0);
        DEFAULT_VALUE_MAP.put(Long.class, 0L);
        DEFAULT_VALUE_MAP.put(Float.class, 0.00f);
        DEFAULT_VALUE_MAP.put(Double.class, 0.00d);
        DEFAULT_VALUE_MAP.put(BigDecimal.class, new BigDecimal("0.00"));
        DEFAULT_VALUE_MAP.put(Boolean.class, false);
        DEFAULT_VALUE_MAP.put(LocalDate.class, LocalDate.now());
        DEFAULT_VALUE_MAP.put(LocalDateTime.class, LocalDateTime.now());
    }

    public void setNext(AbstractTypeGenerator generator) {
        this.next = generator;
    }

    public Object getDefaultValue(Class<?> clz, Type type) {
        for (Map.Entry<Class<?>, Object> entry : DEFAULT_VALUE_MAP.entrySet()) {
            if (entry.getKey().isAssignableFrom(clz)) {
                return entry.getValue();
            }
        }

        if (judge(clz)) {
            return generateDefaultValue(clz, type);
        }

        if (next != null) {
            return next.getDefaultValue(clz, type);
        }

        return null;
    }

    abstract boolean judge(Class<?> clz);

    abstract Object generateDefaultValue(Class<?> clz, Type type);


    protected Class<?> getClassFromType(Type type) {
        if (type instanceof ParameterizedType)
            return (Class<?>) ((ParameterizedType) type).getRawType();

        return (Class<?>) type;
    }
}
