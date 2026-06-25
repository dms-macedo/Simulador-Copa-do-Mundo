package SimuladorCopaDoMundo.Sistema.Copa;

import SimuladorCopaDoMundo.Sistema.Copa.Classes.Selecao;
import SimuladorCopaDoMundo.Sistema.enums.Continente;
import SimuladorCopaDoMundo.Sistema.exceptions.RegisterSelecaoException;
import SimuladorCopaDoMundo.Sistema.exceptions.SortSelecoesException;

import java.util.Random;

public class CopaDoMundo {
    private String ano;
    private Selecao[] selecoes = new Selecao[48];

    private Selecao[][] grupos = new Selecao[12][4];
    private Selecao[][] jogosFaseDeGrupos = new Selecao[72][2];
    private int[][] placaresFaseDeGrupos = new int[72][2];

    private Selecao[] classificadosPrimeiro = new Selecao[12];
    private Selecao[] classificadosSegundo = new Selecao[12];
    private Selecao[] classificadosRepescagem = new Selecao[8];
    private Selecao[] eliminados = new Selecao[16];

    public CopaDoMundo(String ano) {
        this.ano = ano;
    }

    private boolean isAmerica(Selecao selecao){
        return selecao.getContinente() == Continente.AMERICA_DO_SUL || selecao.getContinente() == Continente.AMERICA_CENTRAL || selecao.getContinente() == Continente.AMERICA_DO_NORTE;
    }

    public void calcularRanking(Selecao[] lista){
        Random random = new Random();

        for (int j = 0; j < lista.length; j++) {
            Selecao selecaoDaVez = lista[j];
            Selecao selecaoVizinha = lista[j + 1];

            boolean isTrocarDeLugar = false;

            if (selecaoDaVez.pontos < selecaoVizinha.pontos){
                isTrocarDeLugar = true;
            } else if (selecaoDaVez.pontos == selecaoVizinha.pontos){
                if (selecaoDaVez.saldoDeGols < selecaoVizinha.saldoDeGols){
                    isTrocarDeLugar = true;
                } else if (selecaoDaVez.saldoDeGols == selecaoVizinha.saldoDeGols){
                    if (selecaoDaVez.golsMarcados < selecaoVizinha.golsMarcados){
                        isTrocarDeLugar = true;
                    } else if (selecaoDaVez.golsMarcados == selecaoVizinha.golsMarcados){
                        if (random.nextBoolean()){
                            isTrocarDeLugar = true;
                        }
                    }
                }
            }

            if (isTrocarDeLugar){
                Selecao selecaoTemp = lista[j];
                lista[j] = lista[j + 1];
                lista[j + 1] = selecaoTemp;
            }
        }
    }

    public void registrarSelecao(Selecao selecao){
        if (selecao == null){
            throw new RegisterSelecaoException("A seleção não pode ser nula.");
        }

        int counterAmerica = 0;
        int counterAsia = 0;
        int counterEuropa = 0;
        int counterAfrica = 0;
        int counterOceania = 0;

        for (Selecao time : selecoes){
            if (time != null){
                if (isAmerica(time)) {
                    counterAmerica++;
                } else if (time.getContinente() == Continente.EUROPA){
                    counterEuropa++;
                } else if (time.getContinente() == Continente.AFRICA){
                    counterAfrica++;
                } else if (time.getContinente() == Continente.OCEANIA){
                    counterOceania++;
                } else if (time.getContinente() == Continente.ASIA){
                    counterAsia++;
                }
            }
        }

        if (counterAmerica == 13 && isAmerica(selecao)){
            throw new RegisterSelecaoException("A quantidade de Seleções da América já estão cheias. (" + counterAmerica + ")");
        } else if (counterEuropa == 16 && selecao.getContinente() == Continente.EUROPA){
            throw new RegisterSelecaoException("A quantidade de Seleções da Europa já estão cheias. (" + counterEuropa + ")");
        } else if (counterAsia == 9 && selecao.getContinente() == Continente.ASIA){
            throw new RegisterSelecaoException("A quantidade de Seleções da Ásia já estão cheias. (" + counterAsia + ")");
        } else if (counterAfrica == 9 && selecao.getContinente() == Continente.AFRICA){
            throw new RegisterSelecaoException("A quantidade de Seleções da África já estão cheias. (" + counterAfrica + ")");
        } else if (counterOceania == 1 && selecao.getContinente() == Continente.OCEANIA){
            throw new RegisterSelecaoException("A quantidade de Seleções da Oceania já estão cheias. (" + counterOceania + ")");
        }

        for (int i = 0; i < this.selecoes.length; i++) {
            if (this.selecoes[i] == null){
                this.selecoes[i] = selecao;
                return;
            }
        }

        throw new RegisterSelecaoException("A copa está cheia.");
    }

    public void registrarSelecoes(Selecao... selecoes){
        for (Selecao selecao : selecoes){
            try{
                this.registrarSelecao(selecao);
                System.out.println("Sucesso! Seleção: " + selecao.getNome() + " registrada.");
            } catch (RegisterSelecaoException e){
                System.out.println("Erro ao registrar '" + selecao.getNome() + "': " +  e.getMessage());
            }
        }
    }

    public void sortearGrupos(){
        if (this.selecoes[this.selecoes.length - 1] == null){
            throw new SortSelecoesException("A Copa está incompleta. È necessário 48 seleções para sortear os grupos.");
        }

        Random random = new Random();

        //Embaralha todas as seleções no array
        for (int i = 0; i < this.selecoes.length; i++) {
            int sort = random.nextInt(this.selecoes.length);
            Selecao selecaoTemp = this.selecoes[i];
            this.selecoes[i] = this.selecoes[sort];
            this.selecoes[sort] = selecaoTemp;
        }

        //Distribui 4 selecoes por grupo após o embaralhamento
        int contador = 0;
        for (int i = 0; i < this.grupos.length; i++) {
            for(int j = 0; j < this.grupos[i].length; j++){
                this.grupos[i][j] = this.selecoes[contador];
                contador++;
            }
        }
    }

    public void simularFaseDeGrupos(){
        for (Selecao[] grupo : this.grupos){
            for (Selecao selecao : grupo){
                if (selecao == null){
                    throw new SortSelecoesException("Não há times suficientes para simular a fase de grupos.");
                }
            }
        }

        Random random = new Random();
        int counterJogos = 0;

        //Cria as Partidas
        for (Selecao[] grupo : this.grupos) {
            for (int j = 0; j < grupo.length; j++) {
                for (int k = j + 1; k < grupo.length; k++) {
                    this.jogosFaseDeGrupos[counterJogos][0] = grupo[j];
                    this.jogosFaseDeGrupos[counterJogos][1] = grupo[k];
                    counterJogos++;
                }
            }
        }

        //Sorteia os Resultados de cada time em cada jogo
        for (int i = 0; i < this.placaresFaseDeGrupos.length; i++) {
            for(int j = 0; j < this.placaresFaseDeGrupos[i].length; j++){
                int pontos = random.nextInt(11);
                this.placaresFaseDeGrupos[i][j] = pontos;
            }
        }

        //Distribui os pontos de cada equipe, saldo de gols e gols marcados
        for (int i = 0; i < this.jogosFaseDeGrupos.length; i++){

            int placarSelecaoEsquerda = this.placaresFaseDeGrupos[i][0];
            int placarSelecaoDireita = this.placaresFaseDeGrupos[i][1];

            Selecao selecaoEsquerdaPartida = this.jogosFaseDeGrupos[i][0];
            Selecao selecaoDireitaPartida = this.jogosFaseDeGrupos[i][1];

            if (placarSelecaoEsquerda > placarSelecaoDireita){
                selecaoEsquerdaPartida.pontos += 3;

                selecaoEsquerdaPartida.saldoDeGols += (placarSelecaoEsquerda - placarSelecaoDireita);
                selecaoDireitaPartida.saldoDeGols += (placarSelecaoDireita - placarSelecaoEsquerda);

                selecaoEsquerdaPartida.golsMarcados += placarSelecaoEsquerda;
                selecaoDireitaPartida.golsMarcados += placarSelecaoDireita;
            } else if (placarSelecaoEsquerda == placarSelecaoDireita){
                selecaoEsquerdaPartida.pontos += 1;
                selecaoDireitaPartida.pontos += 1;

                selecaoEsquerdaPartida.golsMarcados += placarSelecaoEsquerda;
                selecaoDireitaPartida.golsMarcados += placarSelecaoDireita;
            } else {
                selecaoDireitaPartida.pontos += 3;

                selecaoEsquerdaPartida.saldoDeGols += (placarSelecaoEsquerda - placarSelecaoDireita);
                selecaoDireitaPartida.saldoDeGols += (placarSelecaoDireita - placarSelecaoEsquerda);

                selecaoEsquerdaPartida.golsMarcados += placarSelecaoEsquerda;
                selecaoDireitaPartida.golsMarcados += placarSelecaoDireita;
            }
        }

        //Organiza os grupos do 1° ao 4° lugar com primeiro critério no maior números de pontos, segundo critério no maior saldo de gols e terceiro critério na maior quantidade de gols marcados, se todos os critérios estiverem empatados, será feito sorteio para quem fica com a colocação maior (random.nextBoolean())
        for (Selecao[] grupo : this.grupos) {
            for (int j = 0; j < grupo.length; j++) {
                calcularRanking(grupo);
            }
        }

        //Verifica quem se classificou em primeiro, segundo e os 8 melhores terceiros lugares, além dos eliminados
        int counterPrimeiroLugar = 0;
        int counterSegundoLugar = 0;
        int counterEliminados = 0;

        Selecao[] terceirosLista = new Selecao[12];
        int counterTerceiroLugar = 0;

        for (Selecao[] grupo : this.grupos) {
            for (Selecao selecao : grupo) {
                if (selecao == grupo[0]) {
                    this.classificadosPrimeiro[counterPrimeiroLugar] = selecao;
                    counterPrimeiroLugar++;
                } else if (selecao == grupo[1]) {
                    this.classificadosSegundo[counterSegundoLugar] = selecao;
                    counterSegundoLugar++;
                } else if (selecao == grupo[2]) {
                    terceirosLista[counterTerceiroLugar] = selecao;
                    counterTerceiroLugar++;
                } else {
                    this.eliminados[counterEliminados] = selecao;
                    counterEliminados++;
                }
            }
        }

        calcularRanking(terceirosLista);
        int repescagem = 0;
        for (Selecao selecao : terceirosLista) {
            this.classificadosRepescagem[repescagem] = selecao;
            repescagem++;

            if (repescagem == 8) {
                break;
            }
        }
    }


}
