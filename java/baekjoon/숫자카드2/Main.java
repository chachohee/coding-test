package baekjoon.숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * HashMap 사용
 * 시간 복잡도: O(N + M)
 * */
public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); // 상근이 숫자 카드 개수
		String[] nStrArr = br.readLine().split(" "); // 상근이 숫자 카드 정수
		int[] nIntArr = Arrays.stream(nStrArr).mapToInt(Integer::parseInt).toArray();
		int M = Integer.parseInt(br.readLine()); // 주어진 정수 M개
		String[] mStrArr = br.readLine().split(" "); // 정수 배열
		int[] mIntArr = Arrays.stream(mStrArr).mapToInt(Integer::parseInt).toArray();

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < N; i++) {
			int num = nIntArr[i];
			map.put(num, map.getOrDefault(num, 0) + 1);
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			int num = mIntArr[i];
			sb.append(map.getOrDefault(num, 0)).append(" ");
		}

		System.out.println(sb);
	}

	// 시간 초과 난 코드 --> 이중 for문 (O(N x M))
	// public static void main(String[] args) throws IOException {
	// 	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 	int N = Integer.parseInt(br.readLine()); // 상근이 숫자 카드 개수
	// 	String[] nStrArr = br.readLine().split(" "); // 상근이 숫자 카드 정수
	// 	int[] nIntArr = Arrays.stream(nStrArr).mapToInt(Integer::parseInt).toArray();
	// 	int M = Integer.parseInt(br.readLine()); // 주어진 정수 M개
	// 	String[] mStrArr = br.readLine().split(" "); // 정수 배열
	// 	int[] mIntArr = Arrays.stream(mStrArr).mapToInt(Integer::parseInt).toArray();
	//
	// 	StringBuilder sb = new StringBuilder();
	// 	for (int i = 0; i < M; i++) {
	// 		int num = mIntArr[i];
	// 		int count = 0;
	// 		for (int j = 0; j < N; j++) {
	// 			if (num != nIntArr[j]) continue;
	// 			count++;
	// 		}
	// 		sb.append(count).append(" ");
	// 	}
	//
	// 	System.out.println(sb);
	// }
}
