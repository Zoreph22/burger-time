package logic;

public class Assiettes {

    // Attributs
    private Burger[] burgers;
    private int size;

    // Constructeurs
    public Assiettes(int size) {        
        setSize(size);
        burgers = new Burger[this.size];
        //initBurger();
    }
    public Assiettes(int size, Burger[] burgers){
        setSize(size);
        this.burgers = burgers;
    }

    // Get
    public int getSize() {
        return this.size;
    }    
    public Burger getAssiette(int id) {
        return this.burgers[id];
    }


    // Set 
    public void setSize(int size) {
        this.size = size;
    }

    public void setAssiette(int id, Burger burger) {
        this.burgers[id] = burger;
    }

    // Méthode init 
    public void initBurger() {
        for(int i=0;i<this.size;i++){
            // burgers[i] = new Burger();
        }
    }

    // Print
    public void print() {
        System.out.println("");
        for (int i = 0; i < this.size; i++)
            System.out.print("└───┘");

        System.out.println("");
    }
}
