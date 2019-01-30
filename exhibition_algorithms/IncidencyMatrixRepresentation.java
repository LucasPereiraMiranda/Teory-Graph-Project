package br.graph.exhibition_algorithms;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import br.graph.base_of_graph.Edge;
import br.graph.base_of_graph.Graph;
import br.graph.base_of_graph.Vertice;

public class IncidencyMatrixRepresentation implements ExhibitionAlgorithmsRequirements{
	
    private Graph m_AuxGraph;
    private LinkedHashMap <Vertice,Integer> m_RepresentationLines;
    private LinkedHashMap <Edge,Integer> m_RepresentationColums;
    private int [][] m_IncidencyMatrix;
    private int m_AuxToMapLinesIndex;
    private int m_AuxToMapColumsIndex;
    private boolean m_IsDirect;
    
    private ArrayList<Vertice> m_ArrayToOrderVertices = new ArrayList<Vertice>();
    private ArrayList<Edge> m_ArrayToOrderEdges = new ArrayList<Edge>();
	
    public IncidencyMatrixRepresentation(Graph p_Graph) {
    	m_IsDirect = p_Graph.get_IsDirect();
	m_AuxGraph = p_Graph;
	m_RepresentationLines = new LinkedHashMap<Vertice,Integer>();
	m_RepresentationColums = new LinkedHashMap<Edge,Integer>();
    	m_IncidencyMatrix = new int[m_AuxGraph.getNumberOfVertices()][m_AuxGraph.getNumberOfEdges()];
    	m_AuxToMapLinesIndex=0;
    	m_AuxToMapColumsIndex=0;
    	
    	for(int i=0;i<m_AuxGraph.getNumberOfVertices();i++) {
    		for(int j=0;j<m_AuxGraph.getNumberOfEdges();j++) {
    			m_IncidencyMatrix[i][j]=0;
   		}
   	}
    
	Vertice[] v_AuxArrayVertice = new Vertice [p_Graph.getGroupOfVertice().size()];
	m_ArrayToOrderVertices.addAll(p_Graph.getGroupOfVertice());
	Collections.sort(m_ArrayToOrderVertices);
	m_ArrayToOrderVertices.toArray(v_AuxArrayVertice);
	
	for(int i=0;i<v_AuxArrayVertice.length;i++) {
		this.addVertice(v_AuxArrayVertice[i]);	
	}
		
	Edge[] m_AuxArrayEdge;
    	m_AuxArrayEdge = new Edge [p_Graph.getGroupOfEdge().size()];
    	m_ArrayToOrderEdges.addAll(p_Graph.getGroupOfEdge());
    	Collections.sort(m_ArrayToOrderEdges);
    	m_ArrayToOrderEdges.toArray(m_AuxArrayEdge);
    	for(int i=0;i<m_AuxArrayEdge.length;i++) {
			this.addEdge(m_AuxArrayEdge[i]);
	}
    }
	
	
    @Override
    public void addEdge(Edge p_Edge) {
   	addVertice(p_Edge.getSource());
   	addVertice(p_Edge.getTarget());
   		
   	int v_PositionSource;
   	int v_PositionTarget;
   		
   	m_RepresentationColums.put(p_Edge,m_AuxToMapColumsIndex);
   	m_AuxToMapColumsIndex++;
   	int v_ColumPosition = m_RepresentationColums.get(p_Edge);
   		
   	if(!m_IsDirect) {
   		v_PositionSource = m_RepresentationLines.get(p_Edge.getSource());
   		v_PositionTarget =  m_RepresentationLines.get(p_Edge.getTarget());
   		m_IncidencyMatrix[v_PositionSource][v_ColumPosition]=1;
   		m_IncidencyMatrix[v_PositionTarget][v_ColumPosition]=1;	
   	}
	else{
   		v_PositionSource = m_RepresentationLines.get(p_Edge.getSource());
   	 	v_PositionTarget =  m_RepresentationLines.get(p_Edge.getTarget());
   	   	m_IncidencyMatrix[v_PositionSource][v_ColumPosition]=+1;
   	   	m_IncidencyMatrix[v_PositionTarget][v_ColumPosition]=-1;	
   	}
    }
    
    @Override
    public void addVertice(Vertice p_Vertice) {
        	if(m_RepresentationLines.containsKey(p_Vertice)) 
        		return;
        	m_RepresentationLines.put(p_Vertice, m_AuxToMapLinesIndex);
       		m_AuxToMapLinesIndex++;
    }
	
	
    @Override
    public void printRepresentation() {
    	System.out.println("\nIncidency Matrix representation: ");
    	for(int i=0;i<m_AuxGraph.getNumberOfVertices();i++) {
    		for(int j=0;j<m_AuxGraph.getNumberOfEdges();j++) {
			System.out.print(m_IncidencyMatrix[i][j]+"      ");
    		}
    		System.out.println();
    	}
    }

}
