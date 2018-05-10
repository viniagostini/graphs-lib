package test.model;

import main.model.Aresta;
import main.model.Grafo;
import main.model.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class GrafoTest {

    Grafo grafo;

    @BeforeEach
    void setUp() {
        grafo = new Grafo();
    }

    @Test
    void constructorTest() {
        assertNotNull(grafo, "O Grafo não deve ser null");
        assertNotNull(grafo.getArestas(), "As arestas do Grafo devem ter sido inicialiadas no construtor");
        assertNotNull(grafo.getVertices(), "Os vértices do Grafo devem ter sido inicialiados no construtor");
    }

    @Test
    void getVertexNumber() {
        assertEquals(0, grafo.getVertexNumber(), "O Grafo deveria começar sem vertices.");

        Set<Vertice> vertices = new HashSet<>();

        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);

        vertices.add(v1);
        vertices.add(v2);

        grafo.setVertices(vertices);

        assertEquals(2, grafo.getVertexNumber(), "O Grafo deve possuir dois vértices");
    }

    @Test
    void getEdgeNumber() {
        assertEquals(0, grafo.getEdgeNumber(), "O Grafo deveria começar sem arestas.");

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

        assertEquals(2, grafo.getEdgeNumber(), "O Grafo deve possuir duas arestas");
    }

    @Test
    void getMeanEdge() {

        Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();

        Vertice v1 = new Vertice(1);

        vertices.add(v1);

        grafo.setVertices(vertices);

        assertEquals(0, grafo.getMeanEdge(),"O grau médio do grafo deve ser 0, pois só há um vértice isolado");

        Vertice v2 = new Vertice(2);

        Aresta a1 = new Aresta(v1,v2,1);

        vertices.add(v2);
        arestas.add(a1);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals(1, grafo.getMeanEdge(), "Pela definição de grau médio, o valor para 1 aresta e 2 vértices deve ser 1");

        Aresta a2 = new Aresta(v2,v1,1);
        arestas.add(a2);

        grafo.setArestas(arestas);

        assertEquals(2, grafo.getMeanEdge(), "Pela definição de grau médio, o valor para 2 arestas e 2 vértices deve ser 2");
    }

    @Test
    void graphRepresentation() {
    }

    @Test
    void BFS() {
    }

    @Test
    void DFS() {
    }

    @Test
    void SCC() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void mst() {
    }

    @Test
    void getArestas() {
    }

    @Test
    void setArestas() {
    }

    @Test
    void getVertices() {
    }

    @Test
    void setVertices() {
    }
}
