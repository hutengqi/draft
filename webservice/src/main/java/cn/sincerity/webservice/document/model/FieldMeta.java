package cn.sincerity.webservice.document.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.lang.reflect.Field;
import java.lang.reflect.Type;

/**
 * FieldMeta
 *
 * @author Ht7_Sincerity
 * @date 2023/7/19
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class FieldMeta extends ObjectMeta {

    private Field field;

    private FieldType fieldType;

    private String typeName;

    public FieldMeta(Class<?> clazz, Type type) {
        super(clazz, type);
    }

    public static FieldMeta of(Class<?> clazz, Type type, Field field, FieldType fieldType) {
        FieldMeta meta = new FieldMeta(clazz, type);
        meta.field = field;
        meta.fieldType = fieldType;
        meta.typeName = clazz.getSimpleName();
        return meta;
    }
}
