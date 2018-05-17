package main.view;

/**
 * Created by viniagostini on 28/04/2018.
 */
public class Main {

    public static void main(String[] args) {
    	IO io = new IO();
        System.out.println(io.readWeightedGraph("testWeightedGraph.txt"));
    }
}
