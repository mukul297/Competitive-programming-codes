public class GTCient {

	// 10 3 20 2 50 0 60 0 30 0 40 1 70 0
	// 10 3 20 2 50 0 60 0 30 0 40 1 70 1 100 0
	// 10 3 20 2 50 0 60 2 80 0 90 0 30 0 40 1 70 2 100 0 110 0
	public static void main(String[] args) {

		GenericTree gt = new GenericTree();
		// GenericTree gt2 = new GenericTree();
		gt.display();
		System.out.println(gt.size());
		System.out.println(gt.size2());
		System.out.println(gt.find(60));
		System.out.println(gt.find(70));
		System.out.println(gt.max());
		System.out.println(gt.height());

		// gt.mirror();
		// System.out.println(gt.structurallySimilar(gt2));
		// gt.display();

		// gt.levelorder();
		// System.out.println();
		// gt.levelorderLW();
		// System.out.println();
		// gt.levelorderZZ();

		// gt.multiSolver(10);
		gt.preorderICT();
		System.out.println();
		gt.preorderIO();
	}

}
