package cn.sincerity.webservice.document;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * ApiInformation
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@Data
@NoArgsConstructor
public class ApiInformation {

    private String name;

    private String path;

    private String params;

    private String requestMethod;

    private String paramType;
}
