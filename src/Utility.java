import java.util.Scanner;

public class Utility implements Runnable
{
    
    private int test;
    private Scanner sc;

    public Utility()
    {
        sc = new Scanner(System.in);
        test = 0;
    }

    public void run()
    {
        test = sc.nextInt();
    }

    public int getInt()
    {
        return this.test;
    }

    






}
