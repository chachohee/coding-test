package programmers.lv2.프로세스;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 최대 priority 미리 정렬
 * 시간 복잡도: O(N log N)
 * */
public class Solution2 {
	public int solution(int[] priorities, int location) {

		// 큐 생성 -> [2,1,3,2]
		Queue<Integer> queue = new LinkedList<>();
		for (int p : priorities) {
			queue.add(p);
		}

		// 우선순위 정렬 -> [1,2,2,3]
		Arrays.sort(priorities);

		int count = 0;
		int index = priorities.length -1; // 가장 큰 우선순위 index -> 3

		while (!queue.isEmpty()) {

			int current = queue.poll(); // 2

			if (current == priorities[index]) { // 가장 큰 우선순위
				count++;
				index--;

				if (location == 0) {
					return count;
				}
			} else {
				queue.add(current);
			}

			location--; // 큐에서 하나 꺼내고 뒤로 다시 넣었기 때문에 -1

			if (location < 0) {
				location = queue.size() -1;
			}
		}

		return count;
	}
}
