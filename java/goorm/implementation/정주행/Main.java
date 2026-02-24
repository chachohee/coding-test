package goorm.implementation.정주행;

/**
 * [문제] 정주행
 *
 * 승철이가 본 웹툰 에피소드 번호 N개가 주어질 때,
 * 해당 번호들을 정렬하여 연속적인 수열로 표현할 수 있는지 판단한다.
 *
 * 연속적인 수열이란,
 * 정렬했을 때 모든 i에 대해
 * episodes[i] = episodes[i - 1] + 1 을 만족하는 수열을 의미한다.
 *
 * [입력]
 * - 첫째 줄: 에피소드 수 N (1 ≤ N ≤ 100,000)
 * - 둘째 줄: 서로 다른 에피소드 번호 N개 (1 ≤ 번호 ≤ 1,000,000)
 *
 * [출력]
 * - 연속적인 수열이면 "YES"
 * - 아니면 "NO"
 *
 * [풀이]
 * 1. 에피소드 번호를 배열에 저장
 * 2. 배열 정렬
 * 3. 인접한 두 값의 차이가 모두 1인지 확인
 *
 * [시간 복잡도]
 * - 정렬: O(N log N)
 * - 비교: O(N)
 * - 전체: O(N log N)
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		// 본 에피소드 수 (1 < n < 100,000)
		// 중복 X
		// 연속적인 수열: 항상 i번째 숫자의 값이 (i+1)번째 숫자보다 1이 작은 정수로 이루어진 수열
		// 에피소드 번호 (1 < n < 1000,000)
		// 1. 에피소드 번호들을 배열에 저장
		// 2. 정렬
		// 3. 인접한 두 수의 차이가 모두 1인지 확인
		// 4. 모두 1이면 YES, 아니면 NO

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 입력
		int n = Integer.parseInt(br.readLine());

		if (n == 1) {
			br.readLine(); // 입력은 받아야 함 (문제에서 두 줄 입력받는다고 했으니까)
			System.out.println("YES");
			return;
		}

		StringTokenizer st = new StringTokenizer(br.readLine());
		// String[] tokens = br.readLine().split(" ");

		int[] episodes = new int[n];
		for (int i = 0; i < n; i++) {
			// episodes[i] = Integer.parseInt(tokens[i]);
			episodes[i] = Integer.parseInt(st.nextToken());
		}

		// 정렬
		Arrays.sort(episodes);

		// 연속적인지 확인
		boolean isConsecutive = true;
		for (int i = 1; i < n; i++) {
			if (episodes[i] - episodes[i-1] != 1) {
				isConsecutive = false;
				break;
			}
		}

		// 출력
		System.out.println(isConsecutive ? "YES" : "NO");

	}
}


/*
 * 7️⃣ 핵심 정리
 * 	•	버퍼 = 임시 저장 공간
 * 	•	목적 = 시스템 호출 최소화
 * 	•	BufferedReader = 입력 버퍼링
 * 	•	코테 입력 많다 → 무조건 버퍼 사용
 *
 * 8️⃣ 한 문장 답변
 *
 * 버퍼는 입력 데이터를 한 번에 모아 메모리에 저장해두고,
 * 프로그램이 메모리에서 데이터를 읽게 해서
 * 시스템 호출 횟수를 줄이고 성능을 향상시키기 위한 구조입니다.
 * */