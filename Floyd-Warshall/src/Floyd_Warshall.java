import java.util.Stack;

public class Floyd_Warshall 
{
	private boolean hasNegativeCycle;  
	private double[][] distTo;         
	private DirectedEdge[][] edgeTo;   


	public Floyd_Warshall (AdjMatrixEdgeWeightedDigraph G) 
	{
		int V = G.V();
		distTo = new double[V][V];
		edgeTo = new DirectedEdge[V][V];

		for (int v = 0; v < V; v++) {
			for (int w = 0; w < V; w++) {
				distTo[v][w] = Double.POSITIVE_INFINITY;
			}
		}

		for (int v = 0; v < G.V(); v++) {

			for (DirectedEdge e : G.adj(v)) {
				distTo[e.from()][e.to()] = e.weight();
				edgeTo[e.from()][e.to()] = e;
			}

			if (distTo[v][v] >= 0.0) {
				distTo[v][v] = 0.0;
				edgeTo[v][v] = null;
			}
		}

		// Floyd-Warshall updates

		for (int i = 0; i < V; i++) {

			for (int v = 0; v < V; v++) {

				if (edgeTo[v][i] == null) continue;  

				for (int w = 0; w < V; w++) {
					if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
						distTo[v][w] = distTo[v][i] + distTo[i][w];
						edgeTo[v][w] = edgeTo[i][w];
					}
				}

				if (distTo[v][v] < 0.0) {
					hasNegativeCycle = true;
					return;
				}
			}
		}

	}

	public boolean hasNegativeCycle() {
		return hasNegativeCycle;
	}

	private void validateVertex(int v) {
		int V = distTo.length;
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
	}

	public boolean hasPath(int s, int t) {
		validateVertex(s);
		validateVertex(t);
		return distTo[s][t] < Double.POSITIVE_INFINITY;
	}

	public double dist(int s, int t) {
		validateVertex(s);
		validateVertex(t);
		if (hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		return distTo[s][t];
	}

	public Iterable<DirectedEdge> path(int s, int t) {
		validateVertex(s);
		validateVertex(t);
		if (hasNegativeCycle())
			throw new UnsupportedOperationException("Negative cost cycle exists");
		if (!hasPath(s, t)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[s][t]; e != null; e = edgeTo[s][e.from()]) {
			path.push(e);
		}
		return path;
	}

}

