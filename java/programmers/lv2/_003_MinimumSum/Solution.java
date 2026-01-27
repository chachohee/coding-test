package programmers.lv2._003_MinimumSum;

/*
문제: 최솟값 만들기
레벨: Lv2
출처: Programmers
링크: https://school.programmers.co.kr/learn/courses/30/lessons/12941
*/
import java.util.Arrays;

class Solution {
	public int solution(int[] A, int[] B) {
		// A는 오름차순, B는 내림차순으로 정렬
		// A의 작은 값과 B의 큰 값을 곱해야 최솟값이 나옴
		Arrays.sort(A);
		Arrays.sort(B);

		int answer = 0;
		int n = A.length;

		for (int i = 0; i < n; i++) {
			// A의 i번째(작은 값)와 B의 (n-1-i)번째(큰 값)를 곱함
			answer += A[i] * B[n - 1 - i];
		}

		return answer;
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		int[] A = {1, 4, 2};
		int[] B = {5, 4, 4};
		System.out.println(sol.solution(A, B));
	}
}
