package logic;

public class Position {

    // Attributs
    private int posx, posy;
    private boolean contact;

    // Constructeurs
    public Position(int posx, int posy) {
        setPosX(posx);
        setPosY(posy);
    }

    // Get
    public int getPosX() {
        return this.posx;
    }

    public int getPosY() {
        return this.posy;
    }

    public boolean getContact() {
        return this.contact;
    }

    // Set
    public void setPosX(int posx) {
        this.posx = posx;
    }

    public void setPosY(int posy) {
        this.posy = posy;
    }

    public void setContact(boolean contact) {
        this.contact = contact;
    }

    public void setPosXY(int x, int y) {
        this.posx = x;
        this.posy = y;
    }

}
