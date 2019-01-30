package br.graph.exhibition_algorithms;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import br.graph.base_of_graph.*;

public class AdjacencyMatrixRepresentation implements ExhibitionAlgorithmsRequirements{
	
    private Graph m_AuxGraph;
    private LinkedHashMap <Vertice,Integer> m_Representation;
    private int [][] m_AdjacencyMatrix;
	private int m_AuxToMapLinesIndex;
    private boolean m_IsDirect;
    
    private ArrayList<Vertice> m_ArrayToOrder = new ArrayList<Vertice>();
    
    public AdjacencyMatrixRepresentation(Graph p_Graph) {
    	
    	m_IsDirect=p_Graph.get_IsDirect();
    	m_Representation = new LinkedHashMap<Vertice,Integer>();
    	m_AuxGraph = p_Graph;
    	m_AdjacencyMatrix = new int[m_AuxGraph.getNumberOfVertices()][m_AuxGraph.getNumberOfVertices()];
    	
    	Vertice[] v_AuxArrayVertice = new Vertice [p_Graph.getGroupOfVertice().size()];
    	m_ArrayToOrder.addAll(p_Graph.getGroupOfVertice());
		Collections.sort(m_ArrayToOrder);
		m_ArrayToOrder.toArray(v_AuxArrayVertice);
		for(int i=0;i<v_AuxArrayVertice.length;i++) {
			this.addVertice(v_AuxArrayVertice[i]);	
		}
		
		Edge[] m_AuxArrayEdge;
        m_AuxArrayEdge = new Edge [p_Graph.getGroupOfEdge().size()];
		p_Graph.getGroupOfEdge().toArray(m_AuxArrayEdge);
		
    	for(int i=0;i<m_AuxGraph.getNumberOfVertices();i++) {
    		for(int j=0;j<m_AuxGraph.getNumberOfVertices();j++) {
    			m_AdjacencyMatrix[i][j]=0;
   			}
   		}
    	
    	for(int i=0;i<m_AuxArrayEdge.length;i++) {
			this.addEdge(m_AuxArrayEdge[i]);
		}
    }
    
    @Override
    public void addEdge(Edge p_Edge) {
    	
   		addVertice(p_Edge.getSource());
   		addVertice(p_Edge.getTarget());

        int v_PositionSource = m_Representation.get(p_Edge.getSource());
        int v_PositionTarget =  m_Representation.get(p_Edge.getTarget());
        if(!m_IsDirect) {
        	m_AdjacencyMatrix[v_PositionSource][v_PositionTarget] = 1; 
        	m_AdjacencyMatrix[v_PositionTarget][v_PositionSource] = 1; 
        }
        else{
        	m_AdjacencyMatrix[v_PositionSource][v_PositionTarget] = 1; 
        }
    }
    
    @Override
    public void addVertice(Vertice p_Vertice) {
        if(m_Representation.containsKey(p_Vertice)) {
        	return;
        }
        m_Representation.put(p_Vertice, m_AuxToMapLinesIndex);
        m_AuxToMapLinesIndex++;
    }
    
	public int[][] getAdjacencyMatrix(){
		return m_AdjacencyMatrix;
	}
	
	@Override
	public void printRepresentation() {
    	System.out.println("\nAdjacency Matrix representation: ");
    	for(int i=0;i<m_AuxGraph.getNumberOfVertices();i++) {
    		for(int j=0;j<m_AuxGraph.getNumberOfVertices();j++) {
    			System.out.print(m_AdjacencyMatrix[i][j]+"  ");
    		}
    		System.out.println();
    	}
    }
}


