package cn.sincerity.webservice.document.model;

import lombok.Data;

import java.util.List;

/**
 * ApiResource: API 文档中方法信息
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Data
public class ApiMethod {

    private String name;

    private String path;

    private String param;

    private String paramType;

    private String requestMethod;

    private String data;

    private List<ApiField> requestFields;

    private List<ApiField> responseFields;

    public static ApiMethod of(String name, String path, String param, String paramType, String requestMethod, String data, List<ApiField> requestFields, List<ApiField> responseFields) {
        ApiMethod apiMethod = new ApiMethod();
        apiMethod.name = name;
        apiMethod.path = path;
        apiMethod.param = param;
        apiMethod.paramType = paramType;
        apiMethod.requestMethod = requestMethod;
        apiMethod.data = data;
        apiMethod.requestFields = requestFields;
        apiMethod.responseFields = responseFields;
        return apiMethod;
    }
}
