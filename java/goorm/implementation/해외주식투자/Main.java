package goorm.implementation.해외주식투자;
/*
[문제 설명]

구름이는 여러 회사의 주식을 보유하고 있다.
총 N개의 회사가 있으며, 각 회사는 1번부터 N번까지 번호가 붙어 있다.

각 회사 i에 대해 다음 정보가 주어진다.
- V_i : 보유한 주식의 개수 (실수)
- W_i : 주식 1개의 가격 (정수)

각 회사의 평가 금액은 다음과 같이 계산된다.
- 평가 금액 = V_i × W_i

단, 평가 금액은 소수점 둘째 자리에서 절삭하여
소수점 첫째 자리까지만 유지한다.

구름이는 다음 기준에 따라 주식을 판매하려고 한다.

[판매 순서 규칙]
1. 평가 금액이 큰 회사부터 판매한다.
2. 평가 금액이 같다면 회사 번호가 작은 것부터 판매한다.

모든 회사를 판매하는 순서를 회사 번호 기준으로 출력하라.


[입력]
- 첫째 줄에 회사의 개수 N이 주어진다.
- 다음 N개의 줄에 각 회사의 정보 V_i, W_i가 공백으로 주어진다.

[출력]
- 판매 순서대로 회사 번호를 공백으로 구분하여 출력한다.

[제한 사항]
- 1 ≤ N ≤ 1,000,000
- 0 < V_i (실수)
- 1 ≤ W_i ≤ 1,000,000
- 입력 값은 모두 유효하다.

[예제]
입력:
3
1.8 3
1.3 4
2 4

출력:
3 1 2


[풀이 아이디어]
1. 각 회사마다
   - 회사 번호
   - 평가 금액(V_i × W_i)을 소수점 첫째 자리까지만 유지한 값
   을 하나의 객체로 저장한다.
2. 소수 계산의 오차를 피하기 위해 평가 금액에 10을 곱한 후 정수(long)로 저장한다.
3. 정렬 기준은 다음과 같다.
   - 1순위: 평가 금액 내림차순
   - 2순위: 회사 번호 오름차순
4. 정렬된 순서대로 회사 번호를 출력한다.
*/

import java.io.*;
import java.util.*;
class Main {

	static class Stock {
		int idx; // 회사번호
		long value; // 평가 금액 * 10 (절삭)

		Stock(int idx, long value) {
			this.idx = idx;
			this.value = value;
		}
	}

	public static void main(String[] args) throws Exception {
		// N개의 주식 구매
		// Vi: 보유한 주식의 개수
		// Wi: 주식 1개 가격 (정수)
		// 평가 금액: Vi X Wi

		// 판매 순서
		// 1. 평가 금액이 큰 회사부터
		// 2. 평가 금액이 같다면 -> 회사 번호가 작은 것부터
		// 3. 평가 금액은 소수점 둘째 자리에서 절삭 -> 소수점 첫째 자리까지만 유지

		// 1. 각 회사마다
		// - 회사 번호
		// - 평가 금액 (절삭 적용)
		// 2. 이걸 하나의 객체(또는 배열)에 담는다
		// 3. 정렬
		// - 1순위: 평가 금액 내림차순
		// - 2순위: 회사 번호 오름차순
		// 4. 정렬된 순서대로 회사 번호 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		Stock[] arr = new Stock[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			double v = Double.parseDouble(st.nextToken()); // 보유한 주식 개수
			int w = Integer.parseInt(st.nextToken()); // 주식 가격

			long value = (long) (v * w * 10); // 소수 둘째 자리에서 절삭
			arr[i] = new Stock(i + 1, value);
		}

		Arrays.sort(arr, (a, b) -> {
			if (a.value != b.value) {
				return Long.compare(b.value, a.value); // 내림차순 정렬
			}
			return Integer.compare(a.idx, b.idx); // 오름차순 정렬
		});

		// Arrays.sort(arr, new Comparator<Stock>() {
		// 	public int compare(Stock a, Stock b) {
		// 		if (a.value != b.value) {
		// 			return Long.compare(b.value, a.value);
		// 		}
		// 		return Integer.compare(a.idx, b.idx);
		// 	}
		// });

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			sb.append(arr[i].idx);
			if (i < N - 1) sb.append(" ");
		}

		System.out.print(sb);
	}
}
