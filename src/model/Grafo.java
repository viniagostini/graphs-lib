package model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;



/**
 * Classe que representa a entridade Grafo.
 */
public class Grafo {

    private Set<Aresta> arestas;
    private Set<Vertice> vertices;
    
private int pai[] = new int[100];
	
	public int find(int x) {
		if(pai[x] == x) {
			return x;
		}return find(pai[x]);
	}
	
	public void unite(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		pai[fx]=fy;
	}

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
    	double peso_mst = 0;
		int vertices_mst = 0;
		//int arestas_mst= 0;
		int a,b;
		double w;
		
		for (int i = 0; i < pai.length; i++) {
			pai[i]=i;
		}
		
		ArrayList<Aresta> arrayArestas = new ArrayList<Aresta>();
		for (Aresta aresta : arestas) {
			arrayArestas.add(aresta);
		}
		
		ArrayList<Vertice> arrayVertices = new ArrayList<Vertice>();
		for (Vertice vertice : vertices) {
			arrayVertices.add(vertice);
		}
		
		Collections.sort(arrayArestas, new Comparator<Aresta>() {
			@Override public int compare(Aresta a1, Aresta a2) {
				return (int) (a1.getPeso() - a2.getPeso());
			}
		});
		
		System.out.println(arrayArestas.size());
		System.out.println(arrayVertices.size());
		
		while( (vertices_mst < arrayArestas.size())) {
			a= arrayArestas.get(vertices_mst).getVerticeInicial().getId();
			b= arrayArestas.get(vertices_mst).getVerticeFinal().getId();
			w = arrayArestas.get(vertices_mst).getPeso();
			
			if( this.find(a) != this.find(b)) {
				this.unite(a, b);
				peso_mst += w;
				System.out.println(a + " " + " " + b + " " + w);
				//arestas_mst++;
			}
			vertices_mst++;
		}
		
		System.out.println("\n O peso é " + peso_mst);
    	
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
