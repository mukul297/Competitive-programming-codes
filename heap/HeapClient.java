
public class HeapClient {

	public static void main(String[] args) {

		Heap heap = new Heap();
		heap.add(60);
		heap.display();
		heap.add(50);
		heap.display();

		heap.add(40);
		heap.display();
		heap.add(30);
		heap.display();
		heap.add(20);
		heap.display();
		heap.add(10);

		heap.display();

		System.out.println(heap.remove());
		heap.display();
		System.out.println(heap.remove());
		heap.display();
		System.out.println(heap.remove());
		heap.display();

		// Scanner scn = new Scanner(System.in);
		//
		// scn.nextLine();
		// String s1 = scn.next();
		// String s2 = scn.next();
		//
		// int n = scn.nextInt();
		// System.out.println("s1 :" + s1);
		// System.out.println("s2 :" + s2);
	}

}
