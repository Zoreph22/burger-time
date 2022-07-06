package logic;

public class Position {

    // Attributs
    private int posi, posj;

    // Constructeurs
    public Position(int posi, int posj) {
        setPosi(posi);
        setPosj(posj);
    }

    // Get
    public int getPosi() {
        return this.posi;
    }

    public int getPosj() {
        return this.posj;
    }

    // Set
    public void setPosi(int posi) {
        this.posi = posi;
    }

    public void setPosj(int posj) {
        this.posj = posj;
    }

    public void setPosij(int i, int j) {
        this.posi = i;
        this.posj = j;
    }

}
