package programmers.lv2.프로세스;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue 전체 탐색
 * 1. 큐에서 하나 꺼낸다
 * 2. 더 높은 priority가 있으면 다시 넣는다
 * 3. 없으면 실행한다
 * 4. 내가 찾는 프로세스면 종료
 *
 * 시간 복잡도: O(N²)
 * */
public class Solution {
	// return "location에 있는 프로세스가 몇 번째로 실행되는지"
	public int solution(int[] priorities, int location) {
		// 우선순위 숫자가 높은 게 우선순위 높은 것
		// [1,1,9,1,1,1] 0 5
		Queue<int[]> queue = new LinkedList<>();
		for (int i = 0; i < priorities.length; i++) {
			queue.add(new int[]{priorities[i], i}); // [priroity, index]
		}

		int count = 0;
		while (!queue.isEmpty()) {
			int[] current = queue.poll();	// 현재 큐 꺼내기 [1,0] [1,1]
			boolean hasHigher = false;

			for (int[] q : queue) { // [1,1] // [9,2]
				if (q[0] > current[0]) {
					hasHigher = true;
					break;
				}
			}

			if (hasHigher) {
				queue.add(current);
			} else {
				count++;

				if (current[1] == location) {
					return count;
				}
			}
		}
		return count;
	}
}
