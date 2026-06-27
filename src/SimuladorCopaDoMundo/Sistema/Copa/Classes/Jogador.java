package SimuladorCopaDoMundo.Sistema.Copa.Classes;

import SimuladorCopaDoMundo.Sistema.enums.posicaoJogadores.PosicaoGenerica;

public class Jogador extends Pessoa{
    private PosicaoGenerica posicao;
    private double altura;
    private int numeroCamisa;
    private int gols;
    private int assistencias;
    private int nivelForca = 0;

    public Jogador(String nome, int idade, PosicaoGenerica posicao, double altura, int numeroCamisa){
        super(nome, idade);
        this.posicao = posicao;
        this.altura = altura;
        this.numeroCamisa = numeroCamisa;
    }

    public PosicaoGenerica getPosicao() {
        return posicao;
    }

    public double getAltura() {
        return altura;
    }

    public int getNumeroCamisa() {
        return numeroCamisa;
    }

    public int getGols() {
        return gols;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public int getNivelForca() {
        return nivelForca;
    }
}
