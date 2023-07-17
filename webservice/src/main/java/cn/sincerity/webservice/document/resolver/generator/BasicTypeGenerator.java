package cn.sincerity.webservice.document.resolver.generator;

import java.lang.reflect.Type;

/**
 * BasicTypeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/17
 */
public class BasicTypeGenerator extends AbstractTypeGenerator {

    @Override
    boolean judge(Class<?> clz) {
        return false;
    }

    @Override
    Object generateDefaultValue(Class<?> clz, Type type) {
        return null;
    }
}
