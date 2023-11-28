package br.gov.cesarschool.poo.bonusvendas.negocio;

import br.gov.cesarschool.poo.bonusvendas.entidade.Vendedor;
import br.gov.cesarschool.poo.bonusvendas.util.Comparador;

public class ComparadorVendedorNome implements Comparador {
    private static ComparadorVendedorNome instance;

    public static ComparadorVendedorNome getInstance() {
        if (instance == null) {
            instance = new ComparadorVendedorNome();
        }
        return instance;
    }


    private ComparadorVendedorNome() {}

    public int comparar(Object o1, Object o2) {
        Vendedor v1 = (Vendedor) o1;
        Vendedor v2 = (Vendedor) o2;

        int compareVal = v1.getNomeCompleto().compareTo(v2.getNomeCompleto());

        if (compareVal > 0) {
            return 1;
        } else if (compareVal == 0) {
            return 0;
        } else {
            return -1;
        }
    }
}
