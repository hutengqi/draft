package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.ObjectMeta;

import java.util.List;

/**
 * BasicTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class BasicTypeGenerator extends AbstractTypeGenerator {

    @Override
    public boolean support(Class<?> clz) {
        return primitiveType(clz);
    }

    @Override
    public Object generateDefaultValue(ObjectMeta objectMeta) {
        return null;
    }

    @Override
    public void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields) {
        ApiField currentField = handleCurrentField(fieldMeta);
        apiFields.add(currentField);
    }

    @Override
    public int getOrder() {
        return Integer.MIN_VALUE;
    }
}
