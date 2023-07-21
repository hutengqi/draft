package cn.sincerity.webservice.document.model;

import lombok.Data;

/**
 * ApiEnum
 *
 * @author Ht7_Sincerity
 * @date 2023/7/21
 */
@Data
public class ApiEnum {

    private String enumName;

    private String code;

    private String desc;

    public static ApiEnum of(String enumName, String code, String desc) {
        ApiEnum apiEnum = new ApiEnum();
        apiEnum.enumName = enumName;
        apiEnum.code = code;
        apiEnum.desc = desc;
        return apiEnum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiEnum apiEnum = (ApiEnum) o;

        if (!enumName.equals(apiEnum.enumName)) return false;
        if (!code.equals(apiEnum.code)) return false;
        return desc.equals(apiEnum.desc);
    }

    @Override
    public int hashCode() {
        int result = enumName.hashCode();
        result = 31 * result + code.hashCode();
        result = 31 * result + desc.hashCode();
        return result;
    }
}
