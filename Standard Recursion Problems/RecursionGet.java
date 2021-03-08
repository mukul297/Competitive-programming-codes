import java.util.ArrayList;
public class RecursionGet {

	public static void main(String[] args) {

		// System.out.println(getSSAscii("abc"));
		// System.out.println(KPC("145"));
		// System.out.println(Permutations("abc"));
		// System.out.println(BoardPath(0, 10));

		System.out.println(MazePathDMM(0, 0, 2, 2));
	}

	public static ArrayList<String> getSS(String str) {

		if (str.length() == 0) {

			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		char ch = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> rr = getSS(ros);
		ArrayList<String> mr = new ArrayList<>();

		for (String rrs : rr) {
			mr.add(rrs);
			mr.add(ch + rrs);
		}

		// for (int i = 0; i < rr.size(); i++) {
		// String rrs = rr.get(i);
		// mr.add(rrs);
		// mr.add(ch + rrs);
		// }

		return mr;

	}

	public static ArrayList<String> getSSAscii(String str) {

		if (str.length() == 0) {

			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		char ch = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> rr = getSSAscii(ros);
		ArrayList<String> mr = new ArrayList<>();

		for (String rrs : rr) {
			mr.add(rrs);
			mr.add(ch + rrs);
			mr.add((int) ch + rrs);
		}

		return mr;

	}

	public static ArrayList<String> KPC(String str) {

		if (str.length() == 0) {

			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		char ch = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> rr = KPC(ros);
		ArrayList<String> mr = new ArrayList<>();

		String code = getCode(ch);

		for (int i = 0; i < code.length(); i++) {

			for (String rrs : rr) {

				mr.add(code.charAt(i) + rrs);
			}
		}

		return mr;

	}

	public static String getCode(char ch) {

		if (ch == '1')
			return "abc";
		else if (ch == '2')
			return "def";
		else if (ch == '3')
			return "ghi";
		else if (ch == '4')
			return "jk";
		else if (ch == '5')
			return "lmno";
		else if (ch == '6')
			return "pqr";
		else if (ch == '7')
			return "stu";
		else if (ch == '8')
			return "vwx";
		else if (ch == '9')
			return "yz";
		else if (ch == '0')
			return "@#";
		else
			return "";
	}

	public static ArrayList<String> Permutations(String str) {

		if (str.length() == 0) {

			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		char ch = str.charAt(0);
		String ros = str.substring(1);

		ArrayList<String> rr = Permutations(ros);
		ArrayList<String> mr = new ArrayList<>();

		for (String rrs : rr) {

			for (int i = 0; i <= rrs.length(); i++) {

				String val = rrs.substring(0, i) + ch + rrs.substring(i);
				mr.add(val);
			}
		}

		return mr;

	}

	public static ArrayList<String> BoardPath(int curr, int end) {

		if (curr == end) {
			ArrayList<String> br = new ArrayList<>();
			br.add("\n");
			return br;
		}

		if (curr > end) {
			ArrayList<String> br = new ArrayList<>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<>();

		for (int dice = 1; dice <= 6; dice++) {
			ArrayList<String> rr = BoardPath(curr + dice, end);

			for (int i = 0; i < rr.size(); i++) {
				String rr1s = rr.get(i);

				mr.add(dice + rr1s);
			}
		}

		return mr;

	}

	public static ArrayList<String> MazePath(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		if (cr > er || cc > ec) {
			ArrayList<String> br = new ArrayList<>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<>();

		ArrayList<String> rrh = MazePath(cr, cc + 1, er, ec);

		for (String rrhs : rrh) {
			mr.add("H" + rrhs);
		}

		ArrayList<String> rrv = MazePath(cr + 1, cc, er, ec);

		for (String rrvs : rrv) {
			mr.add("V" + rrvs);
		}

		return mr;

	}

	public static ArrayList<String> MazePathD(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		if (cr > er || cc > ec) {
			ArrayList<String> br = new ArrayList<>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<>();

		ArrayList<String> rrh = MazePathD(cr, cc + 1, er, ec);

		for (String rrhs : rrh) {
			mr.add("H" + rrhs);
		}

		ArrayList<String> rrv = MazePathD(cr + 1, cc, er, ec);

		for (String rrvs : rrv) {
			mr.add("V" + rrvs);
		}

		ArrayList<String> rrd = MazePathD(cr + 1, cc + 1, er, ec);

		for (String rrds : rrd) {
			mr.add("D" + rrds);
		}

		return mr;
	}

	public static ArrayList<String> MazePathDMM(int cr, int cc, int er, int ec) {

		if (cr == er && cc == ec) {
			ArrayList<String> br = new ArrayList<>();
			br.add("");
			return br;
		}

		if (cr > er || cc > ec) {
			ArrayList<String> br = new ArrayList<>();
			return br;
		}

		ArrayList<String> mr = new ArrayList<>();

		for (int move = 1; move <= ec; move++) {
			ArrayList<String> rrh = MazePathDMM(cr, cc + move, er, ec);

			for (String rrhs : rrh) {
				mr.add("H" + move + rrhs);
			}
		}

		for (int move = 1; move <= er; move++) {
			ArrayList<String> rrv = MazePathDMM(cr + move, cc, er, ec);

			for (String rrvs : rrv) {
				mr.add("V" + move + rrvs);
			}
		}
		for (int move = 1; move <= er && move <= ec; move++) {
			ArrayList<String> rrd = MazePathDMM(cr + move, cc + move, er, ec);

			for (String rrds : rrd) {
				mr.add("D" + move + rrds);
			}
		}
		return mr;
	}
}
