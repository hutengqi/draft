package cn.sincerity.webservice.document;

/**
 * TestEnum
 *
 * @author Ht7_Sincerity
 * @date 2023/7/19
 */
public enum TestEnum {


    ONE(1, "one"),
    TWO(2,"two");

    private final Integer code;

    private final String desc;

    TestEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
