/*
문제: 올바른 괄호
레벨: Lv2
출처: Programmers
링크: https://school.programmers.co.kr/learn/courses/30/lessons/12909
*/
import java.util.Stack;

class Solution {
	boolean solution(String s) {
		// ( --> push
		// ) --> pop
		Stack<Character> stack = new Stack<>();
		for (char c : s.toCharArray()) {
			if (c == '(') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					// 닫는 괄호가 더 많은 경우
					return false;
				}
				stack.pop();
			}
		}
		return stack.isEmpty();
	}
}
