package SimuladorCopaDoMundo.Sistema.Copa.Classes;

public class Tecnico extends Pessoa{
    private String esquemaTatico;

    public Tecnico(String nome, int idade, String esquemaTatico) {
        super(nome, idade);
        this.esquemaTatico = esquemaTatico;
    }
}
