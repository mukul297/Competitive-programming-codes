public class QueueClient {

	public static void main(String[] args) throws Exception {

		Queue q = new Queue();
		q.enqueue(10);
		q.enqueue(20);
		q.enqueue(30);
		q.enqueue(40);
		q.enqueue(50);

		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		q.enqueue(60);
		q.enqueue(70);

		q.display();

		ActualReverse(q);
		q.display();

		DisplayReverse(q, 0);
		q.display();
	}

	public static void ActualReverse(Queue queue) throws Exception {

		if (queue.isEmpty()) {
			return;
		}

		int temp = queue.dequeue();
		ActualReverse(queue);
		queue.enqueue(temp);
	}

	public static void DisplayReverse(Queue queue, int count) throws Exception {

		if (count == queue.size()) {
			return;
		}

		int temp = queue.dequeue();
		queue.enqueue(temp);
		DisplayReverse(queue, count + 1);
		System.out.println(temp);
	}
}
