public class DynamicStack extends Stack {

	@Override
	public void push(int item) throws Exception {

		if (this.size() == this.data.length) {

			int[] os = this.data;
			int[] ns = new int[2 * os.length];

			for (int i = 0; i < os.length; i++) {
				ns[i] = os[i];
			}

			this.data = ns;
		}

		super.push(item);
	}
}
