package goorm.implementation.색종이;

/**
 *
 * [문제 설명 - 색종이]
 *
 * 가로, 세로의 크기가 각각 100인 정사각형 모양의 흰색 도화지가 있다.
 * 이 도화지 위에 가로, 세로의 크기가 각각 10인 정사각형 모양의 검은색 색종이를
 * 한 장 또는 여러 장 붙이려고 한다.
 *
 * 색종이는 (x, y) 좌표를 기준으로,
 * 왼쪽에서 x만큼, 아래에서 y만큼 떨어진 위치부터
 * 가로 10, 세로 10 영역을 덮는다.
 *
 * 색종이가 도화지를 벗어나는 경우는 없다.
 * 색종이들이 서로 겹칠 수 있으며,
 * 겹친 영역은 한 번만 계산한다.
 *
 * 각 테스트케이스마다
 * 색종이가 차지하는 전체 검은 영역의 넓이를 구하는 프로그램을 작성하시오.
 *
 * [입력]
 * - 첫 줄에 테스트케이스 수 T가 주어진다.
 * - 각 테스트케이스마다:
 *   - 첫 줄에 색종이 수 N (1 ≤ N ≤ 100)
 *   - 다음 N줄에 걸쳐 색종이의 위치 (x, y)가 주어진다.
 *
 * [출력]
 * - 각 테스트케이스마다 색종이가 차지하는 넓이를 한 줄에 출력한다.
 *
 * */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

class Main {

	public static void main(String[] args) throws IOException {
		// 첫 줄: 색종이 수 N (1 < N < 100)
		// 첫 번째: x좌표
		// 두 번째: y좌표
		// 색종이는 (x, y)부터 가로 10, 세로 10 영역을 덮음
		// 출력: 색종이가 차지하는 넓이를 한 줄에 공백 없는 정수로

		// 풀이 (2차원 배열로 칠하기)
		// 1. 도화지를 100 x 100 boolean 배열로 보고
		// 2. 색종이가 덮는 영역은 true --> 겹치는 부분은 자동으로 한 번만 처리됨
		// 3. true인 칸 수 세기

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();

		/*
			색종이는 (x, y)부터 시작해서
			- 가로: x ~ x + 9
			- 세로: y ~ y + 9

			즉, 배열에서는
			row = y ~ y + 9
			col = x ~ x + 9 범위를 true로 칠한다.

			이미 칠해진 칸은 다시 세지 않기 위해
			boolean 배열을 사용한다.
		*/
		for (int tc = 0; tc < T; tc++) { // 테스트케이스 수
			// 100 x 100 도화지 (false = 흰색, true = 검은색)
			boolean [][] paper = new boolean [100][100];
			int N = Integer.parseInt(br.readLine()); // 색종이 수
			int area = 0; // 넓이를 바로 계산

			// 각 색종이 처리
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()); // 왼쪽에서의 거리
				int y = Integer.parseInt(st.nextToken()); // 아래에서의 거리

				// 10 x 10 영역을 검은색으로 칠하기
				for (int row = y; row < y + 10; row++) {
					for (int col = x; col < x + 10; col++) {
						if (!paper[row][col]) { // 아직 안 칠해졌으면
							paper[row][col] = true;
							area++; // 넓이 증가
						}
					}
				}
			}

			sb.append(area).append("\n");
		}

		System.out.print(sb);
	}
}

/*
 * [좌표와 배열 인덱스 관계]
 *
 * 문제에서 주어지는 좌표:
 * - x : 왼쪽에서 떨어진 거리 (가로 방향)
 * - y : 아래에서 떨어진 거리 (세로 방향)
 *
 * 2차원 배열 접근 방식:
 * - paper[row][col]
 *   - row : 세로 방향 (위 → 아래)
 *   - col : 가로 방향 (왼 → 오)
 *
 * 따라서 좌표를 배열에 매핑하면:
 * - y → row
 * - x → col
 *
 * 즉,
 * paper[y][x] 형태로 접근하게 된다.
 *
 * */

/*
 * [도화지 & 좌표 시각화]
 *
 * 좌표계 (문제 기준)
 * y ↑
 * 9 | . . . . .
 * 8 | . . . . .
 * 7 | . . . . .
 * 6 | . . . . .
 * 5 | . . . . .
 *     0 1 2 3 4 → x
 *
 * 배열 구조 (컴퓨터 메모리 기준)
 * row 0 → [ ][ ][ ][ ][ ]
 * row 1 → [ ][ ][ ][ ][ ]
 * row 2 → [ ][ ][ ][ ][ ]
 * row 3 → [ ][ ][ ][ ][ ]
 * row 4 → [ ][ ][ ][ ][ ]
 *           col 0 1 2 3 4
 *
 * 좌표 (x, y)는 배열에서:
 * paper[y][x] 로 접근
 *
 * */