public class LinkedList {

	private class Node {
		int data;
		Node next;
	}

	private Node head;
	private Node tail;
	private int size;

	public int size() {
		return this.size;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}

	// O(1)
	public int getFirst() throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		return this.head.data;
	}

	// O(1)
	public int getLast() throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		return this.tail.data;
	}

	// O(n)
	public int getAt(int idx) throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index.");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp.data;
	}

	// O(n)
	private Node getNodeAt(int idx) throws Exception {

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index.");
		}

		Node temp = this.head;
		for (int i = 0; i < idx; i++) {
			temp = temp.next;
		}

		return temp;
	}

	// O(1)
	public void addLast(int item) {

		// create a new node
		Node nn = new Node();

		// update
		nn.data = item;
		nn.next = null;

		// attach
		if (this.size > 0)
			this.tail.next = nn;

		// summary update
		if (this.size == 0) {
			this.head = nn;
			this.tail = nn;
			this.size++;
		} else {
			this.tail = nn;
			this.size++;
		}
	}

	// O(1)
	public void addFirst(int item) {

		// new node
		Node nn = new Node();

		// update
		nn.data = item;
		nn.next = null;

		// attach
		nn.next = this.head;

		// update summary
		if (this.size == 0) {
			this.head = nn;
			this.tail = nn;
			this.size++;
		} else {
			this.head = nn;
			this.size++;
		}

	}

	// O(n)
	public void addAt(int item, int idx) throws Exception {

		if (idx < 0 || idx > this.size) {
			throw new Exception("Invalid Index.");
		}

		if (idx == 0) {
			addFirst(item);
		} else if (idx == this.size) {
			addLast(item);
		} else {
			// new node
			Node nn = new Node();

			// update
			nn.data = item;
			nn.next = null;

			// attach
			Node nm1 = getNodeAt(idx - 1);
			Node np1 = nm1.next;

			nm1.next = nn;
			nn.next = np1;

			this.size++;
		}

	}

	// O(n)
	public void display() {

		System.out.println("------------------------");

		Node temp = this.head;
		while (temp != null) {
			System.out.print(temp.data + " ");
			temp = temp.next;
		}

		System.out.println(".");
		System.out.println("------------------------");
	}

	// O(1)
	public int removeFirst() throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		Node temp = this.head;

		if (this.size == 1) {
			this.head = null;
			this.tail = null;
			this.size = 0;
		} else {
			this.head = this.head.next;
			this.size--;
		}

		return temp.data;
	}

	// O(n)
	public int removeLast() throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		Node temp = this.tail;

		if (this.size == 1) {
			this.head = null;
			this.tail = null;
			this.size = 0;
		} else {
			Node sm2 = getNodeAt(this.size - 2);
			this.tail = sm2;
			this.tail.next = null;
			this.size--;
		}

		return temp.data;
	}

	// O(n)
	public int removeAt(int idx) throws Exception {

		if (this.size == 0) {
			throw new Exception("LL is Empty.");
		}

		if (idx < 0 || idx >= this.size) {
			throw new Exception("Invalid Index.");
		}

		if (idx == 0) {
			return this.removeFirst();
		} else if (idx == this.size - 1) {
			return this.removeLast();
		} else {
			Node nm1 = getNodeAt(idx - 1);
			Node n = nm1.next;
			Node np1 = n.next;

			nm1.next = np1;

			this.size--;
			return n.data;

		}
	}

	public void ReverseDI() throws Exception {

		int left = 0;
		int right = this.size - 1;

		while (left < right) {

			Node ln = getNodeAt(left);
			Node rn = getNodeAt(right);

			int temp = ln.data;
			ln.data = rn.data;
			rn.data = temp;

			left++;
			right--;
		}
	}

	public void ReversePI() {

		Node prev = this.head;
		Node curr = this.head.next;

		while (curr != null) {

			Node ahead = curr.next;

			curr.next = prev;

			prev = curr;
			curr = ahead;
		}

		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;

	}

	public void ReversePR() {
		ReversePRH(this.head, this.head.next);

		Node temp = this.head;
		this.head = this.tail;
		this.tail = temp;
		this.tail.next = null;

	}

	private void ReversePRH(Node prev, Node curr) {

		if (curr == null) {
			return;
		}

		ReversePRH(curr, curr.next);
		curr.next = prev;
	}

	public void ReverseDR() {
		HeapMover mover = new HeapMover();
		mover.left = this.head;
		// ReverseDR(mover, this.head, 0);
		ReverseDRReturn(this.head, this.head, 0);
	}

	public void ReverseDR(HeapMover mover, Node right, int count) {

		if (right == null) {
			return;
		}

		ReverseDR(mover, right.next, count + 1);

		if (count >= size / 2) {
			int temp = mover.left.data;
			mover.left.data = right.data;
			right.data = temp;
		}

		mover.left = mover.left.next;
	}

	private Node ReverseDRReturn(Node left, Node right, int count) {

		if (right == null) {
			return left;
		}

		Node nl = ReverseDRReturn(left, right.next, count + 1);

		if (count >= size / 2) {
			int temp = nl.data;
			nl.data = right.data;
			right.data = temp;
		}

		return nl.next;
	}

	private class HeapMover {
		Node left;
	}

	public Node mid() {

		Node slow = this.head;
		Node fast = this.head;

		while (fast.next != null && fast.next.next != null) {

			slow = slow.next;
			fast = fast.next.next;
		}

		return slow;
	}

	public void fold() {

		HeapMover mover = new HeapMover();
		mover.left = this.head;

		fold(mover, this.head, 0);
	}

	private void fold(HeapMover mover, Node right, int count) {

		if (right == null) {
			return;
		}

		fold(mover, right.next, count + 1);

		if (count > size / 2) {

			Node ahead = mover.left.next;
			mover.left.next = right;
			right.next = ahead;
			mover.left = ahead;

		} else if (count == size / 2) {
			this.tail = right;
			this.tail.next = null;
		}

	}

	public int kthFromLast(int k) {

		Node slow = this.head;
		Node fast = this.head;

		for (int i = 0; i < k; i++) {
			fast = fast.next;
		}

		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}

		return slow.data;

	}

	public LinkedList mergeTwoSortedLL(LinkedList other) {

		LinkedList rv = new LinkedList();

		Node ttemp = this.head;
		Node otemp = other.head;

		while (ttemp != null && otemp != null) {

			if (ttemp.data < otemp.data) {
				rv.addLast(ttemp.data);
				ttemp = ttemp.next;
			} else {
				rv.addLast(otemp.data);
				otemp = otemp.next;
			}
		}

		if (ttemp == null) {
			while (otemp != null) {
				rv.addLast(otemp.data);
				otemp = otemp.next;
			}
		}

		if (otemp == null) {
			while (ttemp != null) {
				rv.addLast(ttemp.data);
				ttemp = ttemp.next;
			}
		}

		return rv;

	}

	public void mergeSort() {

		if (this.size == 1) {
			return;
		}

		Node mid = mid();
		Node midn = mid.next;

		LinkedList fh = new LinkedList();
		fh.head = this.head;
		fh.tail = mid;
		fh.tail.next = null;
		fh.size = (this.size + 1) / 2;

		LinkedList sh = new LinkedList();
		sh.head = midn;
		sh.tail = this.tail;
		sh.tail.next = null;
		sh.size = this.size / 2;

		fh.mergeSort();
		sh.mergeSort();

		LinkedList merged = fh.mergeTwoSortedLL(sh);

		this.head = merged.head;
		this.tail = merged.tail;
	}

	public void kReverse(int k) throws Exception {

		LinkedList prev = null;
		LinkedList curr = null;

		while (this.size != 0) {

			curr = new LinkedList();

			for (int i = 1; i <= k; i++) {
				curr.addFirst(this.removeFirst());
			}

			if (prev == null) {
				prev = curr;
			} else {
				prev.tail.next = curr.head;
				prev.tail = curr.tail;
				prev.size += curr.size;
			}
		}

		this.head = prev.head;
		this.tail = prev.tail;
	}

}
