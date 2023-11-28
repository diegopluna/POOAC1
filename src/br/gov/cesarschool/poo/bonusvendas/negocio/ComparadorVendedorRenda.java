package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorVendedorRenda implements Comparador {
    private static ComparadorVendedorRenda instance;
    public static ComparadorVendedorRenda getInstance() {
        if (instance == null) {
            instance = new ComparadorVendedorRenda();
        }
        return instance;
    }
    private ComparadorVendedorRenda(){}

    public int comparar(Object o1, Object o2) {
        Vendedor v1 = (Vendedor) o1;
        Vendedor v2 = (Vendedor) o2;

        if (v1.getRenda() > v2.getRenda()) {
            return 1;
        } else if (v1.getRenda() == v2.getRenda()) {
            return 0;
        } else {
            return -1;
        }
    }
}
