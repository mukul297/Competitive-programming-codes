public class LinkedListClient {

	public static void main(String[] args) throws Exception {

		LinkedList list = new LinkedList();
		list.addLast(40);
		list.addLast(20);
		list.addLast(30);
		list.addLast(70);
		list.addLast(50);
		list.addLast(5);
		list.addLast(10);
		list.addLast(90);
		list.addLast(900);

		// list.display();
		// //list.addAt(100, 2);
		// list.display();
		// // list.removeFirst();
		// list.display();
		// // list.removeLast();
		// list.display();
		// list.removeAt(2);
		// list.display();
		// System.out.println(list.size());
		//
		// list.display();
		// list.ReverseDR();
		// list.display();
		//
		// list.fold();
		// list.display();
		//
		// System.out.println(list.kthFromLast(3));
		// // System.out.println(list.mid());

		// LinkedList list1 = new LinkedList();
		// list1.addLast(15);
		// list1.addLast(25);
		// list1.addLast(35);
		// list1.addLast(70);
		// list1.addLast(80);
		//
		// LinkedList list2 = list.mergeTwoSortedLL(list1) ;
		// list2.display();

		// list.mergeSort();
		list.display();
		list.kReverse(3);
		list.display();

	}

}
