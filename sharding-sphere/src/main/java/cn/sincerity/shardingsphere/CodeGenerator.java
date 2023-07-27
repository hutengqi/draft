package cn.sincerity.shardingsphere;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * CodeGenerator
 *
 * @author Ht7_Sincerity
 * @date 2023/7/27
 */
public class CodeGenerator {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/sharding-sphere-01?useSSL=false&serverTimezone=Asia/Shanghai";

    private static final String username = "root";

    private static final String password = "hutengqi1228.?";

    private static final String path = System.getProperty("user.dir") + "/sharding-sphere/src/main/java/";

    public static void main(String[] args) {
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("Ht7_Sincerity") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(path); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.sincerity.shardingsphere.domain") // 设置父包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper, path + "cn/sincerity/shardingsphere/domain/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addExclude(
                                    "shoping_01"
                            ).addTableSuffix("_00");
                    // 设置需要生成的表名
                    // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
