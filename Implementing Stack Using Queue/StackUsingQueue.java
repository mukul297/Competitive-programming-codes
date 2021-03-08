public class StackUsingQueue {

	DynamicQueue primaryQueue = new DynamicQueue();

	public void push(int item) throws Exception {

		try {
			primaryQueue.enqueue(item);
		} catch (Exception e) {
			throw new Exception("Stack is Full.");
		}
	}

	public int pop() throws Exception {

		try {
			DynamicQueue helperQueue = new DynamicQueue();

			while (primaryQueue.size() != 1) {
				int temp = primaryQueue.dequeue();
				helperQueue.enqueue(temp);
			}

			int rv = primaryQueue.dequeue();

			primaryQueue = helperQueue;

			return rv;
		} catch (Exception e) {
			throw new Exception("Stack is Empty.");
		}
	}

	public int top() throws Exception {

		try {
			DynamicQueue helperQueue = new DynamicQueue();

			while (primaryQueue.size() != 1) {
				int temp = primaryQueue.dequeue();
				helperQueue.enqueue(temp);
			}

			int rv = primaryQueue.dequeue();

			primaryQueue = helperQueue;
			primaryQueue.enqueue(rv);

			return rv;
		} catch (Exception e) {
			throw new Exception("Stack is Empty.");
		}
	}

	public int size() {
		return primaryQueue.size();
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void display() throws Exception {

		displayH(0);
	}

	public void displayH(int count) throws Exception {

		if (count == primaryQueue.size()) {
			return;
		}

		int temp = primaryQueue.dequeue();
		primaryQueue.enqueue(temp);
		displayH(count + 1);
		System.out.println(temp);
	}

}
