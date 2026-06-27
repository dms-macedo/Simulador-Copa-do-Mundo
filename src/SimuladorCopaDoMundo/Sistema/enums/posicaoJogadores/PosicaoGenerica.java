package SimuladorCopaDoMundo.Sistema.enums.posicaoJogadores;

public enum PosicaoGenerica {
    ATACANTE("Atacante"),
    MEIO_CAMPISTA("MEIO-CAMPISTA"),
    ZAGUEIRO("ZAGUEIRO"),
    GOLEIRO("GOLEIRO");

    private final String nome;

    PosicaoGenerica(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
