package model;

/**
 * Classe que representa a entidade Atesta.
 */
public class Aresta {

    private Vertice verticeInicial;
    private Vertice verticeFinal;
    private float peso;

    public Vertice getVerticeInicial() {
        return verticeInicial;
    }

    public void setVerticeInicial(Vertice verticeInicial) {
        this.verticeInicial = verticeInicial;
    }

    public Vertice getVerticeFinal() {
        return verticeFinal;
    }

    public void setVerticeFinal(Vertice verticeFinal) {
        this.verticeFinal = verticeFinal;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
