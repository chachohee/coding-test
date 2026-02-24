package goorm.implementation.시험성적1;

import java.io.*;
import java.util.*;
class Main {
	public static void main(String[] args) throws Exception {
		// 각 학생 i에 대해
		// A과목 등수도 더 낮고(숫자 큼), B과목 등수도 더 낮은 학생의 수를 구하라.
		// (1,1)보다 A,B 모두 큰 학생
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] A = new int[N];
		int[] B = new int[N];

		StringTokenizer stA = new StringTokenizer(br.readLine());
		StringTokenizer stB = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(stA.nextToken());
			B[i] = Integer.parseInt(stB.nextToken());
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < N; i++) {
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (A[j] > A[i] && B[j] > B[i]) {
					count++;
				}
			}
			sb.append(count);
			if (i < N - 1) sb.append(" ");
		}

		System.out.println(sb);
	}
}
