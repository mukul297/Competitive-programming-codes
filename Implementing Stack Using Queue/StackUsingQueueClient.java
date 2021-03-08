public class StackUsingQueueClient {

	public static void main(String[] args) throws Exception {

		StackUsingQueue stack = new StackUsingQueue();

		stack.push(10);
		stack.push(20);
		stack.push(30);
		stack.push(40);
		stack.push(50);
		System.out.println(stack.pop());

		// stack.display();

	}

}
