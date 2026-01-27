package programmers.lv2._004_JadenCase;

class Solution {
	public String solution(String s) {
		StringBuilder answer = new StringBuilder();
		boolean isFirstChar = true; // 단어의 첫 글자인지 체크

		for (char c : s.toCharArray()) {
			if (c == ' ') {
				answer.append(c);
				isFirstChar = true; // 공백이면 아직 첫 글자 안 나옴
			} else {
				// 첫 글자이면
				if (isFirstChar) {
					answer.append(Character.toUpperCase(c));
					isFirstChar = false; // 다음에 오는 건 첫 글자 아님
				} else {
					answer.append(Character.toLowerCase(c));
				}
			}
		}
		return answer.toString();
	}

	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.solution("3people unFollowed me")); // 3people Unfollowed Me
		System.out.println(sol.solution("for the last week")); // For The Last Week
	}
}
