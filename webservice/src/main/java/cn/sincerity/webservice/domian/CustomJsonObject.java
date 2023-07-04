package cn.sincerity.webservice.domian;

import cn.sincerity.webservice.serialization.CustomJson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * CustomerJsonObject
 *
 * @author Ht7_Sincerity
 * @date 2023/6/8
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomJsonObject {

    @CustomJson
    private String json;
}