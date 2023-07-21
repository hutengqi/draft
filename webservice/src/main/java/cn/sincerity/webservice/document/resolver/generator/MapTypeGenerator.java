package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.AbstractMethodResolver;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * MapTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public class MapTypeGenerator extends AbstractTypeGenerator{

    @Override
    public boolean support(Class<?> clz) {
        return Map.class.isAssignableFrom(clz);
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        MapArgType mapArgType = MapArgType.from(objectMeta.getType());

        ObjectMeta keyMeta = ObjectMeta.of(mapArgType.getKeyClz(), mapArgType.getKeyType());
        Object key = AbstractMethodResolver.getDefaultValue(keyMeta);

        ObjectMeta valueMeta = ObjectMeta.of(mapArgType.getValueClz(), mapArgType.getValueType());
        Object value = AbstractMethodResolver.getDefaultValue(valueMeta);

        return Collections.singletonMap(key, value);
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        MapArgType mapArgType = MapArgType.from(fieldMeta.getType());

        String typeName = generateMapTypeName(fieldMeta.getClazz(), mapArgType);
        fieldMeta.setTypeName(typeName);

        ApiField mapTypeField = handleCurrentField(fieldMeta);
        apiFields.add(mapTypeField);

        FieldMeta keyMeta = FieldMeta.of(mapArgType.getKeyClz(), mapArgType.getKeyType(), null, FieldType.MAP_KEY);
        AbstractMethodResolver.fillApiFields(keyMeta, apiFields);

        FieldMeta valueMeta = FieldMeta.of(mapArgType.getValueClz(), mapArgType.getValueType(), null, FieldType.MAP_VALUE);
        AbstractMethodResolver.fillApiFields(valueMeta, apiFields);
    }

    public String generateMapTypeName(Class<?> clz, MapArgType mapArgType) {
        return clz.getSimpleName() + "<" + mapArgType.getKeyClz().getSimpleName() + "," + mapArgType.getValueClz().getSimpleName() + ">";
    }

    @Override
    public int getOrder() {
        return 2;
    }

    @Data
    static class MapArgType {

        private Type keyType;

        private Class<?> keyClz;

        private Type valueType;

        private Class<?> valueClz;

        static MapArgType from(Type mapType) {
            MapArgType mapArgType = new MapArgType();
            ParameterizedType parameterizedType = (ParameterizedType) mapType;
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            mapArgType.keyType = actualTypeArguments[0];
            mapArgType.keyClz = getClassFromType(mapArgType.keyType);
            mapArgType.valueType = actualTypeArguments[1];
            mapArgType.valueClz = getClassFromType(mapArgType.valueType);
            return mapArgType;
        }
    }
}
