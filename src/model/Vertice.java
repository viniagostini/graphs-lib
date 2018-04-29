package model;

/**
 * Classe que representa a entidade Vértice.
 */
public class Vertice {

    private Integer id;
    private Boolean visitado;

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
}
