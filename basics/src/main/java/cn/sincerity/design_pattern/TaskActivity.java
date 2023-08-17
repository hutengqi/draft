package cn.sincerity.design_pattern;

/**
 * TaskActivity
 *
 * @author Ht7_Sincerity
 * @date 2023/8/17
 */
public class TaskActivity extends BaseActivity {

    public TaskActivity(String type, Long id, String name, Integer scene, String material) {
        super(type, id, name, scene, material);
    }

    @Override
    public void participate() {
        super.participate();
    }

    public static class Builder extends BaseActivity.Builder<Builder> {


        @Override
        public TaskActivity build() {
            return new TaskActivity(this.type, this.id, this.name, this.scene, this.material);
        }
    }
}
