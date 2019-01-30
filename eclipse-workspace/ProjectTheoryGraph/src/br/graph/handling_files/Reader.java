package br.graph.handling_files;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedHashSet;
import br.graph.base_of_graph.Edge;
import br.graph.base_of_graph.Graph;
import br.graph.base_of_graph.Vertice;

public class Reader{
	
	private String m_FileName;
	private boolean m_IsDirect;
	private LinkedHashSet<Vertice> m_GroupVertice;
	private LinkedHashSet<Edge> m_GroupEdge;
	private Graph m_AuxGraph;
	private String [] m_DataInLine;
	private String m_Row;
	
	public Reader(String p_FileName,boolean p_IsDirect) {
		m_FileName = p_FileName;
		m_IsDirect = p_IsDirect;
		m_GroupVertice = new LinkedHashSet<Vertice>();
		m_GroupEdge = new LinkedHashSet<Edge>();
	}
	
	
	
	@SuppressWarnings("resource")
	public Graph readerDimacs() throws Exception {
		FileReader v_FileReader = new FileReader(m_FileName);
		BufferedReader v_Reader = new BufferedReader(v_FileReader);
	    int i=0;
	    for(;(m_Row = v_Reader.readLine())!=null;) {
	    	if(i==0) {
	    		i++;
	    		continue;
	    	}	
	    	else {
	    		m_DataInLine = m_Row.split(" ");
	    		
	    		this.m_GroupVertice.add(new Vertice(Integer.parseInt(m_DataInLine[0])));
	    		this.m_GroupVertice.add(new Vertice(Integer.parseInt(m_DataInLine[1])));
	  
	    		if(m_IsDirect==false) {
	    			this.m_GroupEdge.add(new Edge(new Vertice(Integer.parseInt(m_DataInLine[0])),new Vertice(Integer.parseInt(m_DataInLine[1])),Integer.parseInt(m_DataInLine[2])));
	    			this.m_GroupEdge.add(new Edge(new Vertice(Integer.parseInt(m_DataInLine[1])),new Vertice(Integer.parseInt(m_DataInLine[0])),Integer.parseInt(m_DataInLine[2])));
	    		}
	    		else {
	    			this.m_GroupEdge.add(new Edge(new Vertice(Integer.parseInt(m_DataInLine[0])),new Vertice(Integer.parseInt(m_DataInLine[1])),Integer.parseInt(m_DataInLine[2])));
	    		}
	    	}
	    	i++;
	    }
	    m_AuxGraph = new Graph(m_GroupVertice,m_GroupEdge,m_IsDirect);
		return m_AuxGraph;
	}
	
}
