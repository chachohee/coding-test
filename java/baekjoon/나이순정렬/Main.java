package baekjoon.나이순정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int memberNumbers = Integer.parseInt(br.readLine());
		String[][] members = new String[memberNumbers][2];
		for (int i = 0; i < memberNumbers; i++) {
			String[] memberLine = br.readLine().split(" ");
			members[i][0] = memberLine[0];
			members[i][1] = memberLine[1];
		}
		Arrays.sort(members, Comparator.comparingInt(a -> Integer.parseInt(a[0])));

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < memberNumbers; i++) {
			sb.append(members[i][0]).append(" ")
				.append(members[i][1]).append("\n");
		}
		System.out.print(sb);
	}
}
