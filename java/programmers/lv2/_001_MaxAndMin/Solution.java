package programmers.lv2._001_MaxAndMin;
/*
문제: 최댓값과 최솟값
레벨: Lv2
출처: Programmers
링크: https://school.programmers.co.kr/learn/courses/30/lessons/12939
*/
import java.util.Arrays;

class Solution {
	public String solution(String s) {
		// 공백을 구분으로 배열 만들기
		// int 배열로 만들기
		// 배열 돌면서 차례로 비교하기
		// 최댓값이랑 최솟값 구하기
		// 최솟값 + " " + 최댓값
		String[] str = s.split(" ");
		int[] numArr = Arrays.stream(str).mapToInt(Integer::parseInt).toArray();
		int max = Arrays.stream(numArr).max().getAsInt();
		int min = Arrays.stream(numArr).min().getAsInt();
		String answer = min + " " + max;
		return answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("1 2 3 4"));
	}
}
