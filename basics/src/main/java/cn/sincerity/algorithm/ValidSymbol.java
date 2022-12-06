package cn.sincerity.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Description：有效符号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * Create by Ht7_Sincerity on 2021/10/12
 */
public class ValidSymbol {

    public static void main(String[] args) {
        String s = "()()[]{}";
        System.out.println(isValid(s));
    }

    public static Map<Character, Character> symbolMatch = new HashMap<Character, Character>() {
        private static final long serialVersionUID = -7052690153505153913L;
        {
            put('(', ')');
            put('{', '}');
            put('[', ']');
        }
    };

    public static boolean isValid(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char aChar : chars) {
            if (stack.size() > 0) {
                if (symbolMatch.containsKey(stack.peek()) && aChar == symbolMatch.get(stack.peek())) {
                    stack.pop();
                    continue;
                }
            }
            stack.push(aChar);
        }
        return stack.size() == 0;
    }
}
