import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;


public class GenericTree {

	private class Node {
		int data;
		ArrayList<Node> children = new ArrayList<>();
	}

	private Node root;
	private int size;

	public GenericTree() {
		this.root = construct(new Scanner(System.in), null, -1);
	}

	private Node construct(Scanner scn, Node parent, int ith) {

		if (parent == null) {
			System.out.println("Enter the data for root node ?");
		} else {
			System.out.println("Enter the data for " + ith + " th child of " + parent.data);
		}

		int val = scn.nextInt();
		Node nn = new Node();
		this.size++;
		nn.data = val;

		System.out.println("No. of children for " + nn.data);
		int noc = scn.nextInt();

		for (int i = 0; i < noc; i++) {
			Node child = construct(scn, nn, i);
			nn.children.add(child);
		}

		return nn;

	}

	public void display() {
		System.out.println("-------------------");
		display(this.root);
		System.out.println("-------------------");
	}

	private void display(Node node) {

		// self work
		String str = node.data + " -> ";

		for (int i = 0; i < node.children.size(); i++) {
			str += node.children.get(i).data + ", ";
		}

		str += ".";
		System.out.println(str);

		// smaller trees
		for (int i = 0; i < node.children.size(); i++) {
			display(node.children.get(i));
		}

	}

	public int size() {
		return this.size;
	}

	public int size2() {
		return size2(this.root);
	}

	private int size2(Node node) {

		int tcs = 0;

		for (Node child : node.children) {
			tcs += size2(child);
		}

		return tcs + 1;
	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {

		int tm = node.data;

		for (Node child : node.children) {
			int cm = max(child);
			if (cm > tm) {
				tm = cm;
			}
		}

		return tm;
	}

	public boolean find(int item) {
		return find(this.root, item);
	}

	private boolean find(Node node, int item) {

		if (node.data == item) {
			return true;
		}

		for (Node child : node.children) {
			boolean cf = find(child, item);

			if (cf == true) {
				return true;
			}
		}

		return false;
	}

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {

		int th = -1;
		for (Node child : node.children) {
			int ch = height(child);
			if (ch > th) {
				th = ch;
			}
		}

		return th + 1;
	}

	public void mirror() {
		mirror(this.root);
	}

	private void mirror(Node node) {

		for (Node child : node.children) {
			mirror(child);
		}

		// self work
		int left = 0;
		int right = node.children.size() - 1;

		while (left < right) {
			Node ln = node.children.get(left);
			Node rn = node.children.get(right);

			node.children.set(left, rn);
			node.children.set(right, ln);

			left++;
			right--;
		}
	}

	public boolean structurallySimilar(GenericTree gt2) {
		return structurallySimilar(this.root, gt2.root);
	}

	private boolean structurallySimilar(Node tnode, Node onode) {

		if (tnode.children.size() != onode.children.size()) {
			return false;
		}

		for (int i = 0; i < tnode.children.size(); i++) {
			boolean css = structurallySimilar(tnode.children.get(i), onode.children.get(i));

			if (css == false) {
				return false;
			}
		}

		return true;
	}

	public void preorder() {
		preorder(this.root);
	}

	private void preorder(Node node) {

		// self work
		System.out.print(node.data + " ");

		for (Node child : node.children) {
			preorder(child);
		}

	}

	public void postorder() {
		postorder(this.root);
	}

	private void postorder(Node node) {

		for (Node child : node.children) {
			postorder(child);
		}

		// self work
		System.out.print(node.data + " ");

	}

	public void traversal() {
		traversal(this.root);
	}

	private void traversal(Node node) {

		System.out.println("hii " + node.data);

		for (Node child : node.children) {

			System.out.println("going towards " + child.data);
			traversal(child);
			System.out.println("coming from " + child.data);
		}

		System.out.println("bye " + node.data);

	}

	public void levelorder() {

		LinkedList<Node> queue = new LinkedList<>();
		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();

			System.out.println(rn.data);

			for (int i = 0; i < rn.children.size(); i++) {
				queue.addLast(rn.children.get(i));
			}

		}

	}

	public void levelorderLW() {

		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> helper = new LinkedList<>();
		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();

			System.out.print(rn.data + " ");

			for (int i = 0; i < rn.children.size(); i++) {
				helper.addLast(rn.children.get(i));
			}

			if (queue.isEmpty()) {
				System.out.println();
				queue = helper;
				helper = new LinkedList<>();
			}

		}

	}

	public void levelorderZZ() {

		LinkedList<Node> queue = new LinkedList<>();
		LinkedList<Node> stack = new LinkedList<>();

		int count = 0;
		queue.addLast(this.root);

		while (!queue.isEmpty()) {

			Node rn = queue.removeFirst();

			System.out.print(rn.data + " ");

			if (count % 2 == 0) {
				for (int i = 0; i < rn.children.size(); i++) {
					stack.addFirst(rn.children.get(i));
				}
			} else {
				for (int i = rn.children.size() - 1; i >= 0; i--) {
					stack.addFirst(rn.children.get(i));
				}
			}

			if (queue.isEmpty()) {
				queue = stack;
				stack = new LinkedList<>();
				System.out.println();
				count++;
			}

		}

	}

	private class HeapMover {

		int size;
		int max = Integer.MIN_VALUE;
		boolean find;
		int ht;

		Node pred;
		Node succ;
		Node jl;
	}

	public void multiSolver(int item) {

		HeapMover mover = new HeapMover();
		multiSolver(this.root, item, 0, mover);

		System.out.println("max : " + mover.max);
		System.out.println("find : " + mover.find);
		System.out.println("size : " + mover.size);
		System.out.println("ht : " + mover.ht);
		System.out.println("pred : " + (mover.pred == null ? "null" : mover.pred.data));
		System.out.println("succ : " + (mover.succ == null ? "null" : mover.succ.data));
		System.out.println("jl : " + (mover.jl == null ? "null" : mover.jl.data));

	}

	private void multiSolver(Node node, int item, int depth, HeapMover mover) {

		mover.size++;

		if (node.data > mover.max) {
			mover.max = node.data;
		}

		if (depth > mover.ht) {
			mover.ht = depth;
		}

		if (mover.find == true && mover.succ == null) {
			mover.succ = node;
		}

		if (node.data == item) {
			mover.find = true;
		}

		if (mover.find == false) {
			mover.pred = node;
		}

		if (node.data > item) {
			if (mover.jl == null) {
				mover.jl = node;
			} else if (node.data < mover.jl.data) {
				mover.jl = node;
			}

		}

		for (Node child : node.children) {
			multiSolver(child, item, depth + 1, mover);
		}
	}

	private class OrderPairCT {
		Node node;
		boolean selfDone;
		boolean childDone;
	}

	private class OrderPairO {
		Node node;
		boolean selfDone;
		int childDone;
	}

	public void preorderICT() {

		LinkedList<OrderPairCT> stack = new LinkedList<>();

		OrderPairCT sp = new OrderPairCT();
		sp.node = this.root;
		stack.addFirst(sp);

		while (!stack.isEmpty()) {

			OrderPairCT tp = stack.getFirst();

			if (tp.selfDone == false) {
				System.out.print(tp.node.data + " ");
				tp.selfDone = true;
			} else if (tp.childDone == false) {

				ArrayList<Node> childs = tp.node.children;
				for (int i = childs.size() - 1; i >= 0; i--) {
					OrderPairCT np = new OrderPairCT();
					np.node = childs.get(i);

					stack.addFirst(np);
				}

				tp.childDone = true;
			} else {
				stack.removeFirst();
			}

		}

	}

	public void preorderIO() {

		LinkedList<OrderPairO> stack = new LinkedList<>();

		OrderPairO sp = new OrderPairO();
		sp.node = this.root;
		stack.addFirst(sp);

		while (!stack.isEmpty()) {

			OrderPairO tp = stack.getFirst();

			if (tp.selfDone == false) {
				System.out.print(tp.node.data + " ");
				tp.selfDone = true;
			} else if (tp.childDone < tp.node.children.size()) {

				OrderPairO np = new OrderPairO();
				np.node = tp.node.children.get(tp.childDone);

				stack.addFirst(np);

				tp.childDone++;
			} else {
				stack.removeFirst();
			}

		}

	}
}
