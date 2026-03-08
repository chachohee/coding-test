package baekjoon.숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 정렬 + 이분 탐색
 * 시간 복잡도: O(N log N + M log M)
 *
 * 정렬 후 특정 숫자의 시작 위치와 끝 위치를 찾는다.
 * 어떤 숫자 x의 개수 = upperBound(x) - lowerBound(x)
 * - lowerBound(x) -> x 이상이 처음 나오는 위치
 * - upperBound(x) -> x 초과가 처음 나오는 위치
 * */
public class Main2 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(input[i]);
		}
		Arrays.sort(arr);

		int M = Integer.parseInt(br.readLine());
		String[] targets = br.readLine().split(" ");

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < M; i++) {
			int target = Integer.parseInt(targets[i]);
			int count = upperBound(arr, target) - lowerBound(arr, target);
			sb.append(count).append(" ");
		}

		System.out.println(sb);
	}

	// target 이상이 처음 나오는 위치
	static int lowerBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

	// target 초과가 처음 나오는 위치
	static int upperBound(int[] arr, int target) {
		int left = 0;
		int right = arr.length;
		while (left < right) {
			int mid = (left + right) / 2;
			if (arr[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		return left;
	}

}
