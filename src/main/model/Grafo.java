package main.model;

import java.util.*;

/**
 * Classe que representa a entridade Grafo.
 */
public class Grafo {

	private Set<Aresta> arestas;
	private Set<Vertice> vertices;
	private boolean ponderado;
	private double matrix[][];

	private int pai[] = new int[100];

	public Grafo() {
		arestas = new HashSet<>();
		vertices = new HashSet<>();
	}

	public Vertice getVertice(int id) {

		for (Vertice vertice : this.vertices) {
			if (vertice.getId() == id) {
				return vertice;
			}
		}

		return null;
	}

	/**
	 * Retorna o número de vértices do Grafo.
	 */
	public int getVertexNumber() {
		return vertices.size();
	}

	/**
	 * Retorna o número de arestas do Grafo.
	 */
	public int getEdgeNumber() {
		return arestas.size();
	}

	/**
	 * Retorna o resultado do cálculo do Grau Médio do Grafo.
	 */
	public float getMeanEdge() {
		return (2 * arestas.size()) / vertices.size();
	}

	public String graphRepresentation(Representacao type) {
		if (type == Representacao.AL) {
			return graphAdjacencyList();
		} else {
			return graphAdjacencyMatrix();
		}
	}

	/**
	 * Realiza busca em profundidade no grafo a partir do Vértice raiz passado como
	 * parâmetro.
	 */
	public String BFS(Vertice v) {

		StringBuilder saida = new StringBuilder();
		Queue<Vertice> fila = new LinkedList<>();
		int nivel = 0;

		saida.append(v.getId()).append(" - ").append(nivel).append(" -").append("\n");
		v.setVisitado(true);
		fila.add(v);

		while (!fila.isEmpty()) {

			boolean nivelVisitado = false;
			Vertice pai = fila.remove();
			Vertice filho = null;

			while ((filho = getFilhoNVisitado(pai)) != null) {

				if (!nivelVisitado) {
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

	private void limpaVertices() {

		for (Vertice vertice : vertices) {
			vertice.setVisitado(false);
			vertice.setCor(Vertice.Cor.BRANCO);
		}
	}

	public String DFS(Vertice v) {
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

	/**
	 * Verifica se um grafo é conexo, ou seja, existe um caminho entre todos os
	 * vértices
	 * 
	 * @return retorna true se o grafo for conexo e false se não for conexo
	 */
	public boolean connected() {
		boolean connected = true;
		Queue<Vertice> fila = new LinkedList<>();
		if (!this.vertices.isEmpty()) {
			Vertice v = null;
			for (Vertice vertice : this.vertices) {
				v = vertice;
				break;
			}
			v.setVisitado(true);
			fila.add(v);
			while (!fila.isEmpty()) {
				Vertice pai = fila.remove();
				Vertice filho = null;
				while ((filho = getFilhoNVisitado(pai)) != null) {
					filho.setVisitado(true);
					fila.add(filho);
				}
			}

			for (Vertice vertice : vertices) {
				if (!vertice.getVisitado()) {
					connected = false;
				}
			}
		} else {
			connected = false;
		}
		return connected;
	}

	/**
	 * Monta uma matriz de adjancencia e respectivos pesos entre vertices
	 * 
	 * @return retorna o peso do menor caminho entre dois vertices
	 */
	public double[][] matrix() {
		this.matrix = new double[vertices.size()][vertices.size()];

		for (Aresta aresta : arestas) {
			matrix[aresta.getVerticeInicial().getId() - 1][aresta.getVerticeFinal().getId() - 1] = aresta.getPeso();
			matrix[aresta.getVerticeFinal().getId() - 1][aresta.getVerticeInicial().getId() - 1] = aresta.getPeso();
		}

		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] == 0) {
					matrix[i][j] = 99999;
				}
			}
		}

		return matrix;
	}

	/**
	 * Calcula o menor caminho entre dois vertices
	 * 
	 * @param v1:
	 *            vertice Inicial
	 * @param v2:
	 *            vertice final
	 * @return retorna o peso do menor caminho entre dois vertices
	 */
	public String shortestPath(int v1, int v2) {

		double[][] matrix = this.matrix();

		ArrayList<Vertice> arrayVertices = new ArrayList<Vertice>();
		for (Vertice vertice : vertices) {
			arrayVertices.add(vertice);
		}

		double dist[][] = new double[arrayVertices.size()][arrayVertices.size()];
		int i, j, k;
		for (i = 0; i < vertices.size(); i++)
			for (j = 0; j < vertices.size(); j++)

				dist[i][j] = this.matrix[i][j];

		for (k = 0; k < vertices.size(); k++) {

			for (i = 0; i < vertices.size(); i++) {

				for (j = 0; j < vertices.size(); j++) {
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}

		String result = "O menor entre os vertices " + v1 + " e " + v2 + " �: " + dist[v1 - 1][v2 - 1];
		return result;
	}

	public int calculaNivel(Vertice v) {
		Queue<Vertice> fila = new LinkedList<>();
		int nivel = 0;

		v.setVisitado(true);
		fila.add(v);

		while (!fila.isEmpty()) {

			boolean nivelVisitado = false;
			Vertice pai = fila.remove();
			Vertice filho = null;

			while ((filho = getFilhoNVisitado(pai)) != null) {

				if (!nivelVisitado) {
					nivelVisitado = true;
					nivel++;
				}

				filho.setVisitado(true);
				fila.add(filho);

			}
		}

		limpaVertices();
		return nivel;

	}

	/**
	 * Encontra o pai de um n� do grafo, a ponto de encontrar ciclos
	 * 
	 * @return chamada recursiva para percorrer o grafo
	 */
	public int find(int x) {
		if (pai[x] == x) {
			return x;
		}
		return find(pai[x]);
	}

	/**
	 * Conecta dois n�s de um grafo para gerar a arvore minima
	 */
	public void unite(int x, int y) {
		int fx = find(x);
		int fy = find(y);
		pai[fx] = fy;
	}

	/**
	 * Monta uma arvore de cobertura minima
	 * 
	 * @return retorna uma String na qual exibe o a arvore de cobertura minima
	 */
	public String mst() {
		String result = "";
		double peso_mst = 0;
		int vertices_mst = 0;
		int a, b;
		int nivel = 0;
		double w;

		for (int i = 0; i < pai.length; i++) {
			pai[i] = i;
		}
		ArrayList<Aresta> arrayArestas = new ArrayList<Aresta>();
		for (Aresta aresta : arestas) {
			arrayArestas.add(aresta);
		}

		Collections.sort(arrayArestas, new Comparator<Aresta>() {
			@Override
			public int compare(Aresta a1, Aresta a2) {
				if (a1.getPeso() < a2.getPeso())
					return -1;
				if (a1.getPeso() > a2.getPeso())
					return 1;
				return 0;

			}
		});
		while ((vertices_mst < arrayArestas.size())) {
			a = arrayArestas.get(vertices_mst).getVerticeInicial().getId();
			b = arrayArestas.get(vertices_mst).getVerticeFinal().getId();
			w = arrayArestas.get(vertices_mst).getPeso();

			if (this.find(a) != this.find(b)) {
				this.unite(a, b);
				peso_mst += w;

				result += "Vertice Pai: " + a + " Vertice Filho: " + b + " Nivel: " + " Peso Aresta: " + w + "\n";
			}

			vertices_mst++;
		}
		System.out.println("\n O peso final e: " + peso_mst);
		return result;
	}

	/**
	 * Retorna uma representação do grafo em matriz de adjacências
	 * 
	 * @return representação do tipo string
	 */
	private String graphAdjacencyMatrix() {
		StringBuilder matrixAdjacencias = new StringBuilder();
		matrixAdjacencias.append("Matriz de adjacência" + System.getProperty("line.separator"));
		matrixAdjacencias.append("     " + Arrays.toString(this.vertices.toArray()));

		for (Vertice vLinha : this.vertices) {
			matrixAdjacencias.append(System.getProperty("line.separator") + "[" + vLinha.getId() + "]");
			for (Vertice vColuna : this.vertices) {
				boolean adjacente = false;
				double peso = 0;
				for (Aresta aresta : arestas) {
					if (aresta.getVerticeInicial().equals(vLinha) && aresta.getVerticeFinal().equals(vColuna)) {
						adjacente = true;
						peso = aresta.getPeso();
						break;
					}
				}

				if (adjacente) {
					matrixAdjacencias.append(this.isPonderado() ? "  " + Double.toString(peso) : "    1");
				} else {
					matrixAdjacencias.append("    0");
				}
			}
		}

		return matrixAdjacencias.toString();
	}

	private boolean checkAdjacent(Vertice verticeA, Vertice verticeB) {
		boolean adjacente = false;
		for (Aresta aresta : arestas) {
			if (aresta.getVerticeInicial().equals(verticeA) && aresta.getVerticeFinal().equals(verticeB)) {
				adjacente = true;
				break;
			}
		}
		return adjacente;
	}

	/**
	 * Retorna uma representação do grafo em lista de adjacências
	 * 
	 * @returnrepresentação do tipo string
	 */
	private String graphAdjacencyList() {
		StringBuilder listaAdjacencias = new StringBuilder();
		for (Vertice vAtual : vertices) {
			listaAdjacencias.append(vAtual.getId() + "- ");
			for (Aresta aresta : arestas) {
				if (aresta.getVerticeInicial().equals(vAtual)) {
					StringBuilder celula = new StringBuilder();
					celula.append("-> " + aresta.getVerticeFinal().getId());
					celula.append((this.isPonderado() ? ("(" + Double.toString(aresta.getPeso()) + ")") : " "));
					listaAdjacencias.append(aresta.getVerticeFinal().getId() + " ");
				}
			}
			listaAdjacencias.append(System.getProperty("line.separator"));
		}
		return listaAdjacencias.toString();
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

	public boolean isPonderado() {
		return ponderado;
	}

	public void setPonderado(boolean ponderado) {
		this.ponderado = ponderado;
	}

	public void setVertices(Set<Vertice> vertices) {
		this.vertices = vertices;
	}

	public Vertice getVerticeById(int id) {
		return this.vertices.stream().filter(vertice -> vertice.getId() == id).findFirst().get();
	}

	@Override
	public String toString() {
		return "Grafo : {" + "arestas: " + arestas + ", vertices: " + vertices + '}';
	}
}
