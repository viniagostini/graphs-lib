package main.model;


import java.util.*;

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

    public Grafo () {
        arestas = new HashSet<>();
        vertices = new HashSet<>();
    }


    public Vertice getVertice(int id) {
	    for (Vertice vertice: this.vertices) {
	        if (vertice.getId() == id) {
	            return vertice;
            }
        }

        return null;
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

    /**
     * Retorna o resultado do cálculo do Grau Médio do Grafo.
     */
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

    /**
     * Realiza busca em profundidade no grafo a partir do Vértice raiz passado como parâmetro.
     */
    public String BFS (Vertice v) {

        StringBuilder saida = new StringBuilder();
        Queue<Vertice> fila = new LinkedList<>();
        int nivel = 0;

        saida.append(v.getId()).append(" - ").append(nivel).append(" -").append("\n");
        v.setVisitado(true);
        fila.add(v);

        while(!fila.isEmpty()) {

            boolean nivelVisitado = false;
            Vertice pai = fila.remove();
            Vertice filho = null;

            while ((filho = getFilhoNVisitado(pai)) != null) {

                if(!nivelVisitado){
                    nivelVisitado = true;
                    nivel++;
                }

                filho.setVisitado(true);
                fila.add(filho);

                saida.append(filho.getId()).append(" - ").append(nivel).append(" ").append(pai.getId()).append("\n");
            }
        }

        limpaVertices();
        return saida.toString();
    }

    private Vertice getFilhoNVisitado(Vertice pai) {

        for (Aresta aresta : arestas) {
            Vertice v1 = aresta.getVerticeInicial();
            Vertice v2 = aresta.getVerticeFinal();

            if (v1.equals(pai) && !(v2.getVisitado())) {
                return v2;
            } else if (v2.equals(pai) && !(v1.getVisitado())) {
                return v1;
            }
        }
        return null;
    }

    private void limpaVertices(){

        for (Vertice vertice : vertices) {
            vertice.setVisitado(false);
            vertice.setCor(Vertice.Cor.BRANCO);
        }
    }

    public String DFS (Vertice v) {
        String visita = visitaDFS(v, 0);
        limpaVertices();
        return visita;
    }

    private String visitaDFS(Vertice v, int nivel) {
        String saida = "";

        if (nivel == 0) {
            v = getVertice(v.getId());
            saida += v.getId() + " - 0 -" + System.lineSeparator();
        }

        v.setCor(Vertice.Cor.PRETO);

        LinkedList<Vertice> adjs = new LinkedList<>();

        for (Aresta aresta : this.arestas) {
            Vertice vInicial = aresta.getVerticeInicial();
            Vertice vFinal = aresta.getVerticeFinal();

            if (vInicial.equals(v) && vFinal.getCor() == Vertice.Cor.BRANCO) {
                vFinal.setCor(Vertice.Cor.CINZA);
                adjs.add(vFinal);
            } else if (vFinal.equals(v) && vInicial.getCor() == Vertice.Cor.BRANCO) {
                vFinal.setCor(Vertice.Cor.CINZA);
                adjs.add(aresta.getVerticeInicial());
            }
        }

        Iterator<Vertice> iter = adjs.iterator();

        while (iter.hasNext()) {
            Vertice vertice = iter.next();
            saida += vertice.getId() + " - " + v.getId() + " " + (nivel + 1) + System.lineSeparator();
            saida += visitaDFS(vertice, nivel + 1);
        }

        return saida;
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

		System.out.println("\n O peso � " + peso_mst);

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
