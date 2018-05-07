import model.Aresta;
import model.Grafo;
import model.Vertice;

import java.io.*;
import java.util.*;

/**
 * Classe responsável pela leitura de dados.
 */
public class IO {

    private final int DEFAULT_WEIGHT = 1;

    @FunctionalInterface
    private interface ProcessArgs {
        void process(String[] args);
    }

    private void readArchive(String path, ProcessArgs processArgs) {
        File file = new File(path);
        try {
            Scanner input = new Scanner(new FileReader(file));
            input.nextLine();
            while (input.hasNext()) {
                String[] arguments = input.nextLine().split(" ");
                processArgs.process(arguments);
            }
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Grafo read (String path) {
        Grafo grafo = new Grafo();
        Set<Aresta> arestas = new HashSet<>();
        Set<Vertice> vertices = new HashSet<>();

        readArchive(path, (args) -> {
            Vertice vInicial = new Vertice(Integer.parseInt(args[0]));
            Vertice vFinal = new Vertice(Integer.parseInt(args[1]));
            Aresta aresta = new Aresta(vInicial, vFinal, DEFAULT_WEIGHT);

            arestas.add(aresta);
            vertices.add(vInicial);
            vertices.add(vFinal);
            System.out.println(Arrays.toString(args));
        });

        grafo.setArestas(arestas);
        grafo.setVertices(vertices);
        return grafo;
    }

    public Grafo readWeightedGraph (String path) {
        // implementar
        return null;
    }

}
