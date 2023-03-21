package cn.sincerity.algorithm;

/**
 * TemperatureConvert
 * <p>
 * 给你一个四舍五入到两位小数的非负浮点数 celsius 来表示温度，以 摄氏度（Celsius）为单位。
 * <br/>
 * 你需要将摄氏度转换为 开氏度（Kelvin）和 华氏度（Fahrenheit），并以数组 ans = [kelvin, fahrenheit] 的形式返回结果。
 * </p>
 * 开氏度 = 摄氏度 + 273.15
 * <br/>
 * 华氏度 = 摄氏度 * 1.80 + 32.00
 * @author Ht7_Sincerity
 * @date 2023/3/21
 */
public class TemperatureConvert {

    public double[] convertTemperature(double celsius) {
        double[] ans = new double[2];
        ans[0] = celsius + 273.15d;
        ans[1] = celsius * 1.80 + 32.00d;
        return ans;
    }
}
