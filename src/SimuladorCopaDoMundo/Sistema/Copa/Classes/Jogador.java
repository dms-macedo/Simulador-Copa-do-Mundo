package SimuladorCopaDoMundo.Sistema.Copa.Classes;

import SimuladorCopaDoMundo.Sistema.enums.posicaoJogadores;

public class Jogador extends Pessoa{
    private posicaoJogadores posicao;
    private double altura;
    private int numeroCamisa;


    public Jogador(String nome, int idade, posicaoJogadores posicao, double altura, int numeroCamisa){
        super(nome, idade);
        this.posicao = posicao;
        this.altura = altura;
        this.numeroCamisa = numeroCamisa;
    }
}
