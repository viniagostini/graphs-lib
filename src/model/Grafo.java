package model;

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
        return null;
    }

    private String graphAdjacencyList () {
        return null;
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
