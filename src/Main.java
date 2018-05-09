import model.Grafo;
import model.*;


/**
 * Created by viniagostini on 28/04/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Driele!!");
        IO io = new IO();
        Grafo graph = io.read("test.txt");
        System.out.println(graph.toString());        
        System.out.println(graph.graphRepresentation(Representacao.AL));
        
    }



}
