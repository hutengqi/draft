package cn.sincerity.webservice.document.resolver.generator;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * AbstractTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/13
 */
public abstract class AbstractTypeValueGenerator implements TypeValueGenerator {

    public static final Map<Class<?>, Object> DEFAULT_VALUE_MAP = new ConcurrentHashMap<>();

    public static final List<AbstractTypeValueGenerator> GENERATORS = new LinkedList<>();

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

    @Override
    public Object getDefaultValue(Class<?> clz, Type type) {
        for (Map.Entry<Class<?>, Object> entry : DEFAULT_VALUE_MAP.entrySet()) {
            if (entry.getKey().isAssignableFrom(clz)) {
                return entry.getValue();
            }
        }

        Object newInstance = null;
        for (AbstractTypeValueGenerator generator : GENERATORS) {
            if (support(clz))
                newInstance = generator.generateDefaultValue(clz, type);
        }

        if (newInstance != null)
            DEFAULT_VALUE_MAP.put(clz, newInstance);

        return newInstance;
    }

    abstract boolean support(Class<?> type);

    abstract Object generateDefaultValue(Class<?> clz, Type type);
}
