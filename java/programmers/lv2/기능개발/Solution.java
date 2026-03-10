package programmers.lv2.기능개발;

import java.util.ArrayList;
import java.util.List;

/**
 * ceil(올림 나눗셈) 공식
 * ceil(a / b) = (a + b - 1) / b
 * */
public class Solution {
	public int[] solution(int[] progresses, int[] speeds) {
		int n = progresses.length;
		int[] deployDays = new int[n];
		// 남은 작업일 수 = (100 - progress) / speed
		// a = 100 - progress
		// b = speed
		for (int i = 0; i < n; i++) {
			deployDays[i] = (100 - progresses[i] + speeds[i] - 1) / speeds[i];
		}

		List<Integer> result = new ArrayList<>();
		int currentDays = deployDays[0];
		int count = 1;
		for (int i = 1; i < n; i++) {
			if (deployDays[i] <= currentDays) {
				count++;
			} else {
				result.add(count);
				currentDays = deployDays[i];
				count = 1;
			}
		}
		result.add(count);

		return result.stream().mapToInt(Integer::intValue).toArray();
	}
}
