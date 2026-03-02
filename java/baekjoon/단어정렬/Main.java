package baekjoon.단어정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Set<String> set = new HashSet<>();
		for (int i = 0; i < N; i++) {
			set.add(br.readLine());
		}
		String[] words = set.toArray(new String[0]);
		Arrays.sort(words, (a, b) -> {
			if (a.length() == b.length()) {
				return a.compareTo(b);
			}
			return a.length() - b.length();
		});
		StringBuilder sb = new StringBuilder();
		for (String s : words) {
			sb.append(s).append("\n");
		}
		System.out.println(sb);
	}
}
