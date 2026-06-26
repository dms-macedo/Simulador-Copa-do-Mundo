package SimuladorCopaDoMundo.Sistema.Copa.Classes;

import SimuladorCopaDoMundo.Sistema.Copa.CopaDoMundo;
import SimuladorCopaDoMundo.Sistema.enums.Continente;

public class Selecao {
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

    public Selecao(String nome, Jogador[] jogadores, Tecnico tecnico, Continente continente, CopaDoMundo copa) {
        this.nome = nome;
        this.jogadores = jogadores;
        this.tecnico = tecnico;
        this.continente = continente;
        this.copa = copa;
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
