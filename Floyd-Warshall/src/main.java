import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {

		int V = 7;
		AdjMatrixEdgeWeightedDigraph G = new AdjMatrixEdgeWeightedDigraph(V);

		double weight = 0.0;

		FileReader f = new FileReader("./data/arcos.txt");
		BufferedReader b = new BufferedReader(f);
		String cadena = b.readLine();

		while( cadena != null) 
		{

			String[] partes = cadena.split(",");
			int v = Integer.parseInt(partes[0].trim());
			int w = Integer.parseInt(partes[1].trim());
			weight = Double.parseDouble(partes[2].trim());

			if (v == w) G.addEdge(new DirectedEdge(v, w, Math.abs(weight)));
			else G.addEdge(new DirectedEdge(v, w, weight));

			cadena = b.readLine();
		}

		b.close(); 

		// Run Floyd-Warshall algorithm
		Floyd_Warshall spt = new Floyd_Warshall(G);

		// Print all-pairs shortest path distances
		
		StdOut.printf("  ");
		for (int v = 0; v < G.V(); v++) {
			StdOut.printf("%6d ", v);
		}
		StdOut.println();
		for (int v = 0; v < G.V(); v++) {
			StdOut.printf("%3d: ", v);
			for (int w = 0; w < G.V(); w++) {
				if (spt.hasPath(v, w)) StdOut.printf("%6.2f ", spt.dist(v, w));
				else StdOut.printf("  Inf ");
			}
			StdOut.println();
		}

	}

}


