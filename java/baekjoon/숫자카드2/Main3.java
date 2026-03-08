package baekjoon.숫자카드2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * char 단위 파싱 + HashMap
 * 시간복잡도: O(N + M)
 * 카드 저장: O(N)
 * 조회: O(M)
 */
public class Main3 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());

		// 초기 용량 설정 → rehash 방지
		Map<Integer, Integer> map = new HashMap<>(N * 2);

		parseAndStore(br.readLine(), map);

		int M = Integer.parseInt(br.readLine());

		StringBuilder sb = new StringBuilder();

		parseAndQuery(br.readLine(), map, sb);

		System.out.println(sb);
	}

	// 카드 숫자 저장 --> 숫자를 만들다가 공백을 만나면 지금까지 만든 숫자를 저장한다
	static void parseAndStore(String line, Map<Integer, Integer> map) {

		int num = 0;
		boolean isNegative = false;

		for (int i = 0; i <= line.length(); i++) {
			// 마지막 숫자를 map에 넣기 위해 i <= line.length()
			if (i == line.length() || line.charAt(i) == ' ') {

				if (isNegative) num = -num;

				map.put(num, map.getOrDefault(num, 0) + 1);

				// 초기화
				num = 0;
				isNegative = false;

			} else if (line.charAt(i) == '-') {

				isNegative = true;

			} else {
				// 이전에 만든 숫자의 자릿수를 한 자리 왼쪽으로 밀기 위해 num * 10
				num = num * 10 + (line.charAt(i) - '0');

			}
		}
	}

	// 숫자 조회
	static void parseAndQuery(String line, Map<Integer, Integer> map, StringBuilder sb) {

		int num = 0;
		boolean isNegative = false;

		for (int i = 0; i <= line.length(); i++) {

			if (i == line.length() || line.charAt(i) == ' ') {

				if (isNegative) num = -num;

				sb.append(map.getOrDefault(num, 0)).append(" ");

				num = 0;
				isNegative = false;

			} else if (line.charAt(i) == '-') {

				isNegative = true;

			} else {

				num = num * 10 + (line.charAt(i) - '0');

			}
		}
	}
}