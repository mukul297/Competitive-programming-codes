
import java.util.ArrayList;


public class BTClient {

	// 10 true 20 true 40 false false true 50 false false true 30 true 60 false
	// false false
	public static void main(String[] args) {

		// int[] pre = { 10, 20, 40, 50, 70, 80, 30, 60 };
		// int[] in = { 40, 20, 70, 50, 80, 10, 60, 30 };
		int[] pre = { 500, 40, 30, 15, 35, 45, 42, 47, 60, 55, 65 };
		int[] in = { 15, 30, 35, 40, 42, 45, 47, 500, 55, 60, 65 };
		BinaryTree bt = new BinaryTree(pre, in);
		bt.display();

		bt.largestBST();

		// System.out.println(bt.size2());
		// System.out.println(bt.diameter());
		// System.out.println(bt.diameterPair());
		// bt.preOrderI();

	}

}
