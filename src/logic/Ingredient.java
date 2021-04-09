package logic;

public class Ingredient extends Thread {

    private String type;
    private int ligne;
    private int colonne;

    public Ingredient(String type) {
        this.type = type;
    }

    public void run() {

    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getColonne() {
        return this.colonne;
    }

    public void setColonne(int colonne) {
        this.colonne = colonne;
    }

    public int getLigne() {
        return this.ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }
}
