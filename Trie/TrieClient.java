
public class TrieClient {

	public static void main(String[] args) {

		Trie trie = new Trie();
		trie.addWord("be");
		trie.addWord("bee");
		trie.addWord("been");
		trie.addWord("an");
		trie.addWord("ant");
		trie.addWord("and");

		trie.displayWords();
		trie.displayTrie();

		trie.removeWord("bee");
		trie.displayWords();
		System.out.println(trie.searchWord("been"));
		trie.removeWord("been");
		trie.displayWords();
		trie.removeWord("be");
		trie.displayWords();

	}

}
