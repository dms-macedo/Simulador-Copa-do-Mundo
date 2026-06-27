package SimuladorCopaDoMundo.Sistema.Copa;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.enums.Continente;
import SimuladorCopaDoMundo.Sistema.exceptions.RegisterSelecaoException;
import SimuladorCopaDoMundo.Sistema.exceptions.SelecoesException;
import SimuladorCopaDoMundo.Sistema.interfaces.Ranking;
import SimuladorCopaDoMundo.Sistema.interfaces.Verifieds;

import java.util.Random;

public class CopaDoMundo implements Verifieds, Ranking {
    private int ano;
    private Selecao[] selecoes = new Selecao[48];

    //Flags:
    boolean isSelecoesEmbaralhadas = false;

    private boolean isAmerica(Selecao selecao){
        return selecao.getContinente() == Continente.AMERICA_DO_SUL || selecao.getContinente() == Continente.AMERICA_CENTRAL || selecao.getContinente() == Continente.AMERICA_DO_NORTE;
    }

    private boolean isContinente(Selecao selecao, Continente continente) {
        return selecao.getContinente() == continente;
    }

    public void registrarSelecao(Selecao selecao){
        if (selecao == null){
            throw new RegisterSelecaoException("A seleção não pode ser nula.");
        }

        int counterAmerica = 0;
        int counterEuropa = 0;
        int counterAfrica = 0;
        int counterAsia = 0;
        int counterOceania  = 0;

        for (Selecao time : selecoes){
            if (time != null){
                if (isAmerica(time)){
                    counterAmerica++;
                } else if (isContinente(time, Continente.EUROPA)){
                    counterEuropa++;
                } else if (isContinente(time, Continente.ASIA)){
                    counterAsia++;
                } else if (isContinente(time, Continente.AFRICA)){
                    counterAfrica++;
                } else if (isContinente(time, Continente.OCEANIA)){
                    counterOceania++;
                }
            }
        }

        if (counterAmerica == 10 && isAmerica(selecao)){
            throw new RegisterSelecaoException("O limite de quantidade das seleções Americanas já foi atingida. (" + counterAmerica + ")");
        } else if (counterEuropa == 16 && isContinente(selecao, Continente.EUROPA)){
            throw new RegisterSelecaoException("O limite de quantidade das seleções Europeias já foi atingida. (" + counterEuropa + ")");
        } else if (counterAfrica == 10 && isContinente(selecao, Continente.AFRICA)){
            throw new RegisterSelecaoException("O limite de quantidade das seleções Africanas já foi atingida. (" + counterAfrica + ")");
        } else if (counterAsia == 9 && isContinente(selecao, Continente.ASIA)){
            throw new RegisterSelecaoException("O limite de quantidade das seleções Africanas já foi atingida. (" + counterAsia + ")");
        } else if (counterOceania == 1 && isContinente(selecao, Continente.OCEANIA)){
            throw new RegisterSelecaoException("O limite de quantidade das seleções Oceânicas já foi atingida. (" + counterOceania + ")");
        }

        for (Selecao time : selecoes){
            if(time == null){
                time = selecao;
                return;
            }
        }

        throw new RegisterSelecaoException("A Copa do Mundo está cheia.");
    }

    public void registrarSelecoes(Selecao[] selecoes){
        for (Selecao selecao : selecoes){
            try{
                registrarSelecao(selecao);
                System.out.println("SUCESSO! Seleção: " + selecao.getNome() + " registrada.");
            } catch (RegisterSelecaoException e){
                System.out.println("ERRO: " + e.getMessage());
            }
        }
    }

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

        sort(lista, lista.length);

        isSelecoesEmbaralhadas = true;
    }

    public void simularFaseDeGrupos(FaseDeGrupos faseDeGrupos) {
        if (!isSelecoesEmbaralhadas){
            throw new SelecoesException("É necessário embaralhar as seleções primeiro.");
        }

        faseDeGrupos.distribuirSelecoesPorGrupo(this.selecoes);
        faseDeGrupos.criarPartidas();
        faseDeGrupos.simularResultados();
        faseDeGrupos.distribuirPontosSaldoGolsVED();
        faseDeGrupos.distribuirResultadosFinaisGrupos();
    }

    public Selecao[] getSelecoes() {
        return selecoes;
    }
}
