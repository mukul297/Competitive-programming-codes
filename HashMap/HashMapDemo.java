
import java.util.ArrayList;
import java.util.HashMap;

public class HashMapDemo {

	public static void main(String[] args) {

		HashMap<String, Integer> map = new HashMap<>();

		// put
		map.put("India", 100);
		map.put("US", 10);
		map.put("US", 900);
		System.out.println(map);

		// get
		System.out.println(map.get("India"));
		System.out.println(map.get("USA"));

		// contains key
		System.out.println(map.containsKey("India"));
		System.out.println(map.containsKey("USA"));

		// remove
		// System.out.println(map.remove("US"));
		System.out.println(map);
		System.out.println(map.remove("USA"));
		System.out.println(map);

		// keyset
		ArrayList<String> keys = new ArrayList<>(map.keySet());
		System.out.println(keys);

		int[] a1 = { 1, 2, 3, 0, 1, 7, 8, 1, 2, 7, 7 };
		int[] a2 = { 1, 1, 2, 2, 2, 3, 3, 3 };
		System.out.println(intersection(a1, a2));

		
		int[] arr = {2, 12, 9, 16, 10, 5, 3, 20, 25, 11, 1, 8, 6} ;
		System.out.println(longestSequence(arr));
	}

	public static ArrayList<Integer> intersection(int[] a1, int[] a2) {

		HashMap<Integer, Integer> map = new HashMap<>();

		ArrayList<Integer> ans = new ArrayList<>();
		for (int i = 0; i < a1.length; i++) {

			if (map.containsKey(a1[i])) {
				int of = map.get(a1[i]);
				int nf = of + 1;
				map.put(a1[i], nf);
			} else {
				map.put(a1[i], 1);
			}
		}

		for (int j = 0; j < a2.length; j++) {

			if (map.containsKey(a2[j]) && map.get(a2[j]) > 0) {

				// ans add
				ans.add(a2[j]);

				// freq dec put
				int of = map.get(a2[j]);
				int nf = of - 1;
				map.put(a2[j], nf);
			}
		}

		return ans;
	}

	public static ArrayList<Integer> longestSequence(int[] arr) {

		ArrayList<Integer> ans = new ArrayList<>();
		HashMap<Integer, Boolean> map = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {

			int val = arr[i];

			if (map.containsKey(val - 1)) {
				map.put(val, false);
			} else {
				map.put(val, true);
			}

			if (map.containsKey(val + 1)) {
				map.put(val + 1, false);
			}
		}

		ArrayList<Integer> keys = new ArrayList<>(map.keySet());

		int mcount = Integer.MIN_VALUE;
		int seqstarting = -1;

		for (Integer key : keys) {

			if (map.get(key) == true) {

				int count = 0;

				while (map.containsKey(key + count)) {
					count++;
				}

				if (count > mcount) {
					mcount = count;
					seqstarting = key;
				}

			}
		}

		for (int i = 0; i < mcount; i++) {
			ans.add(seqstarting + i);
		}

		return ans;
	}

}
