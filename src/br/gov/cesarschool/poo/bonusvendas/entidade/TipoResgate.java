package br.gov.cesarschool.poo.bonusvendas.entidade;

public enum TipoResgate {
    PRODUTO(1, "Produto"),
    SERVICO(2, "Serviço"),
    CASH(3, "Cash");

    int codigo;
    String nome;

    TipoResgate(int codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }
    public int getCodigo() {
        return codigo;
    }
    public String getNome() {
        return nome;
    }
    public static TipoResgate obterTipo(int codigo) {
        for (TipoResgate tipoResgate : TipoResgate.values()) {
            if (tipoResgate.codigo == codigo) {
                return tipoResgate;
            }
        }
        return null;
    }

}
