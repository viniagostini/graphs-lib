package test.model;

import main.model.Aresta;
import main.model.Grafo;
import main.model.Vertice;
import main.view.IO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.LinkedList;
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

        Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();

        Vertice v1 = new Vertice(1);

        vertices.add(v1);

        grafo.setVertices(vertices);

        assertEquals("1 - 0 -\n", grafo.BFS(v1), "A busca deve retornar apenas o vertice raiz");

        Vertice v2 = new Vertice(2);
        vertices.add(v2);

        Aresta a1 = new Aresta(v1,v2,1);
        arestas.add(a1);
        Aresta a2 = new Aresta(v2,v1, 1);
        arestas.add(a2);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals("1 - 0 -\n2 - 1 1\n", grafo.BFS(v1), "A busca deve retornar o vertice raiz e seu filho");

        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);

        Aresta a3 = new Aresta(v1,v5,1);
        Aresta a4 = new Aresta(v2,v5,1);
        Aresta a5 = new Aresta(v3,v5,1);
        Aresta a6 = new Aresta(v4,v5,1);

        vertices.add(v3); vertices.add(v4); vertices.add(v5);
        arestas.add(a3); arestas.add(a4); arestas.add(a5); arestas.add(a6);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals("1 - 0 -\n2 - 1 1\n5 - 1 1\n3 - 2 5\n4 - 2 5\n" , grafo.BFS(v1),
                "A busca retorna o vertice raiz e todos os vértices do grafo em largura");

    }

    @Test
    void DFS() {
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);
        Vertice v6 = new Vertice(6);
        Vertice v7 = new Vertice(7);

        Aresta a1 = new Aresta(v4, v2, 1);
        Aresta a2 = new Aresta(v2, v1, 1);
        Aresta a3 = new Aresta(v2, v3, 1);
        Aresta a4 = new Aresta(v4, v6, 1);
        Aresta a5 = new Aresta(v6, v5, 1);
        Aresta a6 = new Aresta(v6, v7, 1);
        Aresta a7 = new Aresta(v2, v6, 1);

        HashSet<Aresta> arestas = new HashSet<>();
        arestas.add(a1); arestas.add(a2); arestas.add(a3);
        arestas.add(a4); arestas.add(a5); arestas.add(a6);
        arestas.add(a7);

        HashSet<Vertice> vertices = new HashSet<>();
        vertices.add(v1); vertices.add(v2); vertices.add(v3);
        vertices.add(v4); vertices.add(v5); vertices.add(v6);
        vertices.add(v7);

        Grafo grafo = new Grafo();
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);

        String saida = grafo.DFS(v4);

        String esperado = "4 - 0 -" + System.lineSeparator()
                        + "2 - 4 1" + System.lineSeparator()
                        + "1 - 2 2" + System.lineSeparator()
                        + "3 - 2 2" + System.lineSeparator()
                        + "6 - 4 1" + System.lineSeparator()
                        + "5 - 6 2" + System.lineSeparator()
                        + "7 - 6 2" + System.lineSeparator();

        assertEquals(esperado, saida);
    }

    @Test
    void connected() {
    }

    @Test
    void shortestPath() {
    }

    @Test
    void mst() {
    	Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Vertice v3 = new Vertice(3);
        Vertice v4 = new Vertice(4);
        Vertice v5 = new Vertice(5);
        Vertice v6 = new Vertice(6);
        Vertice v7 = new Vertice(7);

        Aresta a1 = new Aresta(v4, v2, 5.0);
        Aresta a2 = new Aresta(v2, v1, 6.0);
        Aresta a3 = new Aresta(v2, v3, 2.0);
        Aresta a4 = new Aresta(v4, v6, -2.0);
        Aresta a5 = new Aresta(v6, v5, 0.5);
        Aresta a6 = new Aresta(v6, v7, 0.5);
        Aresta a7 = new Aresta(v2, v6, 1.0);
        Aresta a8 = new Aresta(v5, v7, 0.1);

        HashSet<Aresta> arestas = new HashSet<>();
        arestas.add(a1); arestas.add(a2); arestas.add(a3);
        arestas.add(a4); arestas.add(a5); arestas.add(a6);
        arestas.add(a7);arestas.add(a8);

        HashSet<Vertice> vertices = new HashSet<>();
        vertices.add(v1); vertices.add(v2); vertices.add(v3);
        vertices.add(v4); vertices.add(v5); vertices.add(v6);
        vertices.add(v7);

        Grafo grafo = new Grafo();
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);

        String saida = grafo.mst();
        
        String esperado = 	"Vertice Pai: 4 Vertice Filho: 6 Peso Aresta: -2.0\n" +
        					"Vertice Pai: 5 Vertice Filho: 7 Peso Aresta: 0.1\n" +
        					"Vertice Pai: 6 Vertice Filho: 5 Peso Aresta: 0.5\n" +
        					"Vertice Pai: 2 Vertice Filho: 6 Peso Aresta: 1.0\n" +
        					"Vertice Pai: 2 Vertice Filho: 3 Peso Aresta: 2.0\n" +
        					"Vertice Pai: 2 Vertice Filho: 1 Peso Aresta: 6.0\n";
        
    
        assertEquals(esperado, saida);
    	
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
