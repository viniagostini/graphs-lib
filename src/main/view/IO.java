package main.view;

import main.model.Aresta;
import main.model.Grafo;
import main.model.Vertice;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Classe responsável pela leitura de dados.
 */
public class IO {

    private final double DEFAULT_WEIGHT = 1;
    private final int FIRST_LINE = 1;

    @FunctionalInterface
    private interface ProcessArgs {
        void process(String[] args, int line);
    }

    private void readArchive(String path, ProcessArgs processArgs) {
        File file = new File(path);
        try {
            Scanner input = new Scanner(new FileReader(file));
            int line = FIRST_LINE;
            while (input.hasNext()) {
                String[] arguments = input.nextLine().split(" ");
                processArgs.process(arguments, line);
                line++;
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Lê e constrói um {@link Grafo} sem peso a partir das informações do arquivo passado.
     *
     * @param path Caminho para o arquivo que contém as informações do Grafo.
     *
     * @return o grafo contruído.
     */
    public Grafo read (String path) {
        return readGraph(path, false);
    }

    /**
     * Lê e constrói um {@link Grafo} com peso a partir das informações do arquivo passado.
     *
     * @param path Caminho para o arquivo que contém as informações do Grafo.
     *
     * @return o grafo contruído.
     */
    public Grafo readWeightedGraph (String path) {
        return readGraph(path, true);
    }

    private Grafo readGraph(String path, boolean isWeighted) {
        Grafo grafo = new Grafo();
        Set<Aresta> arestas = new HashSet<>();

        readArchive(path, (args, line) -> {
            if (line == FIRST_LINE) {
                int nVertex = Integer.parseInt(args[0]);
                Set<Vertice> vertices = generateVertexes(nVertex);

                grafo.setVertices(vertices);
            } else {
                Vertice vInicial = grafo.getVerticeById(Integer.parseInt(args[0]));
                Vertice vFinal = grafo.getVerticeById(Integer.parseInt(args[1]));
                double weight = isWeighted ? Double.parseDouble(args[2]) : DEFAULT_WEIGHT;
                Aresta aresta = new Aresta(vInicial, vFinal, weight);

                arestas.add(aresta);
            }
        });

        grafo.setArestas(arestas);
        grafo.setPonderado(isWeighted);
        System.out.println(grafo.shortestPath(1, 5));
        System.out.println(grafo.mst());
        return grafo;
    }

    /**
     * Gera um Set de {@link Vertice} com vértices com id´s de 1 ... nVertex
     *
     * @param nVertex número de vértices a serem gerados.
     *
     * @return o Set gerado.
     */
    private Set<Vertice> generateVertexes (int nVertex) {
        return IntStream
                .range(0, nVertex)
                .mapToObj(i -> new Vertice(i+1))
                .collect(Collectors.toSet());
    }
}
