
public class HuffmanClient {

	public static void main(String[] args) {

		HuffmanCoding huffman = new HuffmanCoding("aaaaabbbbccd");

		System.out.println(huffman.encoding("abc"));
		System.out.println(huffman.decoding("011101"));

	}

}
