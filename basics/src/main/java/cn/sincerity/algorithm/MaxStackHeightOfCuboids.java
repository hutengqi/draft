package cn.sincerity.algorithm;

import java.util.Arrays;

/**
 * MaxStackHeightOfCuboids: 堆叠长方体的最大高度
 *
 * @author Ht7_Sincerity
 * @date 2022/12/10
 */
public class MaxStackHeightOfCuboids {

    public static void main(String[] args) {
        int[][] cuboids = {{34, 29, 33}, {7, 25, 75}, {31, 15, 68}, {80, 80, 38}, {72, 21, 30}, {2, 66, 25}, {59, 36, 6}, {39, 48, 95}, {35, 85, 71}, {17, 14, 78}};
        System.out.println(maxHeightByLeetCode(cuboids));
    }

    public static int maxHeightByLeetCode(int[][] cuboids) {
        for (int[] c : cuboids)
            Arrays.sort(c);
        Arrays.sort(cuboids, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] != b[1] ? a[1] - b[1] : a[2] - b[2]);
        int ans = 0, n = cuboids.length;
        int[] f = new int[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j)
                // 排序后，cuboids[j][0] <= cuboids[i][0] 恒成立
                if (cuboids[j][1] <= cuboids[i][1] && cuboids[j][2] <= cuboids[i][2])
                    f[i] = Math.max(f[i], f[j]); // cuboids[j] 可以堆在 cuboids[i] 上
            f[i] += cuboids[i][2];
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
