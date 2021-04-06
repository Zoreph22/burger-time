public class Entities extends Thread 
{

    private String type;
    private int posX;
    private int posY;

    public Entities(String type, int x, int y) 
    {
        setType(type);
        setPosXY(x, y);
    }

    public run()
    {

    }

    private void setType(String type) {this.type = type;}

    public void setPosX(int X) {this.posX = X;}
    public void setPosY(int Y) {this.posY = Y;}
    public void setPosXY(int X, int Y) {this.posX = X; this.posY = Y;}

    public int getPosX() {return this.posX;}
    public int getPosY() {return this.posY;}


}
