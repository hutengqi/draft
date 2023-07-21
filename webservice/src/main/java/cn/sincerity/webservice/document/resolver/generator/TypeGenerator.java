package cn.sincerity.webservice.document.resolver.generator;


import cn.sincerity.webservice.document.model.ApiField;
import cn.sincerity.webservice.document.model.FieldMeta;
import cn.sincerity.webservice.document.model.ObjectMeta;

import java.util.List;

/**
 * TypeGenerator: 根据数据类型生成 默认空对象 和 字段信息 的生成器
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
public interface TypeGenerator {

    boolean support(Class<?> clz);

    Object generateDefaultValue(ObjectMeta objectMeta);

    void generateApiFields(FieldMeta fieldMeta, List<ApiField> apiFields);
}
