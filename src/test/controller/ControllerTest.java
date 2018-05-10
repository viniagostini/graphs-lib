package test.controller;


import main.controller.Controller;
import main.model.Aresta;
import main.model.Grafo;
import main.model.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ControllerTest {

    Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @Test
    void getVertexNumberTest() {
        Grafo grafo = new Grafo();

        assertEquals(0, controller.getVertexNumber(grafo), "O Grafo deveria começar sem vertices.");

        Set<Vertice> vertices = new HashSet<>();

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        vertices.add(v1);
        vertices.add(v2);

        grafo.setVertices(vertices);

        assertEquals(2, controller.getVertexNumber(grafo), "O Grafo deve possuir dois vértices");
    }

    @Test
    void getEdgeNumberTest() {
        Grafo grafo = new Grafo();

        assertEquals(0, controller.getEdgeNumber(grafo), "O Grafo deveria começar sem arestas.");

        Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        vertices.add(v1);
        vertices.add(v2);

        Aresta aresta1 = new Aresta(v1, v2, 1);
        Aresta aresta2 = new Aresta(v2, v1, 1);

        arestas.add(aresta1);
        arestas.add(aresta2);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals(2, controller.getEdgeNumber(grafo), "O Grafo deve possuir duas arestas");
    }

    @Test
    void getMeanEdgeTest() {

        Grafo grafo = new Grafo();

        Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();

        Vertice v1 = new Vertice(1);

        vertices.add(v1);

        grafo.setVertices(vertices);

        assertEquals(0, controller.getMeanEdge(grafo),"O grau médio do grafo deve ser 0, pois só há um vértice isolado");

        Vertice v2 = new Vertice(2);

        Aresta a1 = new Aresta(v1,v2,1);

        vertices.add(v2);
        arestas.add(a1);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals(1, controller.getMeanEdge(grafo), "Pela definição de grau médio, o valor para 1 aresta e 2 vértices deve ser 1");

        Aresta a2 = new Aresta(v2,v1,1);
        arestas.add(a2);

        grafo.setArestas(arestas);

        assertEquals(2, controller.getMeanEdge(grafo), "Pela definição de grau médio, o valor para 2 arestas e 2 vértices deve ser 2");
    }

    @Test
    void graphRepresentationTest() {
    }

    @Test
    void BFSTest() {
    }

    @Test
    void DFSTest() {
    }

    @Test
    void SCCTest() {
    }

    @Test
    void shortestPathTest() {
    }

    @Test
    void mstTest() {
    }
}
