
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;

public class BinaryTree {

	private class Node {
		int data;
		Node left;
		Node right;
	}

	private Node root;
	private int size;

	public BinaryTree() {

		this.root = construct(new Scanner(System.in), null, false);
	}

	private Node construct(Scanner scanner, Node parent, boolean ilc) {

		if (parent == null) {
			System.out.println("Enter the data for root  node");

		} else {
			if (ilc) {
				System.out.println("Enter the data for left child of " + parent.data);
			} else {
				System.out.println("Enter the data for right child of " + parent.data);
			}
		}

		int val = scanner.nextInt();
		Node nn = new Node();
		this.size++;
		nn.data = val;

		System.out.println(nn.data + " has left child ?");
		boolean lc = scanner.nextBoolean();
		if (lc) {
			nn.left = construct(scanner, nn, true);
		}

		System.out.println(nn.data + " has right child ?");
		boolean rc = scanner.nextBoolean();
		if (rc) {
			nn.right = construct(scanner, nn, false);
		}

		return nn;

	}

	public BinaryTree(int[] pre, int[] in) {
		this.root = construct(pre, 0, pre.length - 1, in, 0, in.length - 1);

	}

	private Node construct(int[] pre, int plo, int phi, int[] in, int ilo, int ihi) {

		if (plo > phi) {
			return null;
		}

		Node nn = new Node();
		nn.data = pre[plo];

		int si = -1;

		for (int i = ilo; i <= ihi; i++) {

			if (pre[plo] == in[i]) {
				si = i;
				break;
			}
		}

		int nel = si - ilo;
		nn.left = construct(pre, plo + 1, plo + nel, in, ilo, si - 1);
		nn.right = construct(pre, plo + nel + 1, phi, in, si + 1, ihi);

		return nn;
	}

	public void display() {
		display(this.root);
	}

	private void display(Node node) {

		if (node == null) {
			return;
		}

		String str = "";

		if (node.left != null) {
			str += node.left.data;
		} else {
			str += ".";
		}

		str += " -> " + node.data + " <- ";

		if (node.right != null) {
			str += node.right.data;
		} else {
			str += ".";
		}

		System.out.println(str);

		display(node.left);
		display(node.right);
	}

	public int size2() {
		return size2(this.root);
	}

	private int size2(Node node) {

		if (node == null) {
			return 0;
		}

		int ls = size2(node.left);
		int rs = size2(node.right);
		return ls + rs + 1;
	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}

		int lm = max(node.left);
		int rm = max(node.right);

		return Math.max(node.data, Math.max(lm, rm));

	}

	public int height() {
		return height(this.root);
	}

	private int height(Node node) {

		if (node == null) {
			return -1;
		}

		int lh = height(node.left);
		int rh = height(node.right);

		return Math.max(lh, rh) + 1;
	}

	public boolean find(int item) {
		return find(this.root, item);
	}

	private boolean find(Node node, int item) {

		if (node == null)
			return false;

		if (node.data == item) {
			return true;
		}

		boolean lf = find(node.left, item);
		if (lf)
			return true;

		boolean rf = find(node.right, item);
		if (rf)
			return true;

		return false;
	}

	public int diameter() {
		return diameter(this.root);
	}

	private int diameter(Node node) {

		if (node == null) {
			return 0;
		}

		int ld = diameter(node.left);
		int rd = diameter(node.right);

		int sp = height(node.left) + height(node.right) + 2;

		return Math.max(sp, Math.max(ld, rd));

	}

	private class Pair {
		int height;
		int diameter;
	}

	public int diameterPair() {
		Pair pair = diameterPair(this.root);
		return pair.diameter;
	}

	private Pair diameterPair(Node node) {

		if (node == null) {
			Pair br = new Pair();
			br.height = -1;
			br.diameter = 0;
			return br;
		}

		Pair lr = diameterPair(node.left);
		Pair rr = diameterPair(node.right);

		Pair mr = new Pair();
		mr.height = Math.max(lr.height, rr.height) + 1;

		int ld = lr.diameter;
		int rd = rr.diameter;
		int sp = lr.height + rr.height + 2;

		mr.diameter = Math.max(ld, Math.max(rd, sp));

		return mr;

	}

	private class BalancedPair {

		int height;
		boolean isBalanced;
	}

	public boolean isBalanced() {
		return isBalanced(this.root).isBalanced;
	}

	private BalancedPair isBalanced(Node node) {
		if (node == null) {
			BalancedPair bbp = new BalancedPair();
			bbp.height = -1;
			bbp.isBalanced = true;
			return bbp;
		}

		BalancedPair lbp = isBalanced(node.left);
		BalancedPair rbp = isBalanced(node.right);

		BalancedPair mbp = new BalancedPair();
		mbp.height = Math.max(lbp.height, rbp.height) + 1;

		int bf = lbp.height - rbp.height;

		if (lbp.isBalanced && rbp.isBalanced && Math.abs(bf) <= 1) {
			mbp.isBalanced = true;
		} else {
			mbp.isBalanced = false;
		}

		return mbp;

	}

	private class OrderPair {
		Node node;
		boolean selfDone;
		boolean leftDone;
		boolean rightDone;
	}

	public void preOrderI() {

		LinkedList<OrderPair> stack = new LinkedList<>();

		OrderPair sp = new OrderPair();
		sp.node = this.root;

		stack.addFirst(sp);

		while (!stack.isEmpty()) {

			OrderPair tp = stack.getFirst();

			if (tp.selfDone == false) {
				System.out.println(tp.node.data);
				tp.selfDone = true;
			} else if (tp.leftDone == false) {

				if (tp.node.left != null) {
					OrderPair np = new OrderPair();
					np.node = tp.node.left;
					stack.addFirst(np);
				}
				tp.leftDone = true;
			} else if (tp.rightDone == false) {
				if (tp.node.right != null) {
					OrderPair np = new OrderPair();
					np.node = tp.node.right;
					stack.addFirst(np);
				}
				tp.rightDone = true;
			} else {
				stack.removeFirst();
			}

		}

	}

	public void largestBST() {
		BSTPair bstpair = largestBST(this.root);

		System.out.println(bstpair.largestBSTNode.data);
		System.out.println(bstpair.size);
	}

	private BSTPair largestBST(Node node) {

		if (node == null) {

			BSTPair pair = new BSTPair();
			return pair;
		}

		BSTPair left = largestBST(node.left);
		BSTPair right = largestBST(node.right);

		BSTPair mr = new BSTPair();

		if (left.isbst && right.isbst && node.data > left.max && node.data < right.min) {
			mr.isbst = true;
			mr.largestBSTNode = node;
			mr.size = left.size + right.size + 1;
			mr.max = Math.max(node.data, Math.max(left.max, right.max));
			mr.min = Math.min(node.data, Math.min(left.min, right.min));

		} else {
			mr.isbst = false;
			if (left.size > right.size) {
				mr.size = left.size;
				mr.largestBSTNode = left.largestBSTNode;
				mr.max = left.max;
				mr.min = left.min;
			} else {
				mr.size = right.size;
				mr.largestBSTNode = right.largestBSTNode;
				mr.max = right.max;
				mr.min = right.min;
			}

		}

		return mr;
	}

	private class BSTPair {

		Node largestBSTNode = null;
		int size = 0;
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		boolean isbst = true;

	}
}
