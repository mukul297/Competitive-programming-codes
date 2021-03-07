public class BST {

	private class Node {
		int data;
		Node left;
		Node right;
	}

	private Node root;
	private int size;

	public BST(int[] in) {

		this.root = construct(in, 0, in.length - 1);
	}

	private Node construct(int[] in, int ilo, int ihi) {

		if (ilo > ihi) {
			return null;
		}

		int mid = (ilo + ihi) / 2;

		Node nn = new Node();
		this.size++;
		nn.data = in[mid];

		nn.left = construct(in, ilo, mid - 1);
		nn.right = construct(in, mid + 1, ihi);

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
		if (node.right == null) {
			return node.data;
		}

		return max(node.right);

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

		if (item > node.data) {
			return find(node.right, item);
		} else if (item < node.data) {
			return find(node.left, item);
		} else {
			return true;
		}

	}

	public void printInRange(int ll, int ul) {
		printInRange(this.root, ll, ul);
	}

	private void printInRange(Node node, int ll, int ul) {

		if (node == null) {
			return;
		}

		if (node.data >= ll && node.data <= ul) {
			printInRange(node.left, ll, ul);
			System.out.println(node.data);
			printInRange(node.right, ll, ul);
		} else if (node.data < ll) {
			printInRange(node.right, ll, ul);
		} else if (node.data > ul) {
			printInRange(node.left, ll, ul);
		}
	}

	public void printDec() {
		printDec(this.root);
	}

	private void printDec(Node node) {

		if (node == null) {
			return;
		}

		printDec(node.right);
		System.out.println(node.data);
		printDec(node.left);
	}

	public void replaceWithSumLarger() {

		HeapMover mover = new HeapMover();
		replaceWithSumLarger(this.root, mover);
	}

	private void replaceWithSumLarger(Node node, HeapMover mover) {

		if (node == null) {
			return;
		}
		replaceWithSumLarger(node.right, mover);

		int val = node.data;
		node.data = mover.sum;
		mover.sum += val;

		replaceWithSumLarger(node.left, mover);
	}

	private class HeapMover {
		int sum;
	}

	public void add(int item) {
		add(this.root, item);
		// add(null, this.root, true, item);
	}

	private void add(Node node, int item) {

		if (item <= node.data) {

			if (node.left == null) {
				Node nn = new Node();
				nn.data = item;
				node.left = nn;
			} else {
				add(node.left, item);
			}
		} else {

			if (node.right == null) {
				Node nn = new Node();
				nn.data = item;
				node.right = nn;
			} else {
				add(node.right, item);
			}
		}
	}

	private void add(Node parent, Node child, boolean ilc, int item) {

		if (child == null) {
			Node nn = new Node();
			nn.data = item;
			if (ilc) {
				parent.left = nn;
			} else {
				parent.right = nn;
			}

			return;
		}

		if (child.data < item) {
			add(child, child.right, false, item);
		} else {
			add(child, child.left, true, item);
		}
	}

	public void delete(int item) {

		if (item == this.root.data) {

			if (this.root.left == null && this.root.right == null) {
				this.root = null;
			} else if (this.root.left != null && this.root.right == null) {
				this.root = this.root.left;
			} else if (this.root.left == null && this.root.right != null) {
				this.root = this.root.right;
			} else {
				delete(null, this.root, true, item);
			}
		} else {
			delete(null, this.root, true, item);
		}

	}

	private void delete(Node parent, Node node, boolean ilc, int item) {

		if (item < node.data) {
			delete(node, node.left, true, item);
		} else if (item > node.data) {
			delete(node, node.right, false, item);
		} else {

			if (node.left == null && node.right == null) {

				if (ilc) {
					parent.left = null;
				} else {
					parent.right = null;
				}
			} else if (node.left != null && node.right == null) {

				if (ilc) {
					parent.left = node.left;
				} else {
					parent.right = node.left;
				}
			} else if (node.left == null && node.right != null) {
				if (ilc) {
					parent.left = node.right;
				} else {
					parent.right = node.right;
				}
			} else {

				int max = max(node.left);
				node.data = max;

				delete(node, node.left, true, max);
			}
		}
	}

}
