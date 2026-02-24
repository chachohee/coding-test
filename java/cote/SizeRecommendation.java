package cote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 🧩 문제: 사이즈 추천 시스템
 *
 * 의류 쇼핑몰에서는 브랜드별로 서로 다른 사이즈 기준을 사용한다.
 * 고객의 신체 정보를 기반으로, 고객이 원하는 브랜드에서 적절한 사이즈를 추천하는 프로그램을 작성하라.
 *
 * ------------------------------------------------------------
 * ✅ 규칙
 *
 * 각 브랜드는 여러 개의 사이즈 정보를 가진다.
 * 각 사이즈는 다음 범위를 가진다:
 *
 * - 키 최소 ~ 키 최대
 * - 가슴둘레 최소 ~ 가슴둘레 최대
 * - 허리둘레 최소 ~ 허리둘레 최대
 *
 * 고객은 다음 정보를 입력한다:
 * - 원하는 브랜드명
 * - 키
 * - 가슴둘레
 * - 허리둘레
 *
 * ------------------------------------------------------------
 * ✅ 출력 규칙 (고객 1명당 1줄 출력)
 *
 * 1. 고객이 원하는 브랜드가 존재하지 않으면
 *    → UNKNOWN
 *
 * 2. 브랜드는 존재하지만 맞는 사이즈가 없으면
 *    → 브랜드명,MISMATCH
 *
 * 3. 맞는 사이즈가 존재하면
 *    → 브랜드명,사이즈명
 *
 * ※ 여러 사이즈가 조건을 만족할 경우,
 *    입력된 순서대로 가장 먼저 나온 사이즈를 추천한다.
 *    (사이즈를 작은 순서대로 입력하면 자동으로 가장 작은 사이즈가 선택된다.)
 *
 * ------------------------------------------------------------
 * ✅ 입력 형식
 *
 * B,C           // 브랜드 개수, 고객 수
 *
 * 브랜드명,사이즈개수
 * 사이즈명,키최소,키최대,가슴최소,가슴최대,허리최소,허리최대
 * ...
 * (브랜드 수 만큼 반복)
 *
 * 브랜드명,키,가슴둘레,허리둘레
 * ...
 * (고객 수 만큼 반복)
 *
 * ------------------------------------------------------------
 * ✅ 입력 예시
 *
 * 2,5
 * Nike,2
 * S,160,175,80,95,24,30
 * M,165,180,85,100,26,32
 * Adidas,2
 * SM,160,170,80,90,24,26
 * XL,175,190,100,105,32,40
 * Musinsa,163,85,24
 * Nike,170,90,28
 * Adidas,163,85,24
 * Adidas,180,85,24
 * Nike,173,85,27
 *
 * ------------------------------------------------------------
 * ✅ 출력 예시
 *
 * Musinsa,UNKNOWN
 * Nike,S
 * Adidas,SM
 * Adidas,MISMATCH
 * Nike,S
 *
 * ------------------------------------------------------------
 * ✅ 출력 설명
 *
 * 1. Musinsa,163,85,24 → 브랜드 없음 → Musinsa,UNKNOWN
 * 2. Nike,170,90,28 → S와 M 모두 매칭, 입력 순서상 첫 번째인 S 선택 → Nike,S
 * 3. Adidas,163,85,24 → SM만 매칭 → Adidas,SM
 * 4. Adidas,180,85,24 → 매칭 없음 → Adidas,MISMATCH
 * 5. Nike,173,85,27 → S만 매칭 → Nike,S
 *
 * ------------------------------------------------------------
 * ✅ 핵심 자료구조
 *
 * Map<브랜드명, List<SizeInfo>>
 *
 * 브랜드마다 여러 사이즈를 가지므로 List로 관리한다.
 * 고객 요청 시 해당 브랜드의 사이즈 리스트를 순회하며 조건에 맞는 사이즈를 찾는다.
 */
public class SizeRecommendation {

	static class SizeInfo {
		String sizeName;
		int heightMin, heightMax;
		int chestMin, chestMax;
		int waistMin, waistMax;

		SizeInfo(String sizeName, int heightMin, int heightMax,
			int chestMin, int chestMax, int waistMin, int waistMax) {
			this.sizeName = sizeName;
			this.heightMin = heightMin;
			this.heightMax = heightMax;
			this.chestMin = chestMin;
			this.chestMax = chestMax;
			this.waistMin = waistMin;
			this.waistMax = waistMax;
		}

		boolean matches(int height, int chest, int waist) {
			return height >= heightMin && height <= heightMax &&
				chest >= chestMin && chest <= chestMax &&
				waist >= waistMin && waist <= waistMax;
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 브랜드 개수, 고객 수 입력
		String[] firstLine = br.readLine().split(",");
		int brandCount = Integer.parseInt(firstLine[0]);
		int customerCount = Integer.parseInt(firstLine[1]);

		// 브랜드별 사이즈 정보 저장
		Map<String, List<SizeInfo>> brandSizes = new HashMap<>();

		// 브랜드 정보 입력
		for (int i = 0; i <brandCount; i++) {
			String[] brandInfo = br.readLine().split(",");
			String brandName = brandInfo[0];
			int sizeCount = Integer.parseInt(brandInfo[1]);

			List<SizeInfo> sizes = new ArrayList<>();

			// 각 브랜드의 사이즈 정보 입력
			for (int j = 0; j < sizeCount; j++) {
				String[] sizeData = br.readLine().split(",");
				String sizeName = sizeData[0];
				int heightMin = Integer.parseInt(sizeData[1]);
				int heightMax = Integer.parseInt(sizeData[2]);
				int chestMin = Integer.parseInt(sizeData[3]);
				int chestMax = Integer.parseInt(sizeData[4]);
				int waistMin = Integer.parseInt(sizeData[5]);
				int waistMax = Integer.parseInt(sizeData[6]);

				sizes.add(new SizeInfo(sizeName, heightMin, heightMax,
					chestMin, chestMax, waistMin, waistMax));
			}

			brandSizes.put(brandName, sizes);
		}

		// 고객별 사이즈 추천
		for (int i = 0; i < customerCount; i++) {
			String[] customerInfo = br.readLine().split(",");
			String requestedBrand = customerInfo[0];
			int height = Integer.parseInt(customerInfo[1]);
			int chest = Integer.parseInt(customerInfo[2]);
			int waist = Integer.parseInt(customerInfo[3]);

			// 브랜드가 없는 경우
			if (!brandSizes.containsKey(requestedBrand)) {
				System.out.println(requestedBrand + ",UNKNOWN");
				continue;
			}

			// 맞는 사이즈 찾기
			List<SizeInfo> sizes = brandSizes.get(requestedBrand);
			String recommendedSize = null;

			// 여러 사이즈가 매칭되면 입력 순서대로 첫 번째 사이즈 선택
			// (사이즈를 작은 순서대로 입력했다면 자동으로 가장 작은 사이즈가 선택됨)
			for (SizeInfo size : sizes) {
				if (size.matches(height, chest, waist)) {
					recommendedSize = size.sizeName;
					break; // 첫 번째 매칭 사이즈를 찾으면 즉시 종료
				}
			}

			// 결과 출력
			if (recommendedSize != null) {
				System.out.println(requestedBrand + "," + recommendedSize);
			} else {
				System.out.println(requestedBrand + ",MISMATCH");
			}
		}
	}
}
