package cn.sincerity.webservice.document.model;

import lombok.Data;

/**
 * ApiField: API 文档中字段信息
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Data
public class ApiField {

    private String name;

    private String desc;

    private String type;

    private Boolean require;

    private String remark;

    public static ApiField of(String name, String desc, String type, Boolean require, String remark) {
        ApiField apiField = new ApiField();
        apiField.name = name;
        apiField.desc = desc;
        apiField.type = type;
        apiField.require = require;
        apiField.remark = remark;
        return apiField;
    }
}
