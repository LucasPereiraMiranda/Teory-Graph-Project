package br.graph.base_of_graph;

import java.awt.Color;


public class Edge implements Comparable<Edge>{
	
	private Color m_Color;
	private int m_Weight;
	private Vertice m_VerticeSource;
	private Vertice m_VerticeTarget;
	
	public Edge(Vertice p_VerticeSource, Vertice p_VerticeTarget) {
		m_VerticeSource = p_VerticeSource;
		m_VerticeTarget = p_VerticeTarget;
		m_Weight = 0;
	}

	public Edge(Vertice p_VerticeSource, Vertice p_VerticeTarget, int p_Weight) {
		m_VerticeSource = p_VerticeSource;
		m_VerticeTarget = p_VerticeTarget;
		m_Weight = p_Weight;
	}

	public Color getColor() {
		return m_Color;
	}
	
	public int getWeight() {
		return m_Weight;
	}
	
	public Vertice getSource() {
		return m_VerticeSource;
	}
	
	public Vertice getTarget() {
		return m_VerticeTarget;
	}

	public void setWeight(int m_Weight) {
		this.m_Weight = m_Weight;
	}

	@Override
	public String toString() {
		return "("+m_VerticeSource.getID()+","+m_VerticeTarget.getID()+")";
	}
	
	/*It was necessary to rewrite the methods below
	so that HashSet would differ by the field, not by the address of the object*/
	@Override
	public boolean equals(Object p_Obj){
        if (p_Obj instanceof Edge) {
            Edge v_Aux = (Edge) p_Obj;
            return (v_Aux.m_VerticeSource.equals(this.m_VerticeSource) && v_Aux.m_VerticeTarget.equals(this.m_VerticeTarget));
        } else {
            return false;
        }
    }
	
	
	
	@Override
	public int compareTo(Edge p_Edge) {
		if(this.m_VerticeSource.getID() < p_Edge.getSource().getID()) {
			return -1;
		}
		else if(this.m_VerticeSource.getID() < p_Edge.getSource().getID()) {
			return 1;
		}
		return 0;
	}
}
