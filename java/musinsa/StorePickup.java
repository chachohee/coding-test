package musinsa;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * HashMap + Sort 사용한 버전
 */
public class StorePickup {

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

	// TreeMap → HashMap
	static Map<String, Map<String, Map<String, Integer>>> schedule = new HashMap<>();

	static List<String> failLogs = new ArrayList<>();

	static String toSlot(String time) {
		return time.substring(0, 2) + ":00";
	}

	static boolean inRange(String t, String start, String end) {
		return t.compareTo(start) >= 0 && t.compareTo(end) <= 0;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] first = br.readLine().split(",");
		int productCount = Integer.parseInt(first[0]);
		String open = first[1];
		String close = first[2];

		for (int i = 0; i < productCount; i++) {
			String[] p = br.readLine().split(",");
			products.put(p[0], new Product(p[0],
				Integer.parseInt(p[1]),
				p[2],
				p[3]));
		}

		int n = Integer.parseInt(br.readLine());

		for (int i = 0; i < n; i++) {
			String[] r = br.readLine().split(",");
			String time = r[0];
			String slot = toSlot(time);
			String user = r[1];
			String pname = r[2];
			int qty = Integer.parseInt(r[3]);
			String type = r[4];

			if (!products.containsKey(pname)) {
				failLogs.add(time + "," + user + "," + pname + ",UNKNOWN_PRODUCT");
				continue;
			}

			if (!inRange(time, open, close)) {
				failLogs.add(time + "," + user + "," + pname + ",OUT_OF_BUSINESS_HOUR");
				continue;
			}

			Product p = products.get(pname);

			if (!inRange(time, p.startTime, p.endTime)) {
				failLogs.add(time + "," + user + "," + pname + ",OUT_OF_PICKUP_TIME");
				continue;
			}

			schedule.putIfAbsent(slot, new HashMap<>());
			schedule.get(slot).putIfAbsent(pname, new LinkedHashMap<>());
			Map<String, Integer> userMap = schedule.get(slot).get(pname);

			if (type.equals("RESERVE")) {
				if (p.stock < qty) {
					failLogs.add(time + "," + user + "," + pname + ",OUT_OF_STOCK");
					continue;
				}
				p.stock -= qty;
				userMap.put(user, userMap.getOrDefault(user, 0) + qty);
			} else {
				if (!userMap.containsKey(user) || userMap.get(user) < qty) {
					failLogs.add(time + "," + user + "," + pname + ",INVALID_CANCEL");
					continue;
				}
				userMap.put(user, userMap.get(user) - qty);
				if (userMap.get(user) == 0) userMap.remove(user);
				p.stock += qty;
			}
		}

		// 출력부: 정렬은 여기서만 한다
		System.out.println("[시간대별 상품 예약 현황]");

		List<String> productNames = new ArrayList<>(products.keySet());
		System.out.println("시간\t" + String.join("\t", productNames) + "\t예약자");

		// slot 정렬
		List<String> slots = new ArrayList<>(schedule.keySet());
		Collections.sort(slots);

		for (String slot : slots) {
			Map<String, Map<String, Integer>> productMap = schedule.get(slot);

			System.out.print(slot + "\t");

			for (String pname : productNames) {
				int sum = 0;
				if (productMap.containsKey(pname)) {
					for (int q : productMap.get(pname).values()) {
						sum += q;
					}
				}
				System.out.print(sum + "\t");
			}

			StringBuilder users = new StringBuilder();
			for (String pname : productMap.keySet()) {
				for (Map.Entry<String, Integer> e : productMap.get(pname).entrySet()) {
					users.append(e.getKey())
						.append("(")
						.append(e.getValue())
						.append(") ");
				}
			}

			System.out.println(users.toString().trim());
		}

		System.out.println("\n[상품별 남은 재고]");
		for (Product p : products.values()) {
			System.out.println(p.name + "," + p.stock);
		}

		System.out.println("\n[예약 실패 내역]");
		for (String f : failLogs) {
			System.out.println(f);
		}
	}
}
