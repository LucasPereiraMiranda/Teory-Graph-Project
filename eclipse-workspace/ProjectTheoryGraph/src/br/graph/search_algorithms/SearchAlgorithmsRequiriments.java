package br.graph.search_algorithms;
import br.graph.base_of_graph.Vertice;

public interface SearchAlgorithmsRequiriments {

	public abstract void search(Vertice p_InitialVertice) throws Exception;
	public abstract void print();
}
