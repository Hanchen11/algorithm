package com.learn.algorithm.stack;

/**
 *
 * 20,155,232,844,224,682,496
 *
 * @Author hanchen
 * @Date: 2020/4/9
 */
public class StackCode {

    /**
     * leecode 20. 有效的括号
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        ArrayStack<Character> stack = new ArrayStack<>(s.length());
        for (int i = 0; i < s.length(); i++) {
            if (isSymmetry(stack.top(), s.charAt(i))) {
                stack.pop();
            } else {
                stack.push(s.charAt(i));
            }
        }
        return stack.isEmpty();
    }

    public static boolean isSymmetry(Character a, Character b) {
        if (a == null || b == null) {
            return false;
        }
        return (a == '(' && b == ')') || (a == '{' && b == '}') || (a == '[' && b == ']');
    }


    public static void main(String[] args) {
        System.out.println(isValid("[[]}"));
    }
}
