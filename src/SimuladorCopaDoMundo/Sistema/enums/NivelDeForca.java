package SimuladorCopaDoMundo.Sistema.enums;

public enum NivelDeForca {
    UM(1),
    DOIS(2),
    TRES(3);

    private int nivel;

    NivelDeForca(int nivel){
        this.nivel = nivel;
    }

    public int getNivel() {
        return nivel;
    }
}
