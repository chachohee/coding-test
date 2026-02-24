package goorm.basic.딱지놀이;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		// 첫째 줄: 라운드 수 N
		// 다음 2N개의 줄: 각 라운드의 A, B 딱지상태
		// 딱지 상태:
		// - 두 줄로 이루어짐
		// - 첫 번째 줄: 어린이 A의 딱지 (그림 개수 / 각 그림의 종류)
		// - 두 번째 줄: 어린이 B의 딱지 (그림 개수 / 각 그림의 종류)

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		// 각 라운드마다 A가 승리냐 B가 승리냐
		for (int round = 0; round < N; round++) {
			// A의 딱지
			int[] a = readDdakji(br);
			// B의 딱지
			int[] b = readDdakji(br);

			// 비교
			int result = compare(a, b);

			// 최종 결과
			if (result > 0) {
				System.out.println("A");
			} else if (result < 0) {
				System.out.println("B");
			} else {
				System.out.println("D");
			}
		}

	}

	// 딱지 정보 읽기
	static int[] readDdakji(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		int count = Integer.parseInt(st.nextToken()); // 그림 모양 개수

		int[] ddakji = new int[4]; // [별, 동그라미, 네모, 세모]

		for (int i = 0; i < count; i++) {
			int type = Integer.parseInt(st.nextToken());
			// 4=별, 3=동그라미, 2=네모, 1=세모
			ddakji[4 - type]++; // 인덱스 변환
		}

		return ddakji;
	}

	// 딱지 비교
	// a > b: 양수, a < b: 음수, 같음: 0
	static int compare(int[] a, int[] b) {
		// 우선순위: 별(0) > 동그라미(1) > 네모(2) > 세모(3)
		for (int i = 0; i < 4; i++) {
			if (a[i] != b[i]) {
				return a[i] - b[i];
			}
		}
		return 0; // 모두 같으면 무승부
	}
}
