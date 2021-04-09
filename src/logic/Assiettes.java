package logic;

public class Assiettes {

    private Burger[] assiettes;
    private int taille;

    public Assiettes(int taille) {
        this.taille = taille;
        initAssiettes();
    }

    public Burger getAssiette(int id) {
        return this.assiettes[id];
    }

    public void setAssiette(int id, Burger burger) {
        this.assiettes[id] = burger;
    }

    public void initAssiettes() {
        assiettes = new Burger[this.taille];

    }

    public void print() {
        System.out.println("");
        for (int i = 0; i < this.taille; i++)
            System.out.print("└───┘");
    }

}
