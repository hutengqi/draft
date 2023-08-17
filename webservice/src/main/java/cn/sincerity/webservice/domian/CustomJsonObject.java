package cn.sincerity.webservice.domian;

import cn.sincerity.webservice.serialization.CustomJson;
import com.alibaba.fastjson2.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    @ApiModelProperty("json字段")
    @CustomJson
    private String json;

    @ApiModelProperty("时间字段")
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime localDateTime;

    @ApiModelProperty("二维列表字段")
    private List<List<String>> dyadicArray;


}
