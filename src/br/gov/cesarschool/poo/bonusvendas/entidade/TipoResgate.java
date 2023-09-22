package br.gov.cesarschool.poo.bonusvendas.entidade;

public enum TipoResgate {
    PRODUTO(1, "Produto"),
    SERVICO(2, "Serviço"),
    CASH(3, "Cash");

    int codigo;
    String descricao;

    TipoResgate(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

}
