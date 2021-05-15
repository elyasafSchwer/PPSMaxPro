
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

public class GraphTools {
	public static void add_vertex_with_neighbor(Graph<Integer, DefaultWeightedEdge> all_graph, Graph<Integer, DefaultWeightedEdge> sub_graph, int v){
		sub_graph.addVertex(v);
		for (int u : Graphs.neighborListOf(all_graph, v)) {
			sub_graph.addVertex(u);
			sub_graph.addEdge(v, u, all_graph.getEdge(v, u));
		}
	}
	
	public static double get_path_cost(Path path){
		SimpleWeightedGraph<Integer, DefaultWeightedEdge> graph = (SimpleWeightedGraph<Integer, DefaultWeightedEdge>) path.getGraph();
		double result = 0;
		for (int i = 0; i < path.getLength() - 1; i++) {
			result += graph.getEdgeWeight(graph.getEdge(path.getVertexList().get(i), path.getVertexList().get(i+1)));
		}
		return result;
	}
	
	public static SimpleWeightedGraph<Integer, DefaultWeightedEdge> get_sub_graph_until_money_over(Graph<Integer, DefaultWeightedEdge> graph, int source, double money){
		SimpleWeightedGraph<Integer, DefaultWeightedEdge> result = new SimpleWeightedGraph<Integer, DefaultWeightedEdge> (DefaultWeightedEdge.class);
		get_sub_graph_until_money_over(graph, result, source, money);
		return result;
	}

	private static void get_sub_graph_until_money_over(Graph<Integer, DefaultWeightedEdge> all_graph, Graph<Integer, DefaultWeightedEdge> result, int v, double money) {
		if(money < 0) return;
		result.addVertex(v);
		for (int u : Graphs.neighborListOf(all_graph, v)) {
			if(result.vertexSet().contains(u)) continue;
			result.addVertex(u);
			result.addEdge(v, u, all_graph.getEdge(v, u));
			get_sub_graph_until_money_over(all_graph, result, u, money - all_graph.getEdgeWeight(all_graph.getEdge(v, u)));
		}
	}
}
