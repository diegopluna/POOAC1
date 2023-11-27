package br.gov.cesarschool.poo.bonusvendas.dao;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.entidade.geral.Registro;


public class VendedorDAO {

    private DAOGenerico dao;

    public VendedorDAO() {
        this.dao = new DAOGenerico(Vendedor.class);
    }

    public boolean incluir(Vendedor vend) {
        return dao.incluir(vend);
    }

    public boolean alterar(Vendedor vend) {
        return dao.alterar(vend);
    }

    public Vendedor buscar(String cpf) {
        return (Vendedor)dao.buscar(cpf);
    }

    public Vendedor[] buscarTodos() {
        Registro[] regs = dao.buscarTodos();
        Vendedor[] vends = new Vendedor[regs.length];
        for (int i = 0; i < regs.length; i++) {
            vends[i] = (Vendedor)regs[i];
        }
        return vends;
    }
}
