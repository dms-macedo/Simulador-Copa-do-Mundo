package SimuladorCopaDoMundo.Sistema.interfaces;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.enums.BubbleOption;

import java.util.Random;

public interface Ranking {
    Random random = new Random();

    default void replace(Object[] lista, int indexA, int indexB){
        Object temp = lista[indexA];
        lista[indexA] = lista[indexB];
        lista[indexB] = temp;
    }

    default void bubbleSort(Object[] lista){
        for (int i = 0; i < lista.length; i++) {
            for(int k = 0; k < lista.length - 1; k++){
                Object temp = lista[k];
                lista[k] = lista[k + 1];
                lista[k + 1] = temp;
            }
        }
    };

    default void sort(Object[] lista, int qtdSort){
        for (int i = 0; i < lista.length; i++) {
            int sort = random.nextInt(qtdSort);

            Object temp = lista[i];
            lista[i] = lista[sort];
            lista[sort] = temp;
        }
    }
}
