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

    /**
     * Retorna uma representação do grafo, podendo ser em forma de matriz de adjacências ou lista de adjacências
     * @param graph grafo a ser representado
     * @param type tipo da representação
     * @return Uma representação do grafo em string
     */
    public String graphRepresentation (Grafo graph, Representacao type) {
        return graph.graphRepresentation(type);
    }

    /**
     * Realiza a busca em largura do {@link Grafo} e de um {@link Vertice} raiz passados como parâmetro
     * @param graph
     * @param v
     * @return tabela com vertices do Grafo visitados, com seu respectivo nível e pai
     */
    public String BFS (Grafo graph, Vertice v) {
        return graph.BFS(v);
    }

    public String DFS (Grafo graph, Vertice v) {
        return graph.BFS(v);
    }

    public boolean connected (Grafo graph) {
        return graph.connected();
    }

    public String shortestPath(Grafo graph, int v1, int v2) {
        return graph.shortestPath(v1, v2);
    }

    public String mst (Grafo graph) {
        return graph.mst();
    }
}
