import java.util.HashMap;

/**
 * @author Mukul Sharma
 */

public class GraphClient {

	public static void main(String[] args) {

		Graph graph = new Graph();
		graph.addVertex("A");
		graph.addVertex("B");
		graph.addVertex("C");
		graph.addVertex("D");
		graph.addVertex("E");
		graph.addVertex("F");
		graph.addVertex("G");
		graph.addEdge("A", "B", 1);
		graph.addEdge("A", "D", 10);
		graph.addEdge("B", "C", 2);
		graph.addEdge("C", "D", 3);
		graph.addEdge("D", "E", 4);
		graph.addEdge("E", "F", 5);
		graph.addEdge("E", "G", 12);
		graph.addEdge("F", "G", 4);

		// System.out.println(graph.containsEdge("A", "B"));
		// System.out.println(graph.containsEdge("X", "B"));
		// System.out.println(graph.numVertex());
		// System.out.println(graph.numEdge());

		graph.display();

		// graph.removeEdge("D", "E");
		// graph.removeEdge("B", "C");
		graph.removeEdge("F", "G");
		// graph.removeVertex("B");
		// graph.display();

		// System.out.println(graph.hasPath("A", "G", new HashMap<>()));
		// System.out.println(graph.bfs("A", "G"));
		// System.out.println(graph.dfs("A", "G"));
		// graph.bft();
		// graph.dft();

		// System.out.println(graph.isCyclic());
		// System.out.println(graph.isConnected());
		// System.out.println(graph.getCC());
		// System.out.println(graph.isTree());

		System.out.println(graph.isBipartite());
	}

}
