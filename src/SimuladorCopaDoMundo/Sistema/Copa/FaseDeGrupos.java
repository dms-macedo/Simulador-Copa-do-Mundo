package SimuladorCopaDoMundo.Sistema.Copa;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.exceptions.SelecoesException;
import SimuladorCopaDoMundo.Sistema.interfaces.Ranking;
import SimuladorCopaDoMundo.Sistema.interfaces.Verifieds;

import java.util.Random;

public class FaseDeGrupos implements Verifieds, Ranking {
    private final Random random = new Random();
    
    private final Selecao[][] grupos = new Selecao[12][4];

    private final Selecao[][] partidasFaseDeGrupos = new Selecao[72][2];
    private final int[][] placaresFaseDeGrupos = new int[72][2];

    private final Selecao[] classificadosPrimeiroLugar = new Selecao[12];
    private final Selecao[] classificadosSegundoLugar = new Selecao[12];
    private final Selecao[] classificadosRepescagem = new Selecao[8];
    private final Selecao[] eliminados = new Selecao[16];

    //Flags:
    private boolean isSelecoesDistribuidas = false;
    private boolean isPartidasCriadas = false;
    private boolean isResultadosSimulados = false;
    private boolean isPtsSaldoGolsVEDistribuidos = false;

    private void calcularRanking(Selecao[] lista){
        nullListVerified(lista);

        for (int i = 0; i < lista.length; i++) {
            for(int k = 0; k < lista.length - 1; k++){
                Selecao selecaoDaVez = lista[k];
                Selecao selecaoVizinha = lista[k + 1];

                boolean isTrocarDeLugar = false;
                if (selecaoDaVez.getPontos() < selecaoVizinha.getPontos()){
                    isTrocarDeLugar = true;
                } else if (selecaoDaVez.getPontos() == selecaoVizinha.getPontos()){
                    if (selecaoDaVez.getSaldoDeGols() < selecaoVizinha.getSaldoDeGols()){
                        isTrocarDeLugar = true;
                    } else if (selecaoDaVez.getSaldoDeGols() == selecaoVizinha.getSaldoDeGols()){
                        if (selecaoDaVez.getGolsMarcados() < selecaoVizinha.getGolsMarcados()){
                            isTrocarDeLugar = true;
                        } else if (selecaoDaVez.getGolsMarcados() == selecaoVizinha.getGolsMarcados()){
                            if (random.nextBoolean()){
                                isTrocarDeLugar = true;
                            }
                        }
                    }
                }

                if (isTrocarDeLugar){
                    bubbleSort(lista);
                }
            }
        }
    }

    protected void distribuirSelecoesPorGrupo(Selecao[] lista){
        nullListVerified(lista);

        int counterSelecao = 0;

        for (int i = 0; i < this.grupos.length; i++) {
            for (int j = 0; j < this.grupos[i].length; j++) {
                this.grupos[i][j] = lista[counterSelecao];
                counterSelecao++;
            }
        }

        this.isSelecoesDistribuidas = true;
    }

    protected void criarPartidas(){
        if (this.isSelecoesDistribuidas){
            Selecao[][] grupos = this.grupos;
            nullListBiDirecionalVerified(grupos);

            int counterJogos = 0;
            for (int i = 0; i <grupos.length; i++) {
                for(int j = 0; j < grupos[i].length; j++){
                    for(int k = j + 1; k < grupos[i].length; k++){
                        this.partidasFaseDeGrupos[counterJogos][0] = this.grupos[i][j];
                        this.partidasFaseDeGrupos[counterJogos][1] = this.grupos[i][k];
                        counterJogos++;
                    }
                }
            }

            this.isPartidasCriadas = true;
            return;
        }

        throw new SelecoesException("É necessário distribuir as seleções por grupo primeiro.");
    }

    protected void simularResultados(){
        if (isPartidasCriadas){
            for (int i = 0; i < this.placaresFaseDeGrupos.length; i++) {
                int ResultadoSelecaoEsq = random.nextInt(11);
                int ResultadoSelecaoDir = random.nextInt(11);

                this.placaresFaseDeGrupos[i][0] = ResultadoSelecaoEsq;
                this.placaresFaseDeGrupos[i][1] = ResultadoSelecaoDir;
            }

            isResultadosSimulados = true;
            return;
        }

        throw new SelecoesException("É necessário criar as partidas primeiro.");
    }

    protected void distribuirPontosSaldoGolsVED(){
        if (isResultadosSimulados){
            for (int i = 0; i < this.placaresFaseDeGrupos.length; i++) {
                int ResultadoSelecaoEsq = this.placaresFaseDeGrupos[i][0];
                int ResultadoSelecaoDir = this.placaresFaseDeGrupos[i][1];

                Selecao selecaoEsq = this.partidasFaseDeGrupos[i][0];
                Selecao selecaoDir = this.partidasFaseDeGrupos[i][1];

                selecaoEsq.setSaldoDeGols(selecaoEsq.getSaldoDeGols() + (ResultadoSelecaoEsq - ResultadoSelecaoDir));
                selecaoDir.setSaldoDeGols(selecaoDir.getSaldoDeGols() + (ResultadoSelecaoDir - ResultadoSelecaoEsq));

                selecaoEsq.setGolsMarcados(selecaoEsq.getGolsMarcados() + ResultadoSelecaoEsq);
                selecaoDir.setGolsMarcados(selecaoDir.getGolsMarcados() + ResultadoSelecaoDir);

                if (ResultadoSelecaoEsq > ResultadoSelecaoDir){
                    selecaoEsq.setPontos(selecaoEsq.getPontos() + 3);
                    selecaoEsq.setVitorias(selecaoEsq.getVitorias() + 1);
                    selecaoDir.setDerrotas(selecaoDir.getDerrotas() + 1);
                } else if (ResultadoSelecaoEsq == ResultadoSelecaoDir){
                    selecaoEsq.setPontos(selecaoEsq.getPontos() + 1);
                    selecaoDir.setPontos(selecaoDir.getPontos() + 1);

                    selecaoEsq.setEmpates(selecaoEsq.getEmpates() + 1);
                    selecaoDir.setEmpates(selecaoDir.getEmpates() + 1);
                } else {
                    selecaoDir.setPontos(selecaoEsq.getPontos() + 3);
                    selecaoDir.setVitorias(selecaoEsq.getVitorias() + 1);
                    selecaoEsq.setDerrotas(selecaoDir.getDerrotas() + 1);
                }
            }

            isPtsSaldoGolsVEDistribuidos = true;
            return;
        }

        throw new SelecoesException("É necessário simular os resultados das partidas primeiro.");
    }

    private Selecao[] calcularRepescagem(){
        Selecao[] listaRepescagem = new Selecao[12];
        int counterListaRepescagem = 0;

        for (Selecao[] grupo : this.grupos) {
            listaRepescagem[counterListaRepescagem] = grupo[2];
            counterListaRepescagem++;
        }

        calcularRanking(listaRepescagem);
        return listaRepescagem;
    }

    protected void distribuirResultadosFinaisGrupos(){
        for(Selecao[] grupo : this.grupos){
            calcularRanking(grupo);
        }

        if(isPtsSaldoGolsVEDistribuidos){
            int counterPrimeiroLugar = 0;
            int counterSegundoLugar = 0;
            int counterEliminados = 0;

            for (Selecao[] grupo : this.grupos) {
                for (int j = 0; j < grupo.length; j++) {
                    this.classificadosPrimeiroLugar[counterPrimeiroLugar++] = grupo[0];
                    this.classificadosSegundoLugar[counterSegundoLugar++] = grupo[1];
                    this.eliminados[counterEliminados] = grupo[3];
                }
            }

            Selecao[] repescagem = calcularRepescagem();

            for (int i = 0; i < 8; i++){
                this.classificadosRepescagem[i] = repescagem[i];
            }

            for (int i = 8; i < 12; i++){
                this.eliminados[i] = repescagem[i];
            }

            return;
        }

        throw new SelecoesException("É necessário distribuir pontos, saldo de gols, gols marcados, vitórias, derrotas e empates primeiro.");
    }

    public Selecao[][] getGrupos() {
        return grupos;
    }

    public Selecao[][] getPartidasFaseDeGrupos() {
        return partidasFaseDeGrupos;
    }

    public int[][] getPlacaresFaseDeGrupos() {
        return placaresFaseDeGrupos;
    }

    public Selecao[] getClassificadosPrimeiroLugar() {
        return classificadosPrimeiroLugar;
    }

    public Selecao[] getClassificadosSegundoLugar() {
        return classificadosSegundoLugar;
    }

    public Selecao[] getClassificadosRepescagem() {
        return classificadosRepescagem;
    }

    public Selecao[] getEliminados() {
        return eliminados;
    }
}
