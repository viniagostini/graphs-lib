package controller;

import model.Grafo;
import model.Representacao;
import model.Vertice;

/**
 * controller.Controller responsável por gerenciar as operações de Grafos.
 */
public class Controller {


    public int getVertexNumber (Grafo graph) {
        return graph.getVertexNumber();
    }

    public int getEdgeNumber (Grafo graph) {
        return graph.getEdgeNumber();
    }

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
