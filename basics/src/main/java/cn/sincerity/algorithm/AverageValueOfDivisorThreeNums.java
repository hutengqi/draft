package cn.sincerity.algorithm;

/**
 * AverageValueOfDivisorThreeNums: 可被三整除的偶数的平均值
 *
 * @author Ht7_Sincerity
 * @date 2023/5/29
 */
public class AverageValueOfDivisorThreeNums {

    /**
     * 计算数组中能被3整除的数的平均值
     *
     * @param nums 数组
     * @return 平均值
     */
    public int averageValue(int[] nums) {
        int sum = 0;
        int count = 0;
        for (int num : nums) {
            if (num % 3 == 0 && num % 2 == 0) {
                sum += num;
                count++;
            }
        }
        if (sum == 0)
            return 0;
        return sum / count;
    }
}
