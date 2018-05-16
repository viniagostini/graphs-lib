package main.model;

import java.util.Objects;

/**
 * Classe que representa a entidade Vértice.
 */
public class Vertice {

    private enum Cor {
        BRANCO, CINZA, PRETO;
    }

    private Integer id;
    private Boolean visitado;
    private Cor cor;

    public Vertice(int id) {
        this .id = id;
        this.visitado = false;
        this.cor = Cor.BRANCO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getVisitado() {
        return visitado;
    }

    public void setVisitado(Boolean visitado) {
        this.visitado = visitado;
    }

    public Cor getCor() {
        return cor;
    }

    public void setCor(Cor cor) {
        this.cor = cor;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertice) {
            Vertice vertice = (Vertice) obj;
            return vertice.id == this.id;
        }

        return false;
    }
    
    @Override
 	public String toString() {
 		return "[" + id + "]";
 	}
}
