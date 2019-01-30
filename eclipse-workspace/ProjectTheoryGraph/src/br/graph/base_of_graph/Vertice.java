package br.graph.base_of_graph;

import java.awt.Color;


public class Vertice implements Comparable<Vertice>{
	
	private int m_ID;
	private Color m_Color;
	private int m_Index;

	public Vertice(int p_ID) {
		m_ID = p_ID;
		this.m_Color=Color.red;
	}
	
	public Vertice(int p_ID,int p_Index,Color p_Color) {
		m_ID = p_ID;
		m_Color = p_Color;
		m_Index = p_Index;
	}
	
	
	public Color getColor() {
		return m_Color;
	}
	
	public void setColor(Color p_Color) {
		m_Color = p_Color;
	}
	
	public int getID() {
		return m_ID;
	}
	
	public void setID(int p_ID) {
		this.m_ID=p_ID;
	}
	
	public int getIndex() {
		return m_Index;
	}

	public void setIndex(int p_Index) {
		this.m_Index = p_Index;
	}
	
	@Override
	public String toString() {
		return "ID: " + m_ID;
	}
	
	/*Foi necessário reescrever os métodos abaixo para que o HashSet diferenciasse o pelo campo, e não pelo endereço do objeto*/
	@Override
	public boolean equals(Object p_Obj){
        if (p_Obj instanceof Vertice) {
            Vertice v_Aux = (Vertice) p_Obj;
            return (v_Aux.m_ID == this.m_ID);
        } else {
            return false;
        }
    }
	
	@Override
	public int hashCode(){
        return this.getID();
	}
	
	@Override
	public int compareTo(Vertice p_Vertice) {
		if(this.m_ID < p_Vertice.m_ID) {
			return -1;
		}
		else if(this.m_ID > p_Vertice.m_ID) {
			return 1;
		}
		return 0;
	}
}