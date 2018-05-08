package model;

/**
 * Classe que representa a entidade Atesta.
 */
public class Aresta {

    private Vertice verticeInicial;
    private Vertice verticeFinal;
    private float peso;

    public Aresta(Vertice verticeInicial, Vertice verticeFinal, float peso) {
        this.verticeInicial = verticeInicial;
        this.verticeFinal = verticeFinal;
        this.peso = peso;
    }

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Aresta) {
            Aresta aresta = (Aresta) obj;
            return aresta.verticeInicial == this.verticeInicial && aresta.verticeFinal == this.verticeFinal;
        }

        return false;
    }
}
