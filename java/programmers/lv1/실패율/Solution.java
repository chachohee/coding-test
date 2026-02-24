package programmers.lv1.실패율;

import java.util.Arrays;

public class Solution {
	public int[] solution(int N, int[] stages) {
		int total = stages.length;
		double[] failrate = new double[N + 1];

		for (int stage = 1; stage <= N; stage++) {
			int stuck = 0; // 클리어하지 못한 사용자

			// 해당 스테이지 클리어 못한 사용자 수 세기
			for (int s : stages) {
				if (s == stage) stuck++;
			}

			// 실패율 계산
			if (total == 0) {
				failrate[stage] = 0;
			} else {
				failrate[stage] = (double) stuck / total; // (double) 클리어 못한 사용자 / 전체 사용자
				total -= stuck;
			}
		}

		// 스테이지 번호 배열 생성
		Integer[] stageArr = new Integer[N];
		for (int i = 0; i < N; i++) {
			stageArr[i] = i + 1;
		}

		// 내림차순 정렬
		Arrays.sort(stageArr, (a,b) -> Double.compare(failrate[b], failrate[a]));

		// Integer[] -> int[]
		return Arrays.stream(stageArr).mapToInt(Integer::intValue).toArray();
	}
}
