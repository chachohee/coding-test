package programmers.lv1.신고결과받기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
	public int[] solution(String[] id_list, String[] report, int k) {

		// 중복 신고 제거
		Set<String> reportSet = new HashSet<>(Arrays.asList(report));

		// 신고당한 횟수 저장
		Map<String, Integer> reportedCount = new HashMap<>();
		for (String r : reportSet) {
			String[] parts = r.split(" ");
			String reportedUser = parts[1]; // 신고당한 유저

			reportedCount.put(reportedUser, reportedCount.getOrDefault(reportedUser, 0) + 1);
		}

		// 정지된 유저 판별
		Set<String> bannedUsers = new HashSet<>();
		for (String user : reportedCount.keySet()) {
			if (reportedCount.get(user) >= k) {
				bannedUsers.add(user);
			}
		}

		// 각 유저가 받을 메일 수 계산
		Map<String, Integer> mailCount = new HashMap<>();
		for (String r : reportSet) {
			String[] parts = r.split(" ");
			String reporter = parts[0];
			String reportedUser = parts[1];

			if (bannedUsers.contains(reportedUser)) {
				mailCount.put(reporter, mailCount.getOrDefault(reporter, 0) + 1);
			}
		}

		// 결과 생성
		int[] answer = new int[id_list.length];
		for (int i = 0; i < id_list.length; i++) {
			answer[i] = mailCount.getOrDefault(id_list[i], 0);
		}
		return answer;
	}

}
