import java.util.ArrayList;

public class HuffmanCoding {

	private class Node implements Comparable<Node> {
		char ch;
		int cost;
		Node left;
		Node right;

		@Override
		public int compareTo(Node other) {
			return other.cost - this.cost;
		}

	}

	HashMap<Character, String> encoder = new HashMap<>();
	HashMap<String, Character> decoder = new HashMap<>();

	public HuffmanCoding(String trainingDataSet) {

		HashMap<Character, Integer> fmap = new HashMap<>();

		// create a freq map
		for (int i = 0; i < trainingDataSet.length(); i++) {
			char ch = trainingDataSet.charAt(i);
			if (fmap.containsKey(ch)) {
				int oc = fmap.get(ch);
				int nc = oc + 1;
				fmap.put(ch, nc);
			} else {
				fmap.put(ch, 1);
			}
		}

		HeapGeneric<Node> heap = new HeapGeneric<>();

		// key set
		ArrayList<Character> keys = new ArrayList<>(fmap.keySet());

		// put all keys in heap
		for (char key : keys) {
			Node np = new Node();
			np.ch = key;
			np.cost = fmap.get(key);

			heap.add(np);
		}

		// do work till the size of heap is 1
		while (heap.size() != 1) {

			// take out two nodes
			Node fn = heap.remove();
			Node sn = heap.remove();

			// merge 2 nodes and put back in heap
			Node merged = new Node();
			merged.cost = fn.cost + sn.cost;
			merged.left = fn;
			merged.right = sn;

			heap.add(merged);
		}

		Node lastNode = heap.remove();
		fillEncoderDecoder(lastNode, "");
	}

	private void fillEncoderDecoder(Node node, String asf) {

		if (node.left == null && node.right == null) {
			encoder.put(node.ch, asf);
			decoder.put(asf, node.ch);
			return;
		}
		fillEncoderDecoder(node.left, asf + "0");
		fillEncoderDecoder(node.right, asf + "1");
	}

	public String encoding(String str) {

		String ans = "";

		for (int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			ans += encoder.get(ch);
		}

		return ans;
	}

	public String decoding(String str) {

		String ans = "";
		String key = "";

		for (int i = 0; i < str.length(); i++) {
			key += str.charAt(i);

			if (decoder.containsKey(key)) {
				ans += decoder.get(key);
				key = "";
			}
		}

		return ans;
	}
}
