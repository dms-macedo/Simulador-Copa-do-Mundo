package SimuladorCopaDoMundo.Sistema.Copa;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.exceptions.SelecoesException;

import java.util.Random;

public class CopaDoMundo {
    Random random = new Random();

    private int ano;
    private Selecao[] selecoes = new Selecao[48];

    public void embaralharSelecoes(Selecao[] lista) {
        int counterSelecaoExists = 0;
        int counterSelecaoNull = 0;

        for (Selecao selecao : lista) {
            if (selecao == null) {
                counterSelecaoNull++;
                continue;
            }

            counterSelecaoExists++;
        }

        if (counterSelecaoNull > 0) {
            throw new SelecoesException("A lista de seleções está incompleta. (" + counterSelecaoExists + "/" + lista.length + ")");
        } else if (counterSelecaoNull < 0) {
            throw new SelecoesException("A lista de seleções não pode ser negativa.");
        }

        for (int i = 0; i < lista.length; i++) {
            int sort = random.nextInt(lista.length);

            Selecao selecaoTemp = lista[i];
            lista[i] = lista[sort];
            lista[sort] = selecaoTemp;
        }
    }
}
