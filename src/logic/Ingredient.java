package logic;

public class Ingredient extends Thread {

    private String type;
    private int posX;
    private int posY;
    private boolean pos1isOk;
    private boolean pos2isOk;
    private boolean pos3isOk;
    private boolean isFall;

    public Ingredient(String type, int y) 
    {
        initIngredient(type, y);
        

    }

    public void run() 
    {
        
    }

    public void initIngredient(String type, int y)
    {
        setType(type);
        if(getType().equals("P1"))
            setPosXY(y+1, 0);
        else if(getType().equals("S"))
            setPosXY(y+1, 1);
        else if(getType().equals("V"))
            setPosXY(y+1, 2);
        else if(getType().equals("P2"))
            setPosXY(y+1, 3);
    }

    public boolean getPos1IsOk() {return this.pos1isOk;}
    public boolean getPos2IsOk() {return this.pos2isOk;}
    public boolean getPos3IsOk() {return this.pos3isOk;}

    public void setPos1IsOk(boolean tmp) {this.pos1isOk = tmp;}
    public void setPos2IsOk(boolean tmp) {this.pos2isOk = tmp;}
    public void setPos3IsOk(boolean tmp) {this.pos3isOk = tmp;}

    public int getPosY() {return this.posY;}
    public int getPosX() {return this.posX;}

    public void setPosY(int tmp) {this.posY = tmp;}
    public void setPosX(int tmp) {this.posX = tmp;}

    public void setPosXY(int x, int y)
    {
        this.posX = x;
        this.posY = y;
    }

    public String getType() {return this.type;}

    public void setType(String type) {this.type = type;}

    public boolean getIsFall() {return this.isFall;}

    public void setIsFall(boolean isFall) {this.isFall = isFall;}

    public String toString()
    {
        if(type.equals("P1") || type.equals("P2"))
            return "PPP";
        else if(type.equals("S"))
            return "SSS";
        else if(type.equals("V"))
            return "VVV";
        else
            return null;
    }


}
