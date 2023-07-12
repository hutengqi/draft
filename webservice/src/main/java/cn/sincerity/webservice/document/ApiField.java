package cn.sincerity.webservice.document;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ApiField
 *
 * @author Ht7_Sincerity
 * @date 2023/7/10
 */
@Data
@Builder
public class ApiField {

    private String name;

    private String desc;

    private String type;

    private Boolean require;

    private String remark;
}
