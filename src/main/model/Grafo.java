package main.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Classe que representa a entridade Grafo.
 */
public class Grafo {

    private Set<Aresta> arestas;
    private Set<Vertice> vertices;

    public Grafo () {
        arestas = new HashSet<>();
        vertices = new HashSet<>();
    }

    /**
     * Retorna o número de vértices do Grafo.
     */
    public int getVertexNumber () {
        return vertices.size();
    }

    /**
     * Retorna o número de arestas do Grafo.
     */
    public int getEdgeNumber () {
        return arestas.size();
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
