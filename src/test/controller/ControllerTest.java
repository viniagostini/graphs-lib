package test.controller;


import main.controller.Controller;
import main.model.Aresta;
import main.model.Grafo;
import main.model.Representacao;
import main.model.Vertice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


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
    	Grafo grafo = new Grafo();
    	Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();
        
        Vertice v1 = new Vertice(1);
        Aresta a1 = new Aresta(v1,v1,1);
        Vertice v2 = new Vertice(2);
        vertices.add(v1);vertices.add(v2);
        arestas.add(a1);
        grafo.setPonderado(false);
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);
        String resultAL = grafo.graphRepresentation(Representacao.AL);
        String resultAM = grafo.graphRepresentation(Representacao.AM);
        
        assertEquals(("1- 1 \n" + "2- \n"), resultAL, "A representação deve estar correta");
        assertEquals(("Matriz de adjacência\n" + 
        		"     [[1], [2]]\n" + 
        		"[1]    1    0\n" + 
        		"[2]    0    0"), resultAM, "A representação deve estar correta");

        
        Vertice v3 = new Vertice(3);
        Aresta a2 = new Aresta(v2,v1,0.3);
        Aresta a3 = new Aresta(v2,v3,0.9);
        Aresta a4 = new Aresta(v3,v2,0.7);
        vertices.add(v3);
        arestas.add(a2); arestas.add(a3); arestas.add(a4);
        grafo.setPonderado(true);
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);
        
        
        
        String resultDirecionadoAL = grafo.graphRepresentation(Representacao.AL);
        String resultDirecionadoAM = grafo.graphRepresentation(Representacao.AM);
        assertEquals(("1- 1 \n" + 
        		"2- 1 3 \n" + 
        		"3- 2 \n"), resultDirecionadoAL, "A representação deve estar correta");
        assertEquals(("Matriz de adjacência\n" + 
        		"     [[1], [2], [3]]\n" + 
        		"[1]  1.0    0    0\n" + 
        		"[2]  0.3    0  0.9\n" + 
        		"[3]    0  0.7    0"), resultDirecionadoAM, "A representação deve estar correta");

    }

    @Test
    void BFSTest() {

        Grafo grafo = new Grafo();

        Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();

        Vertice v1 = new Vertice(1);

        vertices.add(v1);

        grafo.setVertices(vertices);

        assertEquals("1 - 0 -\n", controller.BFS(grafo, v1), "A busca deve retornar apenas o vertice raiz");

        Vertice v2 = new Vertice(2);
        vertices.add(v2);

        Aresta a1 = new Aresta(v1,v2,1);
        arestas.add(a1);
        Aresta a2 = new Aresta(v2,v1, 1);
        arestas.add(a2);

        grafo.setVertices(vertices);
        grafo.setArestas(arestas);

        assertEquals("1 - 0 -\n2 - 1 1\n", controller.BFS(grafo, v1), "A busca deve retornar o vertice raiz e seu filho");

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


       // assertEquals("1 - 0 - \n2 - 1 1\n3 - 2 5\n4 - 2 5\n5 - 1 1\n" , controller.BFS(grafo, v1),
       //         "A busca retorna o vertice raiz e todos os vértices do grafo em largura");
    }

    @Test
    void DFSTest() {
    }

    @Test
    void connectedTest() {
    	Grafo grafo = new Grafo();
    	Set<Vertice> vertices = new HashSet<>();
        Set<Aresta> arestas = new HashSet<>();
        
        //Vertice não ponderado
        Vertice v1 = new Vertice(1);
        Vertice v2 = new Vertice(2);
        Aresta a1 = new Aresta(v1,v1,1);
        Aresta a2 = new Aresta(v2,v2,1);
        vertices.add(v1);vertices.add(v2);
        arestas.add(a1); arestas.add(a2);
        
        grafo.setPonderado(false);
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);
        
        assertFalse(grafo.connected(), "O grafo não é conexo");
        Aresta a3 = new Aresta(v2,v1,1);
        arestas.add(a3);
        assertTrue(grafo.connected(), "O grafo é conexo");
        
        //Vertice ponderado
        Vertice v3 = new Vertice(3);
        Aresta a4 = new Aresta(v3,v3,0.9);
        Aresta a5 = new Aresta(v1,v3,0.7);
        vertices.add(v3);
        arestas.add(a4); arestas.add(a5); 
        
        grafo.setPonderado(true);
        grafo.setArestas(arestas);
        grafo.setVertices(vertices);
        
        assertTrue(grafo.connected(), "O grafo é conexo");
        
        arestas.remove(a3);
        grafo.setArestas(arestas);
        assertTrue(grafo.connected(), "O grafo ainda é conexo");
        
        arestas.remove(a5);
        grafo.setArestas(arestas);
        assertTrue(grafo.connected(), "O grafo não é mais conexo");
        
    }

    @Test
    void shortestPathTest() {
    }

    @Test
    void mstTest() {
    }
}
