import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Mukul Sharma
 */

public class Graph {

	private class Vertex {
		HashMap<String, Integer> nbrs = new HashMap<>();
	}

	HashMap<String, Vertex> vertices = new HashMap<>();

	public int numVertex() {
		return this.vertices.size();
	}

	public boolean containsVertex(String vname) {
		return this.vertices.containsKey(vname);
	}

	public void addVertex(String vname) {

		if (this.vertices.containsKey(vname)) {
			return;
		}

		Vertex vtx = new Vertex();
		this.vertices.put(vname, vtx);
	}

	public void removeVertex(String vname) {

		Vertex vtx = this.vertices.get(vname);

		ArrayList<String> nbrs = new ArrayList<>(vtx.nbrs.keySet());

		for (String nbr : nbrs) {
			removeEdge(nbr, vname);
		}

		this.vertices.remove(vname);
	}

	public int numEdge() {

		int ans = 0;

		ArrayList<String> keys = new ArrayList<>(this.vertices.keySet());

		for (String key : keys) {
			Vertex vtx = vertices.get(key);
			ans += vtx.nbrs.size();
		}

		return ans / 2;
	}

	public boolean containsEdge(String vname1, String vname2) {

		Vertex vtx1 = vertices.get(vname1);
		Vertex vtx2 = vertices.get(vname2);

		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return false;
		}

		return true;

	}

	public void addEdge(String vname1, String vname2, int cost) {

		Vertex vtx1 = vertices.get(vname1);
		Vertex vtx2 = vertices.get(vname2);

		if (vtx1 == null || vtx2 == null || vtx1.nbrs.containsKey(vname2)) {
			return;
		}

		vtx1.nbrs.put(vname2, cost);
		vtx2.nbrs.put(vname1, cost);

	}

	public void removeEdge(String vname1, String vname2) {

		Vertex vtx1 = vertices.get(vname1);
		Vertex vtx2 = vertices.get(vname2);

		if (vtx1 == null || vtx2 == null || !vtx1.nbrs.containsKey(vname2)) {
			return;
		}

		vtx1.nbrs.remove(vname2);
		vtx2.nbrs.remove(vname1);
	}

	public void display() {

		System.out.println("---------------------------");

		ArrayList<String> keys = new ArrayList<>(this.vertices.keySet());

		for (String key : keys) {

			String str = key + " -> ";

			Vertex vtx = this.vertices.get(key);
			str += vtx.nbrs;

			System.out.println(str);
		}

		System.out.println("---------------------------");
	}

	public boolean hasPath(String src, String dst, HashMap<String, Boolean> processed) {

		// processing
		processed.put(src, true);

		// direct edge
		if (containsEdge(src, dst)) {
			return true;
		}

		// nbrs
		Vertex vtx = vertices.get(src);
		ArrayList<String> nbrs = new ArrayList<>(vtx.nbrs.keySet());

		for (String nbr : nbrs) {
			if (!processed.containsKey(nbr) && hasPath(nbr, dst, processed)) {
				return true;
			}
		}

		return false;

	}

	private class Pair {

		String vname;
		String psf;
		Vertex vtx;
		String color;

		public Pair(String vname, String psf, Vertex vtx) {
			this.vname = vname;
			this.psf = psf;
			this.vtx = vtx;
		}

		public Pair(String vname, String psf, Vertex vtx, String color) {
			this.vname = vname;
			this.psf = psf;
			this.vtx = vtx;
			this.color = color;
		}
	}

	public boolean bfs(String src, String dst) {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		// make a starting pair
		Pair np = new Pair(src, src, vertices.get(src));
		queue.addLast(np);

		// queue is not empty
		while (!queue.isEmpty()) {

			// remove the first element
			Pair rp = queue.removeFirst();

			// process only unprocessed
			if (processed.containsKey(rp.vname)) {
				continue;
			}

			// processing true
			processed.put(rp.vname, true);

			// direct edge
			if (containsEdge(rp.vname, dst)) {
				System.out.println(rp.psf + dst);
				return true;
			}

			// nbrs
			ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

			// loop on nbrs
			for (String nbr : nbrs) {

				// process only unprocessed nbrs
				if (!processed.containsKey(nbr)) {
					// make a pair of nbr and put in queue
					Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
					queue.addLast(nbrPair);
				}
			}

		}

		return false;
	}

	public boolean dfs(String src, String dst) {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> stack = new LinkedList<>();
		// make a starting pair
		Pair np = new Pair(src, src, vertices.get(src));
		stack.addFirst(np);

		// queue is not empty
		while (!stack.isEmpty()) {

			// remove the first element
			Pair rp = stack.removeFirst();

			// process only unprocessed
			if (processed.containsKey(rp.vname)) {
				continue;
			}

			// processing true
			processed.put(rp.vname, true);

			// direct edge
			if (containsEdge(rp.vname, dst)) {
				System.out.println(rp.psf + dst);
				return true;
			}

			// nbrs
			ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

			// loop on nbrs
			for (String nbr : nbrs) {

				// process only unprocessed nbrs
				if (!processed.containsKey(nbr)) {
					// make a pair of nbr and put in queue
					Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
					stack.addFirst(nbrPair);
				}
			}

		}

		return false;
	}

	public void bft() {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vertices.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key));
			queue.addLast(np);

			// queue is not empty
			while (!queue.isEmpty()) {

				// remove the first element
				Pair rp = queue.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {
					continue;
				}

				// processing true
				processed.put(rp.vname, true);

				// syso
				System.out.println(rp.vname + " via " + rp.psf);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
						queue.addLast(nbrPair);
					}
				}

			}
		}

	}

	public void dft() {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> stack = new LinkedList<>();

		ArrayList<String> keys = new ArrayList<>(this.vertices.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key));
			stack.addFirst(np);

			// queue is not empty
			while (!stack.isEmpty()) {

				// remove the first element
				Pair rp = stack.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {
					continue;
				}

				// processing true
				processed.put(rp.vname, true);

				// syso
				System.out.println(rp.vname + " via " + rp.psf);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
						stack.addFirst(nbrPair);
					}
				}

			}

		}
	}

	public boolean isConnected() {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vertices.keySet());

		int flag = 0;

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			flag++;

			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key));
			queue.addLast(np);

			// queue is not empty
			while (!queue.isEmpty()) {

				// remove the first element
				Pair rp = queue.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {
					continue;
				}

				// processing true
				processed.put(rp.vname, true);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
						queue.addLast(nbrPair);
					}
				}

			}
		}

		if (flag >= 2) {
			return false;
		} else {
			return true;
		}

	}

	public boolean isCyclic() {

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vertices.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key));
			queue.addLast(np);

			// queue is not empty
			while (!queue.isEmpty()) {

				// remove the first element
				Pair rp = queue.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {
					return true;
				}

				// processing true
				processed.put(rp.vname, true);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
						queue.addLast(nbrPair);
					}
				}

			}
		}

		return false;

	}

	public ArrayList<ArrayList<String>> getCC() {

		ArrayList<ArrayList<String>> ans = new ArrayList<>();

		HashMap<String, Boolean> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vertices.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			ArrayList<String> subans = new ArrayList<>();
			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key));
			queue.addLast(np);

			// queue is not empty
			while (!queue.isEmpty()) {

				// remove the first element
				Pair rp = queue.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {
					continue;
				}

				// processing true
				processed.put(rp.vname, true);

				// syso
				// System.out.println(rp.vname + " via " + rp.psf);
				subans.add(rp.vname);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr));
						queue.addLast(nbrPair);
					}
				}

			}

			ans.add(subans);
		}

		return ans;

	}

	public boolean isTree() {
		return isConnected() && !isCyclic();
	}

	public boolean isBipartite() {

		HashMap<String, String> processed = new HashMap<>();

		LinkedList<Pair> queue = new LinkedList<>();
		ArrayList<String> keys = new ArrayList<>(vertices.keySet());

		for (String key : keys) {

			if (processed.containsKey(key)) {
				continue;
			}

			// make a starting pair
			Pair np = new Pair(key, key, vertices.get(key), "r");
			queue.addLast(np);

			// queue is not empty
			while (!queue.isEmpty()) {

				// remove the first element
				Pair rp = queue.removeFirst();

				// process only unprocessed
				if (processed.containsKey(rp.vname)) {

					String oc = processed.get(rp.vname);
					String nc = rp.color;

					if (!oc.equals(nc)) {
						return false;
					}

					continue;
				}

				// processing true
				processed.put(rp.vname, rp.color);

				// nbrs
				ArrayList<String> nbrs = new ArrayList<>(rp.vtx.nbrs.keySet());

				// loop on nbrs
				for (String nbr : nbrs) {

					// process only unprocessed nbrs
					if (!processed.containsKey(nbr)) {
						// make a pair of nbr and put in queue
						String clr = rp.color.equals("r") ? "g" : "r";
						Pair nbrPair = new Pair(nbr, rp.psf + nbr, vertices.get(nbr), clr);
						queue.addLast(nbrPair);
					}
				}

			}
		}

		return true;

	}

}
