package cn.sincerity.design_pattern;

/**
 * BaseActivity
 *
 * @author Ht7_Sincerity
 * @date 2023/8/17
 */
public class BaseActivity implements Activity {

    public static final String PERIOD = "period";

    public static final String NORMAL = "normal";

    private String type;
    private Long id;
    private String name;
    private Integer scene;
    private String material;

    public BaseActivity(String type, Long id, String name, Integer scene, String material) {
        this.type = type;
        this.id = id;
        this.name = name;
        this.scene = scene;
        this.material = material;
    }

    @Override
    public void participate() {
        // do nothing.
    }

    @SuppressWarnings("unchecked")
    public static class Builder<T extends Builder<T>> {

        protected String type;

        protected Long id;

        protected String name;

        protected Integer scene;

        protected String material;

        public T type(String type) {
            this.type = type;
            return (T) this;
        }

        public T id(Long id) {
            if (PERIOD.equals(this.type))
                this.id = 0L;
            return (T) this;
        }

        public T name(String name) {

            if (PERIOD.equals(this.type))
                this.name = PERIOD + name;
            else
                this.name = NORMAL + name;

            return (T) this;
        }

        public T scene(Integer scene) {
            this.scene = scene;
            return (T) this;
        }

        public T material(String material) {
            this.material = material;
            return (T) this;
        }

        public BaseActivity build() {
            return new BaseActivity(type, id, name, scene, material);
        }
    }
}
