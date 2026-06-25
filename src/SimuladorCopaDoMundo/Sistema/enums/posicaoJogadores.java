package SimuladorCopaDoMundo.Sistema.enums;

public enum posicaoJogadores {
    ATACANTE("Atacante"),
    MEIO_CAMPISTA("Meio Campista"),
    ZAGUEIRO("Zagueiro"),
    LATERAL_DIREITO("Lateral Direito"),
    LATERAL_ESQUERDO("Lateral Esquerdo"),
    GOLEIRO("Goleiro");

    private String nome;

    posicaoJogadores(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
