package programmers.lv2.구명보트;

import java.util.Arrays;

/**
 * Greedy: 매 순간 가장 좋아 보이는 선택을 한다
 * 즉, 지금 당장 최선의 선택을 계속 선택하는 알고리즘
 * 구명보트
 * -> 가장 무거운 사람은 같이 탈 수 있는 선택지가 가장 적다
 * -> 먼저 처리하는 것이 최적이다
 * */
public class Solution {
	public int solution(int[] people, int limit) {
		Arrays.sort(people);	// [50,50,70,80]

		int left = 0;
		int right = people.length - 1;

		int boat = 0;

		// 0 1 2 3
		while (left <= right) {
			if (people[left] + people[right] <= limit) {
				left++;
			}
			right--; // 무거운 쪽은 무조건 탐
			boat++;
		}
		return boat;
	}
}
