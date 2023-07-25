package cn.sincerity.webservice.domian;

import io.swagger.annotations.ApiModel;

/**
 * TestEnums
 *
 * @author Ht7_Sincerity
 * @date 2023/7/22
 */
@ApiModel("测试枚举")
public enum TestEnum {

    ONE("1", "one"),
    TWO("2", "two"),
    ;

    private final String code;

    private final String desc;

    TestEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
