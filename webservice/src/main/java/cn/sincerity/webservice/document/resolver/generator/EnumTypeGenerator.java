package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.ObjectMeta;

import java.util.List;

/**
 * EnumTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/19
 */
public class EnumTypeGenerator extends AbstractTypeGenerator {

    @Override
    public boolean support(Class<?> clz) {
        return clz.isEnum();
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        Object[] enumConstants = objectMeta.getClazz().getEnumConstants();
        if (enumConstants == null || enumConstants.length < 1)
            return null;

        return enumConstants[0];
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        ApiField enumField = handleCurrentField(fieldMeta);
        apiFields.add(enumField);
    }

    @Override
    public int getOrder() {
        return 3;
    }
}
