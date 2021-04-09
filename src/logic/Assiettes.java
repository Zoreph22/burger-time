package logic;

public class Assiettes 
{

    private Burger[] burgers;
    private int taille;

    public Assiettes(int taille) 
    {
        initAssiettes(taille);
    }

    public void setTaille(int taille) {this.taille = taille;}
    public int getTaille() {return this.taille;}

    public Burger getAssiette(int id) {return this.burgers[id];}

    public void setAssiette(int id, Burger burger) {this.burgers[id] = burger;}

    public void initAssiettes(int taille) 
    {
        setTaille(taille);
        burgers = new Burger[this.taille];
        for(int i = 0; i < taille; i++)
            burgers[i] = new Burger(3*i+i+1);
    }

    public void print() 
    {
        System.out.println("");
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < taille; j++)
                if(burgers[j].getIngredient(i).getIsFall())
                    System.out.print(" " + burgers[j].getIngredient(i).toString() + " ");
            
            System.out.print("\n");
        }
        for (int i = 0; i < this.taille; i++)
            System.out.print("└───┘");
    }

}
