

public class HMClient {

	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<>(3);

		// put
		map.put("India", 100);
		map.put("US", 10);
		map.put("SL", 10);
		map.put("ABC", 10);
		// map.display();
		// map.put("US", 900);
		// map.display();
		map.put("DEF", 900);
		map.put("GHI", 900);
		map.display();

		map.put("XYZ", 80);
		map.display();
		// System.out.println(map.get("US"));
		// System.out.println(map.get("USA"));
		//
		// System.out.println(map.keySet());
		//
		// System.out.println(map.remove("US"));
		// map.display();
	}

}
