import java.math.BigDecimal;


public class BSTClient {

	public static void main(String[] args) {

		int[] in = { 2, 5, 10, 15, 20, 25, 30 };
		BST bst = new BST(in);
		bst.display();

		// bst.printInRange(4, 26);
		// bst.replaceWithSumLarger();
		bst.display();

		System.out.println();
		// bst.add(7);
		bst.delete(15);
		bst.display();
		// float f = (float) 2000.1;
		// double d = 2000.1;
		//
		// System.out.printf("%.6f", d);
		// System.out.println();
		// System.out.printf("%.6f", f);

		// System.out.println();
		// for (float f1 = (float) 0.1; f1 <= 0.9; f1 += 0.1) {
		// System.out.println(f1);
		// }
		// System.out.println();
		// for (double f2 = 0.1; f2 <= 0.9; f2 += 0.1) {
		// System.out.println(f2);
		// }

		System.out.println();
		BigDecimal bd = new BigDecimal(1.0023);
		System.out.println(bd);
	}

}
