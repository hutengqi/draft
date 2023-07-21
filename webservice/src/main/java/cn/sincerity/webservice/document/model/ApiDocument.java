package cn.sincerity.webservice.document.model;

import lombok.Data;

import java.util.List;

/**
 * ApiDocument
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Data
public class ApiDocument {

    private String group;

    private List<ApiMethod> methods;

    private List<ApiEnum> enums;

    public static ApiDocument of(String group, List<ApiMethod> methods, List<ApiEnum> enums) {
        ApiDocument apiDocument = new ApiDocument();
        apiDocument.group = group;
        apiDocument.methods = methods;
        apiDocument.enums = enums;
        return apiDocument;
    }
}
