package dev.enricogollner.crudappandroidjava.models;

public class CompostoQuimico {
    public Long idComposto;
    public String nome;
    public String formula;
    public String unidadeMedida;

    public CompostoQuimico() {
    }

    public CompostoQuimico(long id, String nome, String formula, String unidadeMedida) {
        this.idComposto = id;
        this.nome = nome;
        this.formula = formula;
        this.unidadeMedida = unidadeMedida;
    }

    @Override
    public String toString() {
        return this.nome + " | Fórmula: " + this.formula;
    }
}
