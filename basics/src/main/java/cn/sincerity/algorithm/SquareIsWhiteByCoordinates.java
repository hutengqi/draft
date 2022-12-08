package cn.sincerity.algorithm;

/**
 * SquareIsWhiteByCoordinates
 *
 * <p>给你一个坐标 coordinates ，它是一个字符串，表示国际象棋棋盘中一个格子的坐标。下图是国际象棋棋盘示意图。</p>
 *
 * <p>如果所给格子的颜色是白色，请你返回 true，如果是黑色，请返回 false 。</p>
 *
 * <p>给定坐标一定代表国际象棋棋盘上一个存在的格子。坐标第一个字符是字母，第二个字符是数字。</p>
 *
 * @author Ht7_Sincerity
 * @date 2022/12/8
 */
public class SquareIsWhiteByCoordinates {

    public boolean squareIsWhite(String coordinates) {
        String h = "abcdefgh", v = "12345678";
        char[] chars = coordinates.toCharArray();
        int hIdx = h.indexOf(chars[0]);
        int vIdx = v.indexOf(chars[1]);
        int hEven = hIdx % 2 == 0 ? 0 : 1;
        int vEven = vIdx % 2 == 0 ? 0 : 1;
        return vEven != hEven;
    }

    public boolean squareIsWhiteLeetCode(String coordinates) {
        return ((coordinates.charAt(0) - 'a' + 1) + (coordinates.charAt(1) - '0')) % 2 == 1;
    }
}
