package model;

import java.util.Objects;

/**
 * Classe que representa a entidade Atesta.
 */
public class Aresta {

    private Vertice verticeInicial;
    private Vertice verticeFinal;
    private double peso;

    public Aresta(Vertice verticeInicial, Vertice verticeFinal, double peso) {
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

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {

        return Objects.hash(verticeInicial, verticeFinal, peso);
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
