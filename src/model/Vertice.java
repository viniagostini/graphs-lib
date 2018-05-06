package model;

/**
 * Classe que representa a entidade Vértice.
 */
public class Vertice {

    private Integer id;
    private Boolean visitado;

    public Vertice(int id) {
        this .id = id;
        this.visitado = false;
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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertice) {
            Vertice vertice = (Vertice) obj;
            return vertice.id == this.id;
        }

        return false;
    }
}
