package br.gov.cesarschool.poo.bonusvendas.util;

public class Ordenadora {
    private Ordenadora(){}

    public static void ordenar(Object[] lista, Comparador comp) {
        for (int i = 0; i < lista.length - 1; i++) {
            for (int j = i+1; j < lista.length; j++) {
                if (comp.comparar(lista[i], lista[j]) > 0) {
                    Object temp = lista[i];
                    lista[i] = lista[j];
                    lista[j] = temp;
                }
            }
        }
    }
}
