package programmers.lv1.폰켓몬;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
	// 나의 풀이
	public int solution(int[] nums) {
		int total = nums.length;
		int pick = total / 2;
		Map<Integer, Integer> typeMap = new HashMap<>();
		for (int i = 0; i < total; i++) {
			typeMap.put(nums[i], typeMap.getOrDefault(nums[i], 0) + 1);
		}
		int type = typeMap.size();
		return Math.min(pick, type);
	}

	// GPT 풀이
	public int solution2(int[] nums) {
		Set<Integer> typeSet = new HashSet<>();
		for (int num : nums) {
			typeSet.add(num);
		}
		return Math.min(typeSet.size(), nums.length / 2);
	}
}


