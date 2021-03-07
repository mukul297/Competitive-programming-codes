

import java.util.ArrayList;


public class HeapGenericClient {

	public static void main(String[] args) {
		Car[] cars = new Car[5];
		cars[0] = new Car("A", 100, 267);
		cars[1] = new Car("a", 500, 2000);
		cars[2] = new Car("U", 700, 100);
		cars[3] = new Car("Q", 200, 200);
		cars[4] = new Car("J", 900, 340);

		HeapGeneric<Car> heap = new HeapGeneric<>();
		heap.add(cars[0]);
		heap.add(cars[1]);
		heap.add(cars[2]);
		heap.add(cars[3]);
		heap.add(cars[4]);

		System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());
		System.out.println(heap.remove());

		ArrayList<ArrayList<Integer>> list = new ArrayList<>();

		list.add(new ArrayList<>());
		list.add(new ArrayList<>());
		list.add(new ArrayList<>());
		list.add(new ArrayList<>());

		list.get(0).add(10);
		list.get(0).add(20);
		list.get(0).add(30);
		list.get(0).add(40);
		list.get(1).add(2);
		list.get(1).add(3);
		list.get(1).add(5);
		list.get(2).add(6);
		list.get(2).add(15);
		list.get(2).add(23);
		list.get(3).add(1);
		list.get(3).add(2);
		list.get(3).add(3);

		System.out.println(mergeKSortedLists(list));

		ArrayList<Integer> list1 = new ArrayList<>();
		list1.add(10);
		list1.add(5);
		list1.add(100);
		list1.add(60);
		list1.add(80);
		list1.add(2);

		System.out.println(KLargestElements(list1, 3));

	}

	public static class Pair implements Comparable<Pair> {

		int data;
		int listNo;
		int idxNo;

		public Pair(int data, int listNo, int idxNo) {
			this.data = data;
			this.listNo = listNo;
			this.idxNo = idxNo;
		}

		@Override
		public int compareTo(Pair other) {
			return other.data - this.data;
		}

	}

	public static ArrayList<Integer> mergeKSortedLists(ArrayList<ArrayList<Integer>> list) {

		ArrayList<Integer> ans = new ArrayList<>();

		HeapGeneric<Pair> heap = new HeapGeneric<>();

		for (int i = 0; i < list.size(); i++) {
			Pair np = new Pair(list.get(i).get(0), i, 0);
			heap.add(np);
		}

		while (!heap.isEmpty()) {

			Pair rp = heap.remove();
			ans.add(rp.data);

			rp.idxNo++;
			if (rp.idxNo < list.get(rp.listNo).size()) {
				rp.data = list.get(rp.listNo).get(rp.idxNo);
				heap.add(rp);
			}

		}

		return ans;

	}

	// for this fxn change the logic in generic heap to o.compareTo(t)
	public static ArrayList<Integer> KLargestElements(ArrayList<Integer> list, int k) {

		ArrayList<Integer> ans = new ArrayList<>();

		HeapGeneric<Integer> heap = new HeapGeneric<>();

		for (int i = 0; i < k; i++) {
			heap.add(list.get(i));
		}

		for (int i = k; i < list.size(); i++) {

			int existingElement = heap.get();

			if (list.get(i) > existingElement) {
				heap.remove();
				heap.add(list.get(i));
			}

		}

		while (!heap.isEmpty()) {
			ans.add(heap.remove());
		}
		
		return ans;
	}

}
