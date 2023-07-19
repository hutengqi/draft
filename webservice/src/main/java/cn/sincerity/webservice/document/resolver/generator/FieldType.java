package cn.sincerity.webservice.document.resolver.generator;

/**
 * FieldType
 *
 * @author Ht7_Sincerity
 * @date 2023/7/18
 */
public enum FieldType {

    PARAM("param", "请求参数"),
    DATA("data", "返回值"),
    ELEMENT("element", "集合节点元素"),
    MAP_KEY("key", "键值对-Key"),
    MAP_VALUE("value", "键值对-Value"),
    ;
    private final String name;

    private final String desc;

    FieldType(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
