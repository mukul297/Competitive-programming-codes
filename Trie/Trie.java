
import java.util.ArrayList;
import java.util.HashMap;


public class Trie {

	private class Node {
		char ch;
		boolean eow;
		HashMap<Character, Node> table;

		public Node(char ch) {
			this.ch = ch;
			this.eow = false;
			this.table = new HashMap<>();
		}
	}

	private Node root;

	public Trie() {
		this.root = new Node('*');
	}

	public void addWord(String word) {
		this.addWord(this.root, word);
	}

	private void addWord(Node parent, String word) {

		if (word.length() == 0) {
			parent.eow = true;
			return;
		}

		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = parent.table.get(ch);

		if (child == null) {
			child = new Node(ch);
			parent.table.put(ch, child);
			addWord(child, row);
		} else {
			addWord(child, row);
		}

	}

	public boolean searchWord(String word) {
		return this.searchWord(this.root, word);
	}

	private boolean searchWord(Node parent, String word) {

		if (word.length() == 0) {
			return parent.eow;
		}

		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = parent.table.get(ch);

		if (child == null) {
			return false;
		} else {
			return searchWord(child, row);
		}

	}

	public void removeWord(String word) {
		this.removeWord(this.root, word);
	}

	private void removeWord(Node parent, String word) {

		if (word.length() == 0) {
			parent.eow = false;
			return;
		}

		char ch = word.charAt(0);
		String row = word.substring(1);

		Node child = parent.table.get(ch);

		if (child == null) {
			return;
		} else {
			removeWord(child, row);

			if (child.eow == false && child.table.size() == 0) {
				parent.table.remove(ch);
			}
		}

	}

	public void displayWords() {
		System.out.println("--------------------");
		displayWords(this.root, "");
		System.out.println("--------------------");
	}

	private void displayWords(Node node, String asf) {

		if (node.eow) {
			System.out.println(asf);
		}

		ArrayList<Character> keys = new ArrayList<>(node.table.keySet());

		for (char key : keys) {
			displayWords(node.table.get(key), asf + key);
		}
	}

	public void displayTrie() {
		displayTrie(this.root);
	}

	private void displayTrie(Node node) {

		ArrayList<Character> keys = new ArrayList<>(node.table.keySet());

		String str = node.ch + " -> ";

		for (char key : keys) {
			str += key + ", ";
		}

		str += ".";
		System.out.println(str);

		for (char key : keys) {
			displayTrie(node.table.get(key));
		}
	}
}
