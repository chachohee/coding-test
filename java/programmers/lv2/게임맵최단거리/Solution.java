package programmers.lv2.게임맵최단거리;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {

	// 이동 방향 (상하좌우)
	// dx: 행 이동
	// dy: 열 이동
	// (1,0)  → 아래
	// (-1,0) → 위
	// (0,1)  → 오른쪽
	// (0,-1) → 왼쪽
	int[] dx = {1,-1,0,0};
	int[] dy = {0,0,1,-1};

	public int solution(int[][] maps) {

		// 맵의 세로 길이
		int n = maps.length;

		// 맵의 가로 길이
		int m = maps[0].length;

		// BFS 탐색을 위한 Queue
		// 좌표 (x,y)를 저장
		Queue<int[]> queue = new LinkedList<>();

		// 시작 위치 (0,0) 큐에 추가
		queue.add(new int[]{0,0});

		// 큐가 빌 때까지 반복 (BFS 탐색)
		while(!queue.isEmpty()) {

			// 현재 위치 꺼내기
			int[] current = queue.poll();

			int x = current[0]; // 현재 행
			int y = current[1]; // 현재 열

			// 상하좌우 4방향 탐색
			for (int i = 0; i < 4; i++) {

				// 다음 이동할 좌표
				int nx = x + dx[i];
				int ny = y + dy[i];

				// 맵 범위를 벗어나지 않는 경우만 탐색
				if (nx < n && ny < m && nx >= 0 && ny >= 0) {

					// 해당 위치가 길(1)인 경우 이동 가능
					if (maps[nx][ny] == 1) {

						// 현재 위치 거리 + 1
						// BFS이므로 처음 방문이 최단 거리
						maps[nx][ny] = maps[x][y] + 1;	// maps[x][y] 는 항상 "현재 칸까지 오는 최단 거리"

						// 다음 탐색을 위해 큐에 추가
						queue.add(new int[]{nx, ny});
					}
				}
			}
		}

		// 목적지에 도달하지 못한 경우
		// 값이 여전히 1이면 방문하지 못했다는 의미 (아직 방문 안한 길만 이동)
		if (maps[n-1][m-1] == 1) {
			return -1;
		}

		// 도착 위치에 기록된 값이 최단 거리
		return maps[n-1][m-1];
	}
}

/*
에시)
	1 0 1		 1 0 5
	1 1 1	->   2 3 4
	0 1 1		 0 4 5

* */