package logic;

public class Ingredient extends Thread {

    // Attributs
    private String type;
    private Position pos1;
    private Position pos2;
    private Position pos3;

    // Constructeurs
    public Ingredient(String type, int y, int x2) {
        this.setType(type);
        initPositions(y, x2);
    }

    // Méthode Run
    public void run() {

    }

    // Init
    public void initPositions(int y, int x2) {
        pos1.setPosXY(x2 - 1, y);
        pos2.setPosXY(x2, y);
        pos3.setPosXY(x2 + 1, y);
    }

    // Get
    public String getType() {
        return this.type;
    }

    public Position getPos1() {
        return this.pos1;
    }

    public Position getPos2() {
        return this.pos2;
    }

    public Position getPos3() {
        return this.pos3;
    }

    // Set
    public void setType(String type) {
        this.type = type;
    }

    // toString
    public String toString() {
        if (type.equals("P1") || type.equals("P2"))
            return "PPP";
        else if (type.equals("S"))
            return "SSS";
        else if (type.equals("V"))
            return "VVV";
        else
            return null;
    }

}
