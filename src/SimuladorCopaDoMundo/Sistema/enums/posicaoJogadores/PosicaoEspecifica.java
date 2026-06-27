package SimuladorCopaDoMundo.Sistema.enums.posicaoJogadores;

public enum PosicaoEspecifica {
    //ATACANTE
    CENTRO_AVANTE("Centro Avante", PosicaoGenerica.ATACANTE),
    SEGUNDO_ATACANTE("Segundo Atacante", PosicaoGenerica.ATACANTE),
    PONTA_DIREITA("Ponta Direita", PosicaoGenerica.ATACANTE),
    PONTA_ESQUERDA("Ponta Esquerda", PosicaoGenerica.ATACANTE),

    //MEIAS
    VOLANTE("Volante", PosicaoGenerica.MEIO_CAMPISTA),
    MEIA_ARMADOR("Meia Armador", PosicaoGenerica.MEIO_CAMPISTA),
    MEIA_DIREITA("Meia Direita",  PosicaoGenerica.MEIO_CAMPISTA),
    MEIA_ESQUERDA("Meia Esquerda", PosicaoGenerica.MEIO_CAMPISTA),

    //DEFESA
    ZAGUEIRO("Zagueiro", PosicaoGenerica.ZAGUEIRO),
    LATERAL_DIREITO("Lateral Direito", PosicaoGenerica.ZAGUEIRO),
    LATERAL_ESQUERDO("Lateral Esquerdo", PosicaoGenerica.ZAGUEIRO),
    ALA_DIREITO("Ala Direito", PosicaoGenerica.ZAGUEIRO),
    ALA_ESQUERDO("Ala Esquerdo", PosicaoGenerica.ZAGUEIRO);


    private String nome;
    private PosicaoGenerica posicaoGenerica;
    PosicaoEspecifica(String nome, PosicaoGenerica posicaoGenerica){
        this.nome = nome;
        this.posicaoGenerica = posicaoGenerica;
    }

    public String getNome() {
        return nome;
    }

    public PosicaoGenerica getPosicaoGenerica() {
        return posicaoGenerica;
    }
}
