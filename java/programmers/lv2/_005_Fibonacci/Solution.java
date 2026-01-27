package programmers.lv2._005_Fibonacci;

class Solution {
	public int solution(int n) {
		// n이 최대 100,000이므로 재귀는 스택 오버플로우 발생 --> 반복문으로 구현 (동적 프로그래밍)

		if (n == 0) return 0; // 0 % 1234567 = 0
		if (n == 1) return 1; // 1 % 1234567 = 1

		int prev2 = 0;
		int prev1 = 1;
		int current = 0;

		for (int i = 2; i <= n; i++) {
			// 매번 1234567로 나눈 나머지를 저장 (오버플로우 방지)
			// (a + b) % m = ((a % m) + (b % m)) 성질 이용
			current = (prev1 + prev2) % 1234567;
			prev2 = prev1;
			prev1 = current;
		}
		return current;
	}
}

/*
public int fibonacci(int n) {
    if (n == 0) return 0;
    if (n == 1) return 1;
    return fibonacci(n-1) + fibonacci(n-2);  // 재귀 호출
}

**재귀로 피보나치를 구현하면 스택 오버플로우가 발생하는 이유:**

1. 호출 깊이가 너무 깊어짐
   - fibonacci(100000)을 호출하면
   - fibonacci(99999) → fibonacci(99998) → ... → fibonacci(0)까지
   - 최대 100,000번의 함수 호출이 스택에 쌓임

2. Java의 스택 메모리 제한
   - 각 함수 호출마다 스택 프레임(지역변수, 반환주소 등)이 메모리에 저장됨
   - Java 기본 스택 크기는 보통 1MB 정도
   - 깊이가 수만 번이면 스택 메모리 초과 → `StackOverflowError` 발생

3. 예시:
	fibonacci(5) 호출
	  → fibonacci(4) 호출
		→ fibonacci(3) 호출
		  → fibonacci(2) 호출
			→ fibonacci(1) 호출 (스택에 5개 쌓임)

	n=100,000이면 이런 호출이 10만 번 중첩되므로 스택이 터짐
	해결책: 반복문을 사용하면 스택에 1개의 함수만 있으므로 안전하다
*/