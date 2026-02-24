package cote;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * TreeMap 사용한 버전
 *
 * 🧩 문제: 스토어 픽업 예약 시스템
 *
 * 무신사 매장은 여러 상품에 대해 스토어 픽업 예약을 받는다.
 * 각 상품은 재고 수량과 픽업 가능 시간이 서로 다르다.
 *
 * 예약 요청은 분 단위(HH:MM)로 들어오지만,
 * 예약 현황은 시간 단위(HH:00)로 집계해야 한다.
 *
 * 또한 시간대별로 어떤 고객이 몇 개의 상품을 예약했는지도 함께 출력해야 한다.
 *
 * ------------------------------------------------------------
 *
 * ✅ 규칙
 *
 * 1. 매장 영업시간 내에서만 예약 및 취소가 가능하다.
 * 2. 각 상품마다 픽업 가능 시간이 다르다.
 * 3. 예약 시간 14:30 → 14:00 시간대로 변환하여 집계한다.
 * 4. 예약(RESERVE) 시 해당 상품의 재고는 수량만큼 감소한다.
 * 5. 취소(CANCEL) 시 해당 상품의 재고는 수량만큼 증가한다.
 * 6. 같은 고객이 같은 시간대, 같은 상품을 다시 예약하면
 *    이름을 추가하지 않고 기존 수량에 더한다.
 * 7. 취소 시 예약 수량이 감소하며, 0이 되면 해당 고객은 목록에서 제거된다.
 * 8. 재고가 부족하면 예약할 수 없다.
 * 9. 예약자 이름은 해당 시간대에 예약이 처리된 순서대로 출력한다.
 * 10. 시간대별 출력 시 상품 열의 순서는 입력된 상품 순서를 따른다.
 * 11. 아래 조건 중 하나라도 만족하지 않으면 해당 요청은 실패 처리되며,
 *     재고 및 예약 현황에는 아무 영향도 주지 않는다.
 *
 * ------------------------------------------------------------
 *
 * ✅ 입력 형식
 *
 * 상품 개수,영업 시작 시간,영업 종료 시간
 *
 * 상품명,재고 수량,픽업 가능 시작 시간,픽업 가능 종료 시간
 * ...
 * (상품 개수만큼 입력)
 *
 * 예약 요청 수
 *
 * 시간,고객명,상품명,수량,타입
 * ...
 * (예약 요청 수만큼 입력)
 *
 * 타입
 * - RESERVE : 예약
 * - CANCEL  : 취소
 *
 * ------------------------------------------------------------
 *
 * ✅ 처리 규칙
 *
 * [예약 RESERVE]
 *
 * 예약이 성공하려면 다음 조건을 모두 만족해야 한다.
 *
 * 1. 상품이 존재해야 한다.
 * 2. 매장 영업시간 내여야 한다.
 * 3. 해당 상품의 픽업 가능 시간 내여야 한다.
 * 4. 재고가 충분해야 한다.
 *
 * ✔ 성공 시
 *   - 재고 감소
 *   - 해당 시간대에 고객 및 수량 기록
 *
 * ✔ 실패 시
 *   - 실패 로그에 기록
 *   - 재고 및 예약 현황에는 영향 없음
 *
 * ------------------------------------------------------------
 *
 * [취소 CANCEL]
 *
 * 취소가 성공하려면 다음 조건을 만족해야 한다.
 *
 * 1. 해당 시간대에 해당 고객의 예약 내역이 존재해야 한다.
 * 2. 예약 수량 이상으로 취소할 수 없다.
 *
 * ✔ 성공 시
 *   - 재고 복구
 *   - 수량 감소, 0이면 목록에서 제거
 *
 * ✔ 실패 시
 *   - 실패 로그에 기록
 *
 * ------------------------------------------------------------
 *
 * [시간 처리 방식]
 *
 * HH:MM 형식의 시간은 HH:00 으로 변환하여 집계한다.
 *
 * 예)
 * 14:30 → 14:00
 * 10:15 → 10:00
 *
 * ------------------------------------------------------------
 *
 * ✅ 출력 형식
 *
 * 1️⃣ 시간대별 상품 예약 현황
 *
 * 시간    [상품1] [상품2] ... 예약자목록
 *
 * 각 시간대에 대해
 * - 상품별 총 예약 수량
 * - 예약자 이름과 수량을 함께 출력
 *
 * 예)
 * 10:00    2    2    강감찬(1) 김철수(1) 홍길동(2)
 *
 * ------------------------------------------------------------
 *
 * 2️⃣ 상품별 남은 재고
 *
 * 상품명,재고수량
 *
 * ------------------------------------------------------------
 *
 * 3️⃣ 예약 실패 내역
 *
 * 시간,고객명,상품명,실패사유
 *
 * 실패 사유:
 * - UNKNOWN_PRODUCT
 * - OUT_OF_BUSINESS_HOUR
 * - OUT_OF_PICKUP_TIME
 * - OUT_OF_STOCK
 * - INVALID_CANCEL
 *
 * ------------------------------------------------------------
 *
 * ✅ 입력 예시
 *
 * 2,09:00,18:00
 * 티셔츠,10,10:00,17:00
 * 후드,5,12:00,18:00
 * 10
 * 10:10,강감찬,티셔츠,1,RESERVE
 * 10:20,홍길동,후드,2,RESERVE
 * 10:30,김철수,티셔츠,1,RESERVE
 * 11:10,박민수,티셔츠,1,RESERVE
 * 14:10,이영희,티셔츠,1,RESERVE
 * 15:10,최민수,티셔츠,1,RESERVE
 * 09:10,김철수,티셔츠,1,RESERVE
 * 10:15,홍길동,신발,1,RESERVE
 * 11:30,박민수,후드,10,RESERVE
 * 15:10,최민수,티셔츠,1,CANCEL
 *
 * ------------------------------------------------------------
 *
 * ✅ 출력 예시
 * [시간대별 상품 예약 현황]
 * 시간	티셔츠	후드	예약자
 * 10:00	2	0	강감찬(1)김철수(1)
 * 11:00	1	0	박민수(1)
 * 14:00	1	0	이영희(1)
 * 15:00	0	0
 *
 * [상품별 남은 재고]
 * 티셔츠,6
 * 후드,5
 *
 * [예약 실패 내역]
 * 10:20,홍길동,후드,OUT_OF_PICKUP_TIME
 * 09:10,김철수,티셔츠,OUT_OF_PICKUP_TIME
 * 10:15,홍길동,신발,UNKNOWN_PRODUCT
 * 11:30,박민수,후드,OUT_OF_PICKUP_TIME
 */
public class StorePickup2 {

	static class Product {
		String name;
		int stock;
		String startTime, endTime;

		Product(String name, int stock, String startTime, String endTime) {
			this.name = name;
			this.stock = stock;
			this.startTime = startTime;
			this.endTime = endTime;
		}
	}

	static Map<String, Product> products = new LinkedHashMap<>();
	/*
	시간대(timeZone)
		└── 상품명(productName)
				└── 고객(customer) → 수량(Integer)
	*/
	static Map<String, Map<String, Map<String, Integer>>> schedule = new TreeMap<>();
	static List<String> failLogs = new ArrayList<>();

	// 시 단위로
	static String toSlot(String time) {
		return time.substring(0,2) + ":00";
	}

	// 픽업 가능 시간대에 있는지
	static boolean inRange(String t, String start, String end) {
		return t.compareTo(start) >= 0 && t.compareTo(end) <= 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		// 상품개수,오픈시간,마감시간
		String[] firstLine = br.readLine().split(",");
		int productCount = Integer.parseInt(firstLine[0]);
		String openHour = firstLine[1];
		String closeHour = firstLine[2];

		// 상품 정보 (상품개수만큼 입력)
		// 상품명,재고수,픽업시작시간,픽업마감시간
		for (int i = 0; i < productCount; i++) {
			String[] p = br.readLine().split(",");
			products.put(p[0],
				new Product(
					p[0],
					Integer.parseInt(p[1]),
					p[2],
					p[3]));
		}

		// 예약 수
		int reserveCount = Integer.parseInt(br.readLine());

		// 예약 처리
		// 시간대,예약자명,상품명,수량,타입(RESERVE.CANCEL)
		for (int i = 0; i <reserveCount; i++) {
			String[] r = br.readLine().split(",");
			String time = r[0];
			String slot = toSlot(time);
			String user = r[1];
			String pname = r[2];
			int quantity = Integer.parseInt(r[3]);
			String type = r[4];

			if (!products.containsKey(pname)) {
				failLogs.add(time + "," + user + "," + pname + ",UNKNOWN_PRODUCT");
				continue;
			}

			// 영업 시간 내에 있는지
			if (!inRange(time, openHour, closeHour)) {
				failLogs.add(time + "," + user + "," + pname + ",OUT_OF_BUSINESS_HOUR");
				continue;
			}

			Product p = products.get(pname);

			// 해당 상품의 픽업 시간 내에 있는지
			if (!inRange(time, p.startTime, p.endTime)) {
				failLogs.add(time + "," + user + "," + pname + ",OUT_OF_PICKUP_TIME");
				continue;
			}

			// slot: 예약 시간대
			schedule.putIfAbsent(slot, new HashMap<>());
			schedule.get(slot).putIfAbsent(pname, new LinkedHashMap<>());
			// 예약 시간대의 상품별 고객 정보
			Map<String, Integer> userMap = schedule.get(slot).get(pname);

			if (type.equals("RESERVE")) {
				if (p.stock < quantity) {
					failLogs.add(time + "," + user + "," + pname + ",OUT_OF_STOCK");
					continue;
				}
				p.stock -= quantity;
				userMap.put(user, userMap.getOrDefault(user, 0) + quantity);
			} else { // CANCEL
				if (!userMap.containsKey(user) || userMap.get(user) < quantity) {
					failLogs.add(time + "," + user + "," + pname + ",INVALID_CANCEL");
					continue;
				}
				userMap.put(user, userMap.get(user) - quantity);
				p.stock += quantity;
				if (userMap.get(user) == 0) {
					userMap.remove(user);
				}
			}
		}

		// 출력 1: 시간대별 상품 예약 현황
		System.out.println("[시간대별 상품 예약 현황]");

		List<String> productNames = new ArrayList<>(products.keySet());
		System.out.println("시간\t" + String.join("\t", productNames) + "\t예약자");

		for (String slot : schedule.keySet()) {
			Map<String, Map<String, Integer>> productMap = schedule.get(slot);

			Map<String, Integer> productSum = new LinkedHashMap<>();
			for (String pname : productNames) {
				int sum = 0;
				if (productMap.containsKey(pname)) {
					for (int q : productMap.get(pname).values()) {
						sum += q;
					}
				}
				productSum.put(pname, sum);
			}

			StringBuilder users = new StringBuilder();
			for (String pname : productMap.keySet()) {
				for (Map.Entry<String, Integer> e : productMap.get(pname).entrySet()) {
					users.append(e.getKey())
						.append("(")
						.append(e.getValue())
						.append(")");
				}
			}

			System.out.print(slot + "\t");
			for (String pname : productNames) {
				System.out.print(productSum.get(pname) + "\t");
			}
			System.out.println(users.toString().trim());

		}

		// 출력 2: 상품별 남은 재고
		System.out.println("\n[상품별 남은 재고]");
		for (Product p : products.values()) {
			System.out.println(p.name + "," + p.stock);
		}

		// 출력 3: 예약 실패 내역
		System.out.println("\n[예약 실패 내역]");
		for (String log : failLogs) {
			System.out.println(log);
		}
	}

}
