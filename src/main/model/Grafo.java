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
	 * Realiza busca em largura no grafo a partir do Vértice raiz
	 * passado como parâmetro.
	 * @param raiz do {@link Grafo}
	 * @return string das visitas
	 */
	public String BFS(Vertice raiz) {

		StringBuilder saida = new StringBuilder();
		Queue<Vertice> fila = new LinkedList<>();
		int nivel = 0;

		saida.append(raiz.getId()).append(" - ").append(nivel).append(" -").append("\n");
		raiz.setVisitado(true);
		fila.add(raiz);

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

	/**
	 * Encontra o próximo filho não visitado de um vértice.
	 * @param pai
	 * @return filho
	 */
	private Vertice getFilhoNVisitado(Vertice pai) {

		Vertice filho = null;
  
		for (Aresta aresta : arestas) {
			Vertice v1 = aresta.getVerticeInicial();
			Vertice v2 = aresta.getVerticeFinal();

			if (v1.equals(pai) && !(v2.getVisitado())) {
				filho = v2;
				return filho;
			} else if (v2.equals(pai) && !(v1.getVisitado())) {
				filho = v1;
				return filho;
			}
		}
		return filho;
	}

	/**
	 * Limpa as visitas e cores colocadas em todos os vértices
	 */
	private void limpaVertices() {

		for (Vertice vertice : vertices) {
			vertice.setVisitado(false);
			vertice.setCor(Vertice.Cor.BRANCO);
		}
	}

	/**
	 * Caminha em profundidade pelo grafo e constroi uma {@link String} que
	 * repesenta o resultado do caminho.
	 *
	 * @param v Verticide inicial do {@link Grafo}
	 * @return A string construída
	 */
	public String DFS(Vertice v) {
		String visita = visitaDFS(v, 0);
		limpaVertices();
		return visita;
	}

	/**
	 * Visita os vertices do {@link Grafo} e constrói uma {@link String} que
	 * representa o resultado do caminho em profundidade no {@link Grafo}
	 *
	 * @param v     Vertice a ser visitado
	 * @param nivel Nível em relação ao vertice inicial em que o vértice à ser
	 *              visitado está
	 * @return A String contruída
	 */
	private String visitaDFS(Vertice v, int nivel) {

		String saida = "";

		if (nivel == 0) {
			v = getVerticeById(v.getId());
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
	 * @param v1: vertice Inicial
	 * @param v2: vertice final
	 * @return retorna o peso do menor caminho entre dois vertices
	 */
	public String shortestPath(int v1, int v2) {
		String result = "";
		double[][] matrix = this.matrix();
		double[][] dist = new double[vertices.size()][vertices.size()];
		for (double[] row : dist)
			Arrays.fill(row, Double.POSITIVE_INFINITY);

		for (int i = 0; i < vertices.size(); i++)
			for (int j = 0; j < vertices.size(); j++)

				dist[i][j] = this.matrix[i][j];

		int[][] next = new int[vertices.size()][vertices.size()];
		for (int i = 0; i < next.length; i++) {
			for (int j = 0; j < next.length; j++)
				if (i != j)
					next[i][j] = j + 1;
		}

		for (int k = 0; k < vertices.size(); k++)
			for (int i = 0; i < vertices.size(); i++)
				for (int j = 0; j < vertices.size(); j++)
					if (dist[i][k] + dist[k][j] < dist[i][j]) {
						dist[i][j] = dist[i][k] + dist[k][j];
						next[i][j] = next[i][k];
					}

		int verticeInicial = v1;
		int verticeFinal = v2;
		result += verticeInicial + "->" + verticeFinal + "      " + verticeInicial;
		while (verticeInicial != verticeFinal) {
			verticeInicial = next[verticeInicial - 1][verticeFinal - 1];
			result += " -> " + verticeInicial;
		}

		return result;
	}

	/**
	 * Encontra o pai de um nó do grafo, a ponto de encontrar ciclos
	 * 
	 * @return chamada recursiva para percorrer o grafo
	 */
	private int find(int id_vertice) {
		if (pai[id_vertice] == id_vertice) {
			return id_vertice;
		}
		return find(pai[id_vertice]);
	}

	/**
	 * Conecta dois nós de um grafo para gerar a arvore minima
	 */
	private void unite(int id_v1, int id_v2) {
		int fx = find(id_v1);
		int fy = find(id_v2);
		pai[fx] = fy;
	}

	public String mst() {
		String result = "";
		double peso_mst = 0;
		int vertices_mst = 0;
		int verticeInicial, verticeFinal;
		double peso;

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
			verticeInicial = arrayArestas.get(vertices_mst).getVerticeInicial().getId();
			verticeFinal = arrayArestas.get(vertices_mst).getVerticeFinal().getId();
			peso = arrayArestas.get(vertices_mst).getPeso();

			if (this.find(verticeInicial) != this.find(verticeFinal)) {
				this.unite(verticeInicial, verticeFinal);
				peso_mst += peso;
				result += "Vertice Pai: " + verticeInicial + " Vertice Filho: " + verticeFinal + " Peso Aresta: " + peso
						+ "\n";
			}
			vertices_mst++;
		}
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
