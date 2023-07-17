package cn.sincerity.webservice.document.resolver.generator;

import cn.sincerity.webservice.document.ApiField;

import java.lang.reflect.Type;
import java.util.List;

/**
 * DefaultValueGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/13
 */
public interface TypeValueGenerator {

    Object getDefaultValue(Class<?> clz, Type type);

    List<ApiField> generateFields();
}
