package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;
import cn.sincerity.webservice.document.annotation.ApiRemark;
import io.swagger.annotations.ApiModelProperty;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.List;

/**
 * EnumTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/19
 */
public class EnumTypeGenerator extends AbstractTypeGenerator{

    @Override
    boolean judge(Class<?> clz) {
        return clz.isEnum();
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        Object[] enumConstants = clz.getEnumConstants();
        if (enumConstants == null || enumConstants.length < 1)
            return null;

        return enumConstants[0];
    }

    @Override
    public void generateApiFields(Class<?> clz, Type type, Field field, List<ApiField> apiFields, FieldType fieldType) {
        if (field == null) {
            ApiField apiField = ApiField.builder()
                    .name(fieldType.getName())
                    .type(clz.getSimpleName())
                    .desc(fieldType.getDesc())
                    .build();
            apiFields.add(apiField);
            return;
        }
        ApiField build = ApiField.builder()
                .name(field.getName())
                .type(clz.getSimpleName())
                .desc(extractValue(field, ApiModelProperty::value))
                .require(extractValue4Require(field))
                .remark(extractValue(field, ApiRemark::remark))
                .build();
        apiFields.add(build);
    }
}
