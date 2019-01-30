package br.graph.base_of_graph;

import java.util.*;

import br.graph.exhibition_algorithms.AdjacencyListRepresentation;
import br.graph.exhibition_algorithms.AdjacencyMatrixRepresentation;
import br.graph.handling_files.Reader;
import br.graph.search_algorithms.DeepFirstSearch;

public class Graph {
	
	/*sets of vertices and edges*/
	private LinkedHashSet<Vertice> m_GroupOfVertice = new LinkedHashSet<Vertice>() ;
	private LinkedHashSet<Edge> m_GroupOfEdge = new LinkedHashSet<Edge>();
	
	private boolean m_IsDirect;
	
	public Graph(boolean p_IsDirect) throws Exception {
		m_IsDirect = p_IsDirect;
	}

	public Graph(LinkedHashSet<Vertice> p_GroupOfVertice,LinkedHashSet<Edge> p_GroupOfEdge,boolean p_IsDirect) {		
		this.m_GroupOfVertice = p_GroupOfVertice;
		this.m_GroupOfEdge = p_GroupOfEdge;
		this.m_IsDirect =  p_IsDirect;			
	}

	public Graph readDimacs(String p_Patch)throws Exception {
		Reader v_Reader = new Reader(p_Patch,m_IsDirect);
		return v_Reader.readerDimacs();
	}
	
	
	public int getNumberOfVertices() {
		return m_GroupOfVertice.size();
	}
	
	public int getNumberOfEdges() {
		return m_GroupOfEdge.size();
	}
	
	public LinkedHashSet<Vertice> getGroupOfVertice() {
		return m_GroupOfVertice;
	}
	
	public LinkedHashSet<Edge> getGroupOfEdge() {
		return m_GroupOfEdge;
	}
	
	public boolean get_IsDirect() {
		return m_IsDirect;
	}
	
	public boolean isConnected(Graph p_Graph) throws Exception {
		ArrayList<Vertice> v_toOrderArray = new ArrayList<Vertice>();
		v_toOrderArray.addAll(p_Graph.getGroupOfVertice());
		Collections.sort(v_toOrderArray);
		DeepFirstSearch v_DFS = new DeepFirstSearch(p_Graph);
		
		v_DFS.search(v_toOrderArray.get(0));
		
		return v_DFS.getIsConnected();
	}
	
	public int getNumberComponentsConnected(Graph p_Graph) throws Exception {
		ArrayList<Vertice> v_toOrderArray = new ArrayList<Vertice>();
		v_toOrderArray.addAll(p_Graph.getGroupOfVertice());
		Collections.sort(v_toOrderArray);
		DeepFirstSearch v_DFS = new DeepFirstSearch(p_Graph);
		
		v_DFS.search(v_toOrderArray.get(0));
		
		return v_DFS.getNumberComponentsConnecteds();
	}
	
	public boolean isCompleted(Graph p_Graph) {
		boolean v_IsCompleted=true;
		AdjacencyMatrixRepresentation m_AuxAdjacencyMatrix = new AdjacencyMatrixRepresentation(p_Graph);
		int [][] v_TemporaryAdjacencyMatrix1 = m_AuxAdjacencyMatrix.getAdjacencyMatrix();
		for(int i=0;i<p_Graph.getNumberOfVertices();i++) {
			for(int j=0;j<p_Graph.getNumberOfVertices();j++) {
				if(i!=j) {
					continue;
				}
				else {
					v_TemporaryAdjacencyMatrix1[i][j]=0;
					v_IsCompleted=false;
					return v_IsCompleted;
				}
			}
		}
		return v_IsCompleted;
	}
	
	public Graph completingGraph(Graph p_Graph) {
		for(int i=0;i<m_GroupOfVertice.size();i++) {
			for(int j=0;j<m_GroupOfVertice.size();j++) {
				if(i==j) {
					p_Graph.removeEdge(new Vertice(i), new Vertice(j));
				}
				else {
					p_Graph.addEdge(new Edge(new Vertice(i),new Vertice(j)));
					p_Graph.addEdge(new Edge(new Vertice(j),new Vertice(i)));
					p_Graph.addVertice(new Vertice(i));
					p_Graph.addVertice(new Vertice(j));
				}
			}
		}
		return p_Graph;
	}

	public void addVertice(Vertice p_Vertice) {
		if(m_GroupOfVertice.contains(p_Vertice)) {
			return;
		}
		m_GroupOfVertice.add(p_Vertice);
	}

	public void addEdge(Edge p_Edge) {
		if(m_GroupOfEdge.contains(p_Edge))
			return;
		m_GroupOfEdge.add(p_Edge);
		
		this.addVertice(p_Edge.getSource());
		this.addVertice(p_Edge.getTarget());
	}
	
	public ArrayList<Vertice> getNeighbours(Graph p_Graph, int m_ID)throws Exception{
		AdjacencyListRepresentation v_ALR = new AdjacencyListRepresentation(p_Graph);
		return v_ALR.neighbours(m_ID);
	}
	
	public Edge getAdjacentEdge(Vertice p_Source,Vertice p_Target,Graph p_Graph) {
		AdjacencyListRepresentation v_ALR = new AdjacencyListRepresentation(p_Graph);
		return v_ALR.getEdge(p_Source, p_Target);
	}
	
	public boolean isEulerian(Graph p_Graph) throws Exception {
		
		boolean v_ToReturn=false;
		if(p_Graph.get_IsDirect()==true) {
			ArrayList<Vertice> v_SourceArray = new ArrayList<Vertice>();
			ArrayList<Vertice> v_TargetArray = new ArrayList<Vertice>();
			int v_ContSource=0;
			int v_ContTarget=0;
			
			ArrayList<Edge> v_AuxArrayEdge = new ArrayList<Edge>();
			v_AuxArrayEdge.addAll(m_GroupOfEdge);
			Collections.sort(v_AuxArrayEdge);
			
			
			ArrayList<Vertice> m_Group = new ArrayList<Vertice>();
			m_Group.addAll(m_GroupOfVertice);
			Collections.sort(m_Group);
			
			
			for(int i=0;i<v_AuxArrayEdge.size();i++) {
				v_SourceArray.add(v_AuxArrayEdge.get(i).getSource());
				v_TargetArray.add(v_AuxArrayEdge.get(i).getTarget());	
			}
			
			for(int j=0;j<m_Group.size();j++) {
				for(int i=0;i<v_SourceArray.size();i++) {
					if(m_Group.get(j).getID()==v_SourceArray.get(i).getID()) {
						v_ContSource++;
					}
				}
				for(int i=0;i<v_TargetArray.size();i++) {
					if(m_Group.get(j).getID()==v_TargetArray.get(i).getID()) {
						v_ContTarget++;
					}
				}
				
				if(v_ContSource == v_ContTarget) {
					v_ToReturn = true;
					v_ContSource=0;
					v_ContTarget=0;
				}
				else {
					v_ToReturn = false;
					return v_ToReturn;
				}
			}
		}
		
		else {
			LinkedHashSet<Vertice> v_AuxSet = new LinkedHashSet<Vertice>();
			v_AuxSet = p_Graph.getGroupOfVertice();
			LinkedList<Vertice> v_VerifyArray = new LinkedList<Vertice>();
			ArrayList<Boolean> v_Verify = new ArrayList<Boolean>();
			for(Vertice v_AuxVertice : v_AuxSet) {
				v_VerifyArray.addAll(p_Graph.getNeighbours(p_Graph,v_AuxVertice.getID()));
				if(v_VerifyArray.size()%2==0) {
					v_Verify.add(true);
				}
				else {
					return false;
				}
			}
			int v_Length = 0;
			
			for(int i=0;i<v_Verify.size();i++) {
				if(v_Verify.get(i)==true) {
					v_Length++;
				}
			}
			
			if(v_Length==v_Verify.size()) {
				v_ToReturn = true;
			}
		}		
		return v_ToReturn;
	}
	
	public void removeEdge(Vertice p_Source, Vertice p_Target) {
		ArrayList<Edge> v_AuxArray = new ArrayList<Edge>();
		v_AuxArray.addAll(m_GroupOfEdge);
		
		for(int i=0;i<v_AuxArray.size();i++) {			
			if(p_Source.getID() == v_AuxArray.get(i).getSource().getID() && p_Target.getID() == v_AuxArray.get(i).getTarget().getID()) {
				m_GroupOfEdge.remove(v_AuxArray.get(i));
			}
		}
	}
}