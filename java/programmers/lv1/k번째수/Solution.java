package programmers.lv1.k번째수;

import java.util.Arrays;

public class Solution {
	public int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			int[] command = commands[i];
			int[] cutArray = Arrays.copyOfRange(array, command[0] - 1, command[1]);
			Arrays.sort(cutArray);
			answer[i] = cutArray[command[2] - 1];
		}

		return answer;
	}
}
