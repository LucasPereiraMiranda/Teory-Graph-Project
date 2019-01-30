package br.graph.exhibition_algorithms;
import java.util.*;

import br.graph.base_of_graph.*;

public class AdjacencyListRepresentation implements ExhibitionAlgorithmsRequirements{

	private LinkedHashMap<Vertice, LinkedHashSet<Vertice>> m_Representation = new LinkedHashMap<>();
	private ArrayList<Edge> m_RepresentationEdge = new ArrayList<Edge>();
	private boolean m_IsDirect;
	private Graph m_AuxGraph;
	
	private ArrayList<Vertice> m_ArrayToOrder = new ArrayList<Vertice>();
	
	
	public AdjacencyListRepresentation(Graph p_Graph) {
		m_IsDirect = p_Graph.get_IsDirect();
		m_AuxGraph = p_Graph;
		
		/*Add vertices*/
		Vertice[] v_AuxArrayVertice = new Vertice [p_Graph.getGroupOfVertice().size()];
		
		/*to mapping in order in hashmap*/
		m_ArrayToOrder.addAll(m_AuxGraph.getGroupOfVertice());
		Collections.sort(m_ArrayToOrder);
		m_ArrayToOrder.toArray(v_AuxArrayVertice);
		for(int i=0;i<v_AuxArrayVertice.length;i++) {
			this.addVertice(v_AuxArrayVertice[i]);	
		}
		
		
		Edge[] v_AuxArrayEdges= new Edge[p_Graph.getGroupOfEdge().size()];
		p_Graph.getGroupOfEdge().toArray(v_AuxArrayEdges);
		
		
		for(int i=0;i<v_AuxArrayEdges.length;i++) {
			this.addEdge(v_AuxArrayEdges[i]);
		}
	}
	
	public int getVerticesNumber() {
		return m_AuxGraph.getNumberOfVertices();
	}

	
	public int getEdgeNumber() {
		return m_AuxGraph.getNumberOfEdges();
	}
	
	
	public Edge getEdge(Vertice v_VerticeSource, Vertice p_VerticeTarget) throws RuntimeException {
		Edge [] auxVet = new Edge[m_RepresentationEdge.size()];
		m_RepresentationEdge.toArray(auxVet);
		for(int i=0;i<m_RepresentationEdge.size();i++) {
			if(auxVet[i].getSource().getID()== v_VerticeSource.getID() && auxVet[i].getTarget().getID()==p_VerticeTarget.getID()) {
				return m_RepresentationEdge.get(i);
			}
		}
		throw new RuntimeException("This edge was not found in the chosen representation");
	}
	
	@Override
	public void addVertice(Vertice p_Vertice) {
		if(m_Representation.containsKey(p_Vertice))
			return;
		m_Representation.put(p_Vertice,new LinkedHashSet<Vertice>());
	}
	
	@Override
	public void addEdge(Edge p_Edge) {
		Vertice v_Source = p_Edge.getSource();
		addVertice(v_Source);
		Vertice v_Target = p_Edge.getTarget();
		addVertice(v_Target);
		
		if(!m_IsDirect) {
			m_Representation.get(v_Source).add(v_Target);
			m_Representation.get(v_Target).add(v_Source);
		} else {
			m_Representation.get(v_Source).add(v_Target);
		}
		
		if(!m_IsDirect) {
			m_RepresentationEdge.add(new Edge(p_Edge.getSource(),p_Edge.getTarget(),p_Edge.getWeight()));
			m_RepresentationEdge.add(new Edge(p_Edge.getTarget(),p_Edge.getSource(),p_Edge.getWeight()));
		}
		else {
			m_RepresentationEdge.add(p_Edge);
		}
	}
	
	@Override 
	public void printRepresentation() {
		System.out.println("\nAdjacency List Representation: ");
		for(Map.Entry<Vertice, LinkedHashSet<Vertice>> v_Print : m_Representation.entrySet()) {
			System.out.println(v_Print);
		}
	}
	
	/*to get neighbours*/
	public ArrayList<Vertice> neighbours(int p_ID) throws Exception {
		Vertice v_AuxVertice = new Vertice(p_ID);
		if(!m_Representation.containsKey(v_AuxVertice)) {
			throw new Exception("The graph not contains any vertice with This ID ="+p_ID);
		}
		ArrayList<Vertice> v_array = new ArrayList<Vertice>();
		v_array.addAll(m_Representation.get(v_AuxVertice));
		return v_array;
	}
}
