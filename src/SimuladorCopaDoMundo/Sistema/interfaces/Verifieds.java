package SimuladorCopaDoMundo.Sistema.interfaces;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Jogador;
import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.exceptions.SelecoesException;

public interface Verifieds {
    default void nullListVerified(Selecao[] lista){
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
    };

    default void nullListVerified(Jogador[] lista){
        int counterJogadorExists = 0;
        int counterJogadorNull = 0;

        for (Jogador jogador : lista) {
            if (jogador == null) {
                counterJogadorNull++;
                continue;
            }

            counterJogadorExists++;
        }

        if (counterJogadorNull > 0) {
            throw new SelecoesException("A lista de seleções está incompleta. (" + counterJogadorExists + "/" + lista.length + ")");
        } else if (counterJogadorNull < 0) {
            throw new SelecoesException("A lista de seleções não pode ser negativa.");
        }
    }

    default void nullListBiDirecionalVerified(Selecao[][] grupos){
        for (Selecao[] grupo : grupos) {
            nullListVerified(grupo);
        }
    };

}
