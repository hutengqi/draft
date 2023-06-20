package cn.sincerity;

/**
 * TestInstance
 *
 * @author Ht7_Sincerity
 * @since 2023/2/8
 */
public class TestInstance {

    public static final TestInstance DEFAULT_INSTANCE = new TestInstance(0);

    private Integer count;

    public TestInstance() {

    }

    public TestInstance(int count) {
        this.count = count;
        System.out.println("有参构造器被调用");
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
