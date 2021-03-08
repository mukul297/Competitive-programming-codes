public class StackClient {

	public static void main(String[] args) throws Exception {

		Stack stack = new Stack();
		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		// stack.display();
		// stack.push(60);
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());
		// System.out.println(stack.pop());

		stack.display(); // 40 30 20 10
		displayReverse(stack);
		stack.display(); // 40 30 20 10

		actualReverse(stack, new Stack(stack.size()));
		stack.display(); // 10 20 30 40

		int[] price = { 6, 3, 10, 8, 7, 12, 5, 4, 11, 9 };
		int[] span = stockSpan(price);

		for (int val : span)
			System.out.print(val + " ");

	}

	public static void displayReverse(Stack s) throws Exception {

		if (s.isEmpty())
			return;

		int temp = s.pop();
		displayReverse(s);

		System.out.println(temp);
		s.push(temp);
	}

	public static void actualReverse(Stack s, Stack h) throws Exception {

		if (s.isEmpty()) {

			if (h.isEmpty())
				return;

			int temph = h.pop();
			actualReverse(s, h);
			s.push(temph);

			return;
		}

		int temp = s.pop();
		h.push(temp);

		actualReverse(s, h);
	}

	public static int[] stockSpan(int[] price) throws Exception {

		Stack stack = new Stack(price.length);

		int[] span = new int[price.length];

		stack.push(0);
		span[0] = 1;

		for (int i = 1; i < price.length; i++) {

			while (!stack.isEmpty() && price[i] > price[stack.top()]) {
				stack.pop();
			}
			if (stack.isEmpty()) {
				span[i] = i + 1;
			} else {
				span[i] = i - stack.top();
			}
			stack.push(i);
		}
		
		return span;
	}
}
