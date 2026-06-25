package SimuladorCopaDoMundo.Sistema.Copa.Classes;

import SimuladorCopaDoMundo.Sistema.Copa.CopaDoMundo;
import SimuladorCopaDoMundo.Sistema.enums.Continente;

public class Selecao {
    private String nome;
    private Jogador[] jogadores = new Jogador[26];
    private Tecnico[] tecnico;
    private Continente continente;
    private CopaDoMundo copa;
    protected int pontos;
    protected int saldoDeGols;
    protected int golsMarcados;

    public Selecao(String nome, Jogador[] jogadores, Tecnico[] tecnico, Continente continente, CopaDoMundo copa) {
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
        return jogadores;
    }

    public Tecnico[] getTecnico() {
        return tecnico;
    }

    public Continente getContinente() {
        return continente;
    }

    public int getPontos() {
        return pontos;
    }
}
