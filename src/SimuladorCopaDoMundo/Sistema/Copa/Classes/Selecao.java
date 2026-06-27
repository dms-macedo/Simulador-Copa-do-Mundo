package SimuladorCopaDoMundo.Sistema.Copa.Classes;

import SimuladorCopaDoMundo.Sistema.Copa.CopaDoMundo;
import SimuladorCopaDoMundo.Sistema.enums.Continente;
import SimuladorCopaDoMundo.Sistema.interfaces.Ranking;
import SimuladorCopaDoMundo.Sistema.interfaces.Verifieds;

public class Selecao implements Verifieds, Ranking {
    private String nome;
    private Jogador[] jogadores = new Jogador[26];
    private Tecnico tecnico;
    private Continente continente;
    private CopaDoMundo copa;
    private int vitorias = 0;
    private int empates = 0;
    private int derrotas = 0;
    protected int pontos;
    protected int saldoDeGols;
    protected int golsMarcados;
    private Jogador[] timeTitular;


    public Selecao(String nome, Tecnico tecnico, Continente continente, CopaDoMundo copa) {
        this.nome = nome;
        this.tecnico = tecnico;
        this.continente = continente;
        this.copa = copa;
    }

    public void registrarJogador(Jogador jogador){

    }

    public void calcularRankingJogadores(){
        nullListVerified(jogadores);

        for(int i = 0; i < jogadores.length; i++){
            for(int k = 0; k < jogadores.length - 1; i++){
                if (jogadores[k].getNivelForca() < jogadores[k + 1].getNivelForca()){
                    replace(jogadores, k, k + 1);
                }
            }
        }
    }

    public String getNome() {
        return nome;
    }

    public Jogador[] getJogadores() {
        return jogadores.clone();
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public Continente getContinente() {
        return continente;
    }

    public int getPontos() {
        return pontos;
    }

    public int getSaldoDeGols() {
        return saldoDeGols;
    }

    public int getGolsMarcados() {
        return golsMarcados;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }

    public void setSaldoDeGols(int saldoDeGols) {
        this.saldoDeGols = saldoDeGols;
    }

    public void setGolsMarcados(int golsMarcados) {
        this.golsMarcados = golsMarcados;
    }

    public int getVitorias() {
        return vitorias;
    }

    public void setVitorias(int vitorias) {
        this.vitorias = vitorias;
    }

    public int getEmpates() {
        return empates;
    }

    public void setEmpates(int empates) {
        this.empates = empates;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }
}
