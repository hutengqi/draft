package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.FieldType;
import cn.sincerity.webservice.document.model.ObjectMeta;
import cn.sincerity.webservice.document.resolver.AbstractApiResolver;
import lombok.Data;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * ListTypeValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class CollectionTypeGenerator extends AbstractTypeGenerator {

    @Override
    public boolean support(Class<?> clz) {
        return Collection.class.isAssignableFrom(clz);
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {

        CollectionArgType collectionArgType = CollectionArgType.from(objectMeta.getType());
        ObjectMeta argMeta = ObjectMeta.of(collectionArgType.getArgClz(), collectionArgType.getArgType());
        Object element = AbstractApiResolver.getDefaultValue(argMeta);

        if (List.class.isAssignableFrom(objectMeta.getClazz()))
            return Collections.singletonList(element);

        return Collections.singleton(element);
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        CollectionArgType collectionArgType = CollectionArgType.from(fieldMeta.getType());

        String typeName = generateTypeName(fieldMeta.getClazz(), collectionArgType.getArgClz());
        fieldMeta.setTypeName(typeName);

        ApiField listTypeField = handleCurrentField(fieldMeta);
        apiFields.add(listTypeField);

        FieldMeta argMeta = FieldMeta.of(collectionArgType.getArgClz(), collectionArgType.getArgType(), null, FieldType.ELEMENT);
        AbstractApiResolver.fillApiFields(argMeta, apiFields);
    }

    private String generateTypeName(Class<?> clz, Class<?> argClz) {
        return clz.getSimpleName() + "<" + argClz.getSimpleName() + ">";
    }

    @Override
    public int getOrder() {
        return 1;
    }

    @Data
    static class CollectionArgType {

        private Class<?> argClz;

        private Type argType;

        static CollectionArgType from(Type type) {
            CollectionArgType collectionArgType = new CollectionArgType();
            ParameterizedType parameterizedType = (ParameterizedType) type;
            collectionArgType.argType = parameterizedType.getActualTypeArguments()[0];
            collectionArgType.argClz = getClassFromType(collectionArgType.argType);
            return collectionArgType;
        }
    }
}
