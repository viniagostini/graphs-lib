package model;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Classe que representa a entridade Grafo.
 */
public class Grafo {

    private Set<Aresta> arestas;
    private Set<Vertice> vertices;

    //contrutores

    public int getVertexNumber () {
        return 0;
    }

    public int getEdgeNumber () {
        return 0;
    }

    public float getMeanEdge () {
        return (2 * arestas.size()) / vertices.size();
    }

    public String graphRepresentation (Representacao type) {
        if(type == Representacao.AL) {
            return graphAdjacencyList();
        } else {
            return graphAdjacencyMatrix();
        }
    }

    public String BFS (Vertice v) {
        return null;
    }

    public String DFS (Vertice v) {
        return null;
    }

    public String SCC () {
        return null;
    }

    public String shortestPath(Vertice v1, Vertice v2) {
        return null;
    }

    public String mst () {
        return null;
    }

    private String graphAdjacencyMatrix () {
    	StringBuilder matrixAdjacencias = new StringBuilder();
    	matrixAdjacencias.append("Matriz de adjacência" + System.getProperty("line.separator"));
    	matrixAdjacencias.append("     "+Arrays.toString(this.vertices.toArray()) + System.getProperty("line.separator"));
        
    	for(Vertice vLinha : vertices){
        	matrixAdjacencias.append("[" + vLinha.getId() + "]");
        	for(Vertice vColuna : vertices){
        		boolean adjacente = checkAdjacent(vLinha, vColuna);
        		if(adjacente){
        			matrixAdjacencias.append("    1");
        		}else {
        			matrixAdjacencias.append("    0");
        		}
        		
        	}
        	matrixAdjacencias.append(System.getProperty("line.separator"));
        }
        return matrixAdjacencias.toString();
    }

	private boolean checkAdjacent(Vertice verticeA, Vertice verticeB) {
		boolean adjacente = false;
		for(Aresta aresta: arestas){
		    if(aresta.getVerticeInicial().equals(verticeA) && aresta.getVerticeFinal().equals(verticeB)) {
		    	adjacente = true;
		    }
		}
		return adjacente;
	}

    private String graphAdjacencyList () {
    	StringBuilder listaAdjacencias = new StringBuilder();
        for(Vertice vAtual : vertices){
            listaAdjacencias.append("Lista de adjacências do vértice "+ vAtual.getId() + ": ");
            for(Aresta aresta: arestas){
            	if(aresta.getVerticeInicial().equals(vAtual)) {
            		listaAdjacencias.append("-> " + aresta.getVerticeFinal().getId() + " ");
            	}
            }
            listaAdjacencias.append(System.getProperty("line.separator"));
        }
        return listaAdjacencias.toString();
    }

    public Set<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(Set<Aresta> arestas) {
        this.arestas = arestas;
    }

    public Set<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(Set<Vertice> vertices) {
        this.vertices = vertices;
    }
}
