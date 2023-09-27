package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.dao.VendedorDAO;
import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.StringUtil;
import br.gov.cesarschool.poo.bonusvendas.negocio.geral.ValidadorCPF;

import java.time.LocalDate;
import java.time.Period;

public class VendedorMediator {
    private static VendedorMediator instance;

    private VendedorDAO repositorioVendedor;
    private AcumuloResgateMediator caixaDeBonusMediator;

    private VendedorMediator() {
        this.caixaDeBonusMediator = AcumuloResgateMediator.getInstance();
        this.repositorioVendedor = new VendedorDAO();
    }

    public static VendedorMediator getInstance() {
        if (instance == null) {
            instance = new VendedorMediator();
        }
        return instance;
    }

    public ResultadoInclusaoVendedor incluir(Vendedor vendedor) {
        String validacao = validar(vendedor);
        if (validacao != null) {
            return new ResultadoInclusaoVendedor(0, validacao);
        }
        if (!this.repositorioVendedor.incluir(vendedor)) {
            return new ResultadoInclusaoVendedor(0, "Vendedor ja existente");
        }
        long numero = caixaDeBonusMediator.gerarCaixaDeBonus(vendedor);
        if (numero == 0) {
            return new ResultadoInclusaoVendedor(numero, "Caixa de bonus nao foi gerada");
        }
        return new ResultadoInclusaoVendedor(numero, null);
    }

    public String alterar(Vendedor vendedor) {
        String validacao = validar(vendedor);
        if (validacao != null) {
            return validacao;
        }
        if (!this.repositorioVendedor.alterar(vendedor)) {
            return "Vendedor inexistente";
        }
        return null;
    }

    private String validar(Vendedor vendedor) {
        if (StringUtil.ehNuloOuBranco(vendedor.getCpf())){
            return "CPF nao informado";
        }
        else if (!ValidadorCPF.ehCpfValido(vendedor.getCpf())) {
            return "CPF invalido";
        }
        if (StringUtil.ehNuloOuBranco(vendedor.getNomeCompleto())) {
            return "Nome completo nao informado";
        }
        if (vendedor.getSexo() == null) {
            return "Sexo nao informado";
        }
        if (vendedor.getDataNascimento() == null) {
            return "Data de nascimento nao informada";
        } else if (Period.between(LocalDate.now(), vendedor.getDataNascimento()).getYears() >= 18) {
            return "Data de nascimento invalida";
        }
        if (vendedor.getRenda() < 0) {
            return "Renda menor que zero";
        }
        if (vendedor.getEndereco() == null) {
            return "Endereco nao informado";
        } else if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getLogradouro())) {
            return "Logradouro nao informado";
        } else if (vendedor.getEndereco().getLogradouro().length() < 4) {
            return "Logradouro tem menos de 04 caracteres";
        } else if (vendedor.getEndereco().getNumero() < 0) {
            return "Numero menor que zero";
        } else if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getCidade())) {
            return "Cidade nao informada";
        } else if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getEstado())) {
            return "Estado nao informado";
        } else if (StringUtil.ehNuloOuBranco(vendedor.getEndereco().getPais())) {
            return "Pais nao informado";
        }

        return null;

    }


}
