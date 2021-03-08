public class DynamicQueue extends Queue {

	@Override
	public void enqueue(int item) throws Exception {

		if (this.size() == this.data.length) {

			int[] oq = this.data;
			int[] nq = new int[2 * oq.length];

			for (int i = 0; i < oq.length; i++) {
				int idx = (i + this.front) % this.data.length;
				nq[i] = oq[idx];
			}

			this.data = nq;
			this.front = 0;
		}

		super.enqueue(item);
	}
}
