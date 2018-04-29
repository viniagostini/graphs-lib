package model;

import java.util.List;

/**
 * Classe que representa a entridade Grafo.
 */
public class Grafo {

    private List<Aresta> arestas;
    private List<Vertice> vertices;

    //contrutores

    public int getVertexNumber () {
        return 0;
    }

    public int getEdgeNumber () {
        return 0;
    }

    public float getMeanEdge () {
        return 0;
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

    public List<Aresta> getArestas() {
        return arestas;
    }

    public void setArestas(List<Aresta> arestas) {
        this.arestas = arestas;
    }

    public List<Vertice> getVertices() {
        return vertices;
    }

    public void setVertices(List<Vertice> vertices) {
        this.vertices = vertices;
    }
}
