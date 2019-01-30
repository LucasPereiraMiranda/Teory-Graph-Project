
/*
 * Program in java for work and manipulate graphs in the DIMACS format
 * 
 *	file dimacs format
 * 	
 * number_of_vertices number_of_edges 
 * vertice_souce1 vertice_target1 edge_weight1
 * vertice_souce2 vertice_target2 edge_weight2
 * vertice_souce3 vertice_target3 edge_weight3
 * vertice_souce4 vertice_target4 edge_weight4
 * vertice_souce5 vertice_target5 edge_weight5
 * ...
 *  
 * ex:
 *  
 *5 6
 *1 2 2
 *1 4 2
 *2 3 5
 *3 4 1
 *4 5 3
 *5 1 4
 */

import br.graph.base_of_graph.*;
import br.graph.exhibition_algorithms.*;
import br.graph.search_algorithms.BreadthFirstSearch;
import br.graph.search_algorithms.DeepFirstSearch;


public class Controller {
	
	public static void main(String args[]) {
		
		try {	
			/*----------------------------------------------------------------------------------------*/
							/*data file in the dimacs format*/
			
			
			String V_FileName = "dimacsFileGraph.txt";

			
			/*----------------------------------------------------------------------------------------*/
							/*graph*/
			
			
			Graph v_Graph = new Graph(true);
			v_Graph = v_Graph.readDimacs(V_FileName);
			
			
			/*Add Edges in the graph...*/
			
			//v_Graph.addEdge(new Edge(new Vertice(5),new Vertice(3)));
			//v_Graph.addEdge(new Edge(new Vertice(3),new Vertice(7)));
			
			
			System.out.println("Printing the new numbers of edges: "+v_Graph.getNumberOfEdges());
	

			System.out.println("Set of Edges in the graph: "+v_Graph.getGroupOfEdge());
			
			/*----------------------------------------------------------------------------------------*/
				
							/*adjacency list representation*/
				
			AdjacencyListRepresentation v_AdjacencyList = new AdjacencyListRepresentation(v_Graph);
			
			v_AdjacencyList.printRepresentation();
			

			/*----------------------------------------------------------------------------------------*/
							/*adjacency matrix representation*/
			
			AdjacencyMatrixRepresentation v_AdjacencyMatrix = new AdjacencyMatrixRepresentation(v_Graph);
			
			v_AdjacencyMatrix.printRepresentation();
			
			
			/*----------------------------------------------------------------------------------------*/
							/*incidency matrix representation*/
			
			IncidencyMatrixRepresentation v_IncidencyMatrix = new IncidencyMatrixRepresentation(v_Graph);
			
			v_IncidencyMatrix.printRepresentation();
			
			/*----------------------------------------------------------------------------------------*/
							/*depth search*/
			
			Vertice v_ChosenVertice = new Vertice(1);
			
			DeepFirstSearch v_ObjectDeepSearch = new DeepFirstSearch(v_Graph);
			
			v_ObjectDeepSearch.search(v_ChosenVertice);
			
			v_ObjectDeepSearch.print();
			
			/*----------------------------------------------------------------------------------------*/
							/*breadth search*/
			
			BreadthFirstSearch v_Bfs = new BreadthFirstSearch(v_Graph);
			
			v_Bfs.search(v_ChosenVertice);
			
			v_Bfs.print();
			
			
			/*----------------------------------------------------------------------------------------*/
							/*this graph is completed?*/
			
			System.out.println("\nActualy this graph is completed? "+v_Graph.isCompleted(v_Graph));
			
			
			/*----------------------------------------------------------------------------------------*/
							/*this graph is eulerian?*/

			System.out.println("\nThis graph is eulerian? "+ v_Graph.isEulerian(v_Graph));


			/*----------------------------------------------------------------------------------------*/
							/*this graph is connected?*/

			System.out.println("\nThis graph is connected? "+ v_Graph.isConnected(v_Graph));

			/*----------------------------------------------------------------------------------------*/
							/*number of components connecteds in this graph?*/

			System.out.println("\nThe number of components connecteds in this graph is: "+ v_Graph.getNumberComponentsConnected(v_Graph));
			
			
			/*----------------------------------------------------------------------------------------*/
							/*completing this graph*/
			
			/*v_Graph = v_Graph.completingGraph(v_Graph);
			System.out.println("\nActualy this graph is completed? "+v_Graph.isCompleted(v_Graph));
			*/
			
			/*case print adjacencymatrix now,  he will be completed*/
			
			/*
			AdjacencyMatrixRepresentation v_AdjacencyMatrix2 = new AdjacencyMatrixRepresentation(v_Graph);
			v_AdjacencyMatrix2.printRepresentation();
			*/
			
			/*----------------------------------------------------------------------------------------*/
							/*DIJKSTRA*/
			
			/*Implement minimun path(dijkstra)*/
			
			
			/*----------------------------------------------------------------------------------------*/
							/*FLEURY*/
	
			/*Implement eulerian circuit(fleury)*/
			

		}
		catch(ArrayIndexOutOfBoundsException v_Exception) {
			System.out.println(v_Exception.getMessage());
			v_Exception.printStackTrace();
		}
		catch(ArithmeticException v_Exception) {
			System.out.println(v_Exception.getMessage());
			v_Exception.printStackTrace();
		}
		catch(IllegalArgumentException v_Exception) {
			System.out.println(v_Exception.getMessage());
		}
		catch(NullPointerException v_Exception) {	
			System.out.println(v_Exception.getMessage());
			v_Exception.printStackTrace();
		}
		catch(RuntimeException v_Exception) {
			System.out.println(v_Exception.getMessage());
			v_Exception.printStackTrace();
		}
		catch(Exception v_Exception) {
			System.out.println(v_Exception.getMessage());
			v_Exception.printStackTrace();
		}
	}
}
