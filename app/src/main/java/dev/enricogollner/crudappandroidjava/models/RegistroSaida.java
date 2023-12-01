package dev.enricogollner.crudappandroidjava.models;

public class RegistroSaida {
    private Long idRegistro;
    private CompostoQuimico compostoQuimico;

    private double quantidade;

    public Long getIdRegistro() {
        return idRegistro;
    }

    public CompostoQuimico getCompostoQuimico() {
        return compostoQuimico;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public String getObservacoes() {
        return observacoes;
    }

    private String observacoes;

    public RegistroSaida(Long idRegistro, CompostoQuimico compostoQuimico, double quantidade, String observacoes) {
        this.idRegistro = idRegistro;
        this.compostoQuimico = compostoQuimico;
        this.quantidade = quantidade;
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return this.compostoQuimico.nome + " | Quantidade: " + this.quantidade + this.compostoQuimico.unidadeMedida + "\nObservações" + this.observacoes;
    }
}
