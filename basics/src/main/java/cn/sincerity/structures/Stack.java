package cn.sincerity.structures;

import java.util.ArrayDeque;

/**
 * 关于栈的练习代码
 *
 * @author Ht7_Sincerity
 * @date 2022/10/15
 */
public class Stack {

    public static void main(String[] args) {
        ArrayDeque<Integer> stack = new ArrayDeque<>(7);
        for (int i = 0; i < 10; i++) {
            stack.push(i);
        }
    }
}
