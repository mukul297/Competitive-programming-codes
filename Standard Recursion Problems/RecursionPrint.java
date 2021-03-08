public class RecursionPrint {

	public static void main(String[] args) {
		// System.out.println(printSS("abc", ""));
		// KPC("145", "");
		// System.out.println(permutationsA2("aba", ""));
		// lexicoCounting(0, 1000);
		// System.out.println(coin(3, ""));

		// System.out.println(coinNoConsecutiveHead(3, "", true));
		// System.out.println(boardPath(0, 10, ""));
		// System.out.println(mazePathD(0, 0, 2, 2, ""));

		// System.out.println(mazePathDMM(0, 0, 2, 2, ""));
		// System.out.println(nQueen(new boolean[4][4], 0, ""));

		int[] arr = { 1, 5, 7, 3, 0 };
		// targetSum(arr, 0, 60, "");
		// splitArray(arr, 0, 0, 0, "", "");
		System.out.println(TOH(3, "S", "D", "H"));

		// NKnights(new boolean[3][3], 0, 0, "", 0);
	}

	public static int printSS(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		char ch = ques.charAt(0);
		String ros = ques.substring(1);

		// System.out.println("hii" + ques);
		int no = printSS(ros, ans);
		int yes = printSS(ros, ans + ch);
		// System.out.println("bye" + ques);

		return no + yes;
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

	public static void KPC(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return;
		}

		char ch = ques.charAt(0);
		String ros = ques.substring(1);

		String code = getCode(ch);

		for (int i = 0; i < code.length(); i++) {
			KPC(ros, ans + code.charAt(i));
		}
	}

	public static int permutations(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		int res = 0;
		for (int i = 0; i < ques.length(); i++) {

			char ch = ques.charAt(i);
			String roq = ques.substring(0, i) + ques.substring(i + 1);

			res += permutations(roq, ans + ch);
		}

		return res;
	}

	public static void lexicoCounting(int curr, int end) {

		if (curr > end)
			return;

		System.out.println(curr);

		if (curr == 0) {
			for (int i = 1; i <= 9; i++) {
				lexicoCounting(curr * 10 + i, end);
			}
		} else {
			for (int i = 0; i <= 9; i++) {
				lexicoCounting(curr * 10 + i, end);
			}
		}
	}

	public static int permutationsRemoveDupli(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		boolean[] visited = new boolean[256];

		int res = 0;
		for (int i = 0; i < ques.length(); i++) {

			char ch = ques.charAt(i);

			if (visited[ch] == true)
				continue;

			String roq = ques.substring(0, i) + ques.substring(i + 1);

			res += permutationsRemoveDupli(roq, ans + ch);

			visited[ch] = true;
		}

		return res;
	}

	public static int permutationsRemoveDupliA2(String ques, String ans) {

		if (ques.length() == 0) {
			System.out.println(ans);
			return 1;
		}

		int res = 0;
		for (int i = 0; i < ques.length(); i++) {

			char ch = ques.charAt(i);
			boolean flag = true;

			for (int j = i + 1; j < ques.length(); j++) {
				if (ch == ques.charAt(j)) {
					flag = false;
					break;
				}
			}
			String roq = ques.substring(0, i) + ques.substring(i + 1);

			if (flag)
				res += permutationsRemoveDupliA2(roq, ans + ch);
		}

		return res;
	}

	public static int coin(int n, String ans) {

		if (n == 0) {
			System.out.println(ans);
			return 1;
		}

		int hc = coin(n - 1, ans + "H");
		int tc = coin(n - 1, ans + "T");

		return hc + tc;
	}

	public static int coinNoConsecutiveHead(int n, String ans, boolean wasLastHeadIncluded) {

		if (n == 0) {
			System.out.println(ans);
			return 1;

		}

		int ch = 0;
		int ct = 0;

		if (!wasLastHeadIncluded)
			ch = coinNoConsecutiveHead(n - 1, ans + "H", true);

		ct = coinNoConsecutiveHead(n - 1, ans + "T", false);

		return ch + ct;
	}

	public static int boardPath(int curr, int end, String ans) {

		if (curr == end) {
			System.out.println(ans);
			return 1;
		}

		if (curr > end) {
			return 0;
		}

		int res = 0;

		for (int dice = 1; dice <= 6; dice++) {
			res += boardPath(curr + dice, end, ans + dice);
		}

		return res;
	}

	public static int mazePath(int cr, int cc, int er, int ec, String ans) {

		if (cr == er && cc == ec) {
			System.out.println(ans);
			return 1;
		}

		if (cr > er || cc > ec) {
			return 0;
		}

		int ch = mazePath(cr, cc + 1, er, ec, ans + "H");
		int cv = mazePath(cr + 1, cc, er, ec, ans + "V");

		return ch + cv;
	}

	public static int mazePathD(int cr, int cc, int er, int ec, String ans) {

		if (cr == er && cc == ec) {
			System.out.println(ans);
			return 1;
		}

		if (cr > er || cc > ec) {
			return 0;
		}

		int ch = mazePathD(cr, cc + 1, er, ec, ans + "H");
		int cv = mazePathD(cr + 1, cc, er, ec, ans + "V");
		int cd = mazePathD(cr + 1, cc + 1, er, ec, ans + "D");

		return ch + cv + cd;
	}

	public static int mazePathDMM(int cr, int cc, int er, int ec, String ans) {

		if (cr == er && cc == ec) {
			System.out.println(ans);
			return 1;
		}

		if (cr > er || cc > ec) {
			return 0;
		}

		int ch = 0;
		int cv = 0;
		int cd = 0;

		// for (int moves = 1; moves <= ec; moves++) {
		// ch += mazePathDMM(cr, cc + moves, er, ec, ans + "H" + moves);
		// }
		//
		// for (int moves = 1; moves <= er; moves++) {
		// cv += mazePathDMM(cr + moves, cc, er, ec, ans + "V" + moves);
		// }
		//
		// for (int moves = 1; moves <= er && moves <= ec; moves++) {
		// cd += mazePathDMM(cr + moves, cc + moves, er, ec, ans + "D" + moves);
		// }

		for (int moves = 1; moves <= ec || moves <= er; moves++) {
			ch += mazePathDMM(cr, cc + moves, er, ec, ans + "H" + moves);
			cv += mazePathDMM(cr + moves, cc, er, ec, ans + "V" + moves);
			cd += mazePathDMM(cr + moves, cc + moves, er, ec, ans + "D" + moves);
		}

		return ch + cv + cd;
	}

	public static void targetSum(int[] arr, int vidx, int sum, int target, String asf) {

		if (vidx == arr.length) {

			if (target == sum) {
				System.out.println(asf);
			}

			return;
		}

		targetSum(arr, vidx + 1, sum, target, asf);
		targetSum(arr, vidx + 1, sum + arr[vidx], target, asf + " " + arr[vidx]);
	}

	public static int nQueen(boolean[][] board, int row, String asf) {

		if (row == board.length) {
			System.out.println(asf);
			return 1;
		}

		int res = 0;

		for (int col = 0; col < board[0].length; col++) {
			if (isItSafe(board, row, col)) {
				board[row][col] = true;
				res += nQueen(board, row + 1, asf + "{" + row + "," + col + "}");
				board[row][col] = false;
			}
		}

		return res;
	}

	public static boolean isItSafe(boolean[][] board, int row, int col) {

		int r = row;
		int c = col;

		// vertically
		while (r >= 0) {
			if (board[r][c] == true)
				return false;

			r--;
		}

		// diagonally left
		r = row;
		c = col;
		while (r >= 0 && c >= 0) {
			if (board[r][c] == true)
				return false;

			r--;
			c--;
		}

		// diagonally right
		r = row;
		c = col;
		while (r >= 0 && c < board[0].length) {
			if (board[r][c] == true)
				return false;

			r--;
			c++;
		}

		return true;

	}

	public static void splitArray(int[] arr, int vidx, int g1sum, int g2sum, String g1asf, String g2asf) {

		if (vidx == arr.length) {

			if (g1sum == g2sum) {
				System.out.println(g1asf + " and " + g2asf);

			}
			return;
		}
		splitArray(arr, vidx + 1, g1sum + arr[vidx], g2sum, g1asf + "{" + arr[vidx] + "}", g2asf);
		splitArray(arr, vidx + 1, g1sum, g2sum + arr[vidx], g1asf, g2asf + "{" + arr[vidx] + "}");
	}

	public static int TOH(int n, String src, String dst, String helper) {

		if (n == 0) {
			return 0;
		}

		int c = TOH(n - 1, src, helper, dst);
		System.out.println("Move " + n + " disc from " + src + " to " + dst);
		c += TOH(n - 1, helper, dst, src);

		return c + 1;
	}

	public static void NKnights(boolean[][] board, int row, int col, String asf, int kpsf) {

		if (kpsf == board.length) {
			System.out.println(asf);
			return;
		}

		for (int c = col; c < board[0].length; c++) {
			if (isitsafeknight(board, row, c)) {
				board[row][c] = true;
				NKnights(board, row, c + 1, asf + "{" + row + ", " + c + "}", kpsf + 1);
				board[row][c] = false;
			}
		}

		for (int r = row + 1; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (isitsafeknight(board, r, c)) {
					board[r][c] = true;
					NKnights(board, r, c + 1, asf + "{" + r + ", " + c + "}", kpsf + 1);
					board[r][c] = false;
				}
			}
		}
	}

	public static boolean isitsafeknight(boolean[][] board, int r, int c) {
		if (r - 1 >= 0 && c - 2 >= 0 && board[r - 1][c - 2]) {
			return false;
		}

		if (r - 1 >= 0 && c + 2 < board.length && board[r - 1][c + 2]) {
			return false;
		}

		if (r - 2 >= 0 && c - 1 >= 0 && board[r - 2][c - 1]) {
			return false;
		}

		if (r - 2 >= 0 && c + 1 < board.length && board[r - 2][c + 1]) {
			return false;
		}

		return true;
	}

}
