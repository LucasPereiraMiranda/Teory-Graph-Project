package br.graph.search_algorithms;

import java.awt.Color;
import java.util.LinkedHashSet;
import java.util.LinkedList;


import br.graph.base_of_graph.Graph;
import br.graph.base_of_graph.Vertice;

public class DeepFirstSearch implements SearchAlgorithmsRequiriments{

	private Graph m_Graph;
	private LinkedList<Vertice> m_Group;
	private LinkedHashSet<Vertice> m_Visited;
	private int m_NumberComponentsConnecteds;
	private boolean m_IsConnected;
	
	public DeepFirstSearch(Graph p_Graph) {
		m_Graph = p_Graph;
		m_Group = new LinkedList<Vertice>();
		m_Group.addAll(m_Graph.getGroupOfVertice());
		m_Visited = new LinkedHashSet<Vertice>();
		m_NumberComponentsConnecteds = 1;
		m_IsConnected = true;
	}
	
	@Override
	public void search(Vertice p_InitialVertice) throws Exception {		
		
		proof(p_InitialVertice);
		
		for(int i=0;i<m_Group.size();i++) {
			if(m_Group.get(i).getColor() == Color.red) {
				proof(m_Group.get(i));
			}
		}
		
		if(!m_Visited.containsAll(m_Group)) {
			for(int i=0;i<m_Group.size();i++) {
				if(m_Visited.contains(m_Group.get(i))) {
					continue;
				}
				else {
					search(m_Group.get(i));
					m_IsConnected = false;
					m_NumberComponentsConnecteds++;
				}
			}
		}
	}
	
	private void proof(Vertice p_Vertice) throws Exception {
		p_Vertice.setColor(Color.blue);
		m_Visited.add(p_Vertice);
		
		for(Vertice v_Aux : m_Graph.getNeighbours(m_Graph, p_Vertice.getID())) { 
			if(m_Visited.contains(v_Aux)) {
				continue;
			}
			
			if(v_Aux.getColor()==Color.red) {
				proof(v_Aux);
			}
			v_Aux.setColor(Color.red);
		}
	}
	
	@Override
	public void print() {
		String v_ToPrint="";
		Vertice [] v_VerticeArray = new Vertice[m_Visited.size()];
		m_Visited.toArray(v_VerticeArray);
		
		for(int i=0;i<v_VerticeArray.length;i++) {
			v_ToPrint +="-> "+ v_VerticeArray[i];
		}
		
		System.out.print("\nDeep Fist Search(DFS) Recursive: \n");
		System.out.println(v_ToPrint);
	}
	
	public int getNumberComponentsConnecteds() {
		return m_NumberComponentsConnecteds;
	}
	
	public boolean getIsConnected() {
		return m_IsConnected;
	}
}