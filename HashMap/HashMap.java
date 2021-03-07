import java.util.ArrayList;
import java.util.LinkedList;

public class HashMap<K, V> {

	private class HMNode {
		K key;
		V value;

		public HMNode(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private LinkedList<HMNode>[] bucketArray;
	private int size;

	public HashMap() {
		this(5);
	}

	public HashMap(int cap) {
		this.bucketArray = new LinkedList[cap];
		// this.bucketArray = new LinkedList<HMNode>()[cap];
		this.size = 0;

		for (int i = 0; i < bucketArray.length; i++) {
			bucketArray[i] = new LinkedList<>();
		}
	}

	public void put(K key, V value) {

		int bi = hashFunction(key);
		LinkedList<HMNode> bucket = bucketArray[bi];

		int si = findInBucket(bucket, key);

		if (si == -1) {
			HMNode nn = new HMNode(key, value);
			bucket.addLast(nn);
			this.size++;
		} else {
			HMNode ntbu = bucket.get(si);
			ntbu.value = value;
		}

		double avgsize = 1.0 * this.size / this.bucketArray.length;

		if (avgsize > 2.0) {
			rehash();
		}

	}

	private void rehash() {

		LinkedList<HMNode>[] oba = this.bucketArray;

		this.bucketArray = new LinkedList[2 * oba.length];
		this.size = 0;

		for (int i = 0; i < bucketArray.length; i++) {
			bucketArray[i] = new LinkedList<>();
		}

		for (int i = 0; i < oba.length; i++) {

			LinkedList<HMNode> bucket = oba[i];

			for (int j = 0; j < bucket.size(); j++) {
				HMNode node = bucket.get(j);
				this.put(node.key, node.value);
			}
		}

	}

	public V get(K key) {

		int bi = hashFunction(key);
		LinkedList<HMNode> bucket = bucketArray[bi];

		int si = findInBucket(bucket, key);

		if (si == -1) {
			return null;
		} else {
			HMNode node = bucket.get(si);
			return node.value;
		}

	}

	public boolean containsKey(K key) {

		int bi = hashFunction(key);
		LinkedList<HMNode> bucket = bucketArray[bi];

		int si = findInBucket(bucket, key);

		if (si == -1) {
			return false;
		} else {
			return true;
		}

	}

	public V remove(K key) {

		int bi = hashFunction(key);
		LinkedList<HMNode> bucket = bucketArray[bi];

		int si = findInBucket(bucket, key);

		if (si == -1) {
			return null;
		} else {
			HMNode node = bucket.remove(si);
			this.size--;
			return node.value;
		}

	}

	public ArrayList<K> keySet() {

		ArrayList<K> keys = new ArrayList<>();

		for (int i = 0; i < bucketArray.length; i++) {

			LinkedList<HMNode> bucket = bucketArray[i];

			for (int j = 0; j < bucket.size(); j++) {
				HMNode node = bucket.get(j);
				keys.add(node.key);
			}
		}

		return keys;

	}

	public void display() {

		System.out.println("-----------------------------------");

		// all buckets
		for (int i = 0; i < bucketArray.length; i++) {

			System.out.print("Bucket " + i + " : ");

			// get a bucket
			LinkedList<HMNode> bucket = bucketArray[i];

			// bucket loop
			for (int j = 0; j < bucket.size(); j++) {
				HMNode node = bucket.get(j);
				System.out.print(node.key + "@" + node.value + " , ");
			}
			System.out.println();
		}

		System.out.println("-----------------------------------");

	}

	public int hashFunction(K key) {

		int hc = key.hashCode();
		int bi = Math.abs(hc) % this.bucketArray.length;

		return bi;
	}

	public int findInBucket(LinkedList<HMNode> bucket, K ktbf) {

		for (int i = 0; i < bucket.size(); i++) {
			HMNode node = bucket.get(i);
			if (node.key.equals(ktbf)) {
				return i;
			}
		}

		return -1;
	}

}
