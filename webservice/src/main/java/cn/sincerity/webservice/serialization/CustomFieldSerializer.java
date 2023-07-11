package cn.sincerity.webservice.serialization;

import cn.sincerity.webservice.domian.CustomJsonObject;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

/**
 * CustomFieldSerializer
 *
 * @author Ht7_Sincerity
 * @date 2023/6/8
 */
public class CustomFieldSerializer extends StdSerializer<Object> implements ContextualSerializer {

    public CustomFieldSerializer() {
        super(Object.class, false);
    }

    public CustomFieldSerializer(Class<?> t) {
        super(t, false);
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider prov, BeanProperty property) throws JsonMappingException {
        return new CustomFieldSerializer(property.getType().getRawClass());
    }

    @Override
    public void serialize(Object value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        CustomJsonObject x;
        try {
            x = (CustomJsonObject) value;
        } catch (Exception e) {
            return;
        } finally {
            gen.writeNull();
        }
        if (x != null) {
            gen.writeRawValue(x.getJson() + " custom");
        }
    }
}
