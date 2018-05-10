package main.controller;

import main.model.Grafo;
import main.model.Representacao;
import main.model.Vertice;

/**
 * Controller responsável por gerenciar as operações de Grafos.
 */
public class Controller {

    /**
     * Retorna o número de vértices do {@link Grafo} passado como parâmetro.
     *
     * @param graph
     *
     * @return número de vértices do Grafo.
     */
    public int getVertexNumber (Grafo graph) {
        return graph.getVertexNumber();
    }


    /**
     * Retorna o número de arestas do {@link Grafo} passado como parâmetro
     *
     * @param graph
     *
     * @return número de arestas do Grafo.
     */
    public int getEdgeNumber (Grafo graph) {
        return graph.getEdgeNumber();
    }

    /**
     * Retorna o resultado do cálculo do grau médio do {@link Grafo} passado como parâmetro
     *
     * @param graph
     *
     * @return número do grau médio do Grafo
     */
    public float getMeanEdge (Grafo graph) {
        return graph.getMeanEdge();
    }

    public String graphRepresentation (Grafo graph, Representacao type) {
        return graph.graphRepresentation(type);
    }

    public String BFS (Grafo graph, Vertice v) {
        return graph.BFS(v);
    }

    public String DFS (Grafo graph, Vertice v) {
        return graph.BFS(v);
    }

    public String SCC (Grafo graph) {
        return graph.SCC();
    }

    public String shortestPath(Grafo graph, Vertice v1, Vertice v2) {
        return graph.shortestPath(v1, v2);
    }

    public String mst (Grafo graph) {
        return graph.mst();
    }
}
