package cn.sincerity.webservice.document;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * ApiInformation
 *
 * @author Ht7_Sincerity
 * @date 2023/7/7
 */
@Data
@SuperBuilder
public class ApiInformation {

    private String name;

    private String url;

    private String params;

    private String requestMethod;
}
