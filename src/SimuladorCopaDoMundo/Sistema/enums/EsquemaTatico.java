package SimuladorCopaDoMundo.Sistema.enums;

public enum EsquemaTatico {
    QUATRO_TRES_TRES("4-3-3", 4 ,3, 3, 1),
    QUATRO_QUATRO_DOIS("4-4-2", 4, 4, 2, 1),
    CINCO_TRES_DOIS("5-3-2", 5, 3, 2, 1),
    TRES_CINCO_DOIS("3-5-2", 3, 5, 2, 1),
    QUATRO_CINCO_UM("4-5-1", 4, 5, 1, 1),
    TRES_QUATRO_TRES("3-4-3", 3, 4, 3, 1);

    private final String nome;
    private int qtdZagueiros = 0;
    private int qtdMeias = 0;
    private int qtdAtacantes = 0;

    EsquemaTatico(String nome, int qtdZagueiros, int qtdMeias, int qtdAtacantes, int qtdGoleiro){
        this.nome = nome;
        this.qtdZagueiros = qtdZagueiros;
        this.qtdMeias = qtdMeias;
        this.qtdAtacantes = qtdAtacantes;
    }

    public String getNome() {
        return nome;
    }

    public int getQtdZagueiros() {
        return qtdZagueiros;
    }

    public int getQtdMeias() {
        return qtdMeias;
    }

    public int getQtdAtacantes() {
        return qtdAtacantes;
    }
}
