package cn.sincerity;

/**
 * IdTypeEnum
 *
 * @author Ht7_Sincerity
 * @date 2023/6/9
 */
public enum IdTypeEnum {

    ID_CARD_CERT_TYPE("0", "身份证"),

    PASSPORT_CERT_TYPE("1", "护照"),

    HOUSE_CARD_CERT_TYPE("4", "户口本"),

    BIRTH_CARD_CERT_TYPE("7", "出生证"),

    REENTRY_PERMIT_CERT_TYPE("B", "回乡证"),

    TAIBAO_CARD_CERT_TYPE("E", "台胞证"),

    HM_RESIDENT_CERT_TYPE("G", "港澳居民居住证"),

    TAIWAN_RESIDENT_CERT_TYPE("H", "台湾居民居住证"),

    FOREIGNS_RESIDENT_CERT_TYPE("I", "外国人永久居留身份证"),

    CHINA_PASSPORT("J", "中国护照");


    private final String name;
    private final String code;

    IdTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean existsByCode(String code) {
        for (IdTypeEnum value : IdTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return true;
            }
        }
        return false;
    }

    public String getCode() {
        return code;
    }


    public String getName() {
        return name;
    }

    public static void main(String[] args) {
        System.out.println(existsByCode("0"));
    }
}
