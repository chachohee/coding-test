package goorm.basic.원접기;

import java.io.*;
import java.util.*;

class Main {
	public static void main(String[] args) throws Exception {
		// 현의 교점 정리:
		// 원 내부의 한 점에서 두 현이 교차할 때
		// |AM| x |MB| = |CM| x |DM|
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int AM = Integer.parseInt(st.nextToken());
		int CM = Integer.parseInt(st.nextToken());
		int BM = Integer.parseInt(st.nextToken());

		// 기약분수로 만들기
		// 최대공약수 g = gcd(a*b, c)
		// 기약분수:
		// 분자 = (a*b) / g
		// 분모 = c / g
		int numerator = AM * BM; // 분자
		int denominator = CM; // 분모

		// 최대공약수로 약분
		int g = gcd(numerator, denominator);
		numerator /= g;
		denominator /= g;

		System.out.println(numerator + " " + denominator);
	}

	public static int gcd(int a, int b) {
		if (b == 0) return a;
		return gcd(b, a % b);
	}
}
