package dev.enricogollner.crudappandroidjava.models;

public class CompostoQuimico {
    public Long idComponente;
    public String nome;
    public String formula;
    public String unidadeMedida;

    @Override
    public String toString() {
        return this.nome + " | Fórmula: " + this.formula;
    }

}
