package br.graph.search_algorithms;

import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;

import br.graph.base_of_graph.Graph;
import br.graph.base_of_graph.Vertice;

public class BreadthFirstSearch implements SearchAlgorithmsRequiriments{	
	
	private Graph m_Graph;
	private LinkedList<Vertice> m_Group;
	private Queue<Vertice> m_Queue;
	private ArrayList<Vertice> m_Visited;
	private LinkedHashSet<Vertice> m_ToMessage;
	
	public BreadthFirstSearch(Graph p_Graph) {
		m_Queue = new LinkedList<>();
		m_Graph = p_Graph;
		m_Group = new LinkedList<Vertice>();
		m_Group.addAll(m_Graph.getGroupOfVertice());
		m_Visited = new ArrayList<Vertice>(); 
		m_ToMessage = new LinkedHashSet<Vertice>();
	}
	
	//	blue visited
	//	red not visited
	@Override
	public void search(Vertice p_InitialVertice) throws Exception {
		
		for(int i=0;i<m_Group.size();i++) {
			m_Group.get(i).setColor(Color.red);
		}
		
		p_InitialVertice.setColor(Color.blue);
		m_Queue.add(p_InitialVertice);
		
		while(!m_Queue.isEmpty()) {
			Vertice v_U = m_Queue.poll();
	
			m_Visited.add(v_U);
			m_ToMessage.add(v_U);

			for(Vertice v_aux : m_Graph.getNeighbours(m_Graph, v_U.getID())){
				if(m_Visited.contains(v_aux)) {
					continue;
				}
				
				if(v_aux.getColor()==Color.red) {
					v_aux.setColor(Color.blue);
					m_Queue.add(v_aux);
				}
				v_aux.setColor(Color.red);
			}	
		}
		if(!m_Visited.containsAll(m_Group)) {
			for(int i=0;i<m_Group.size();i++) {
				if(m_Visited.contains(m_Group.get(i))) {
					continue;
				}
				else {
					search(m_Group.get(i));
				}
			}
		}
	}
	
	@Override
	public void print() {
		String v_ToPrint="";
		Vertice [] v_VerticeArray = new Vertice[m_ToMessage.size()];
		m_ToMessage.toArray(v_VerticeArray);
		
		for(int i=0;i<v_VerticeArray.length;i++) {
			v_ToPrint +="-> "+ v_VerticeArray[i];
		}
		
		System.out.println("\nBreadth First Search(BFS): ");
		System.out.println(v_ToPrint);
	}
}
