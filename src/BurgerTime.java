import java.util.*;

public class BurgerTime
{
    public static void main(String[] args) 
    {
        Game game = new Game();
       

        Scanner sc = new Scanner(System.in);
        System.out.println("Saisissez un entier : ");
        int i = sc.nextInt();
        System.out.println("Saisissez une chaîne : ");
        //On vide la ligne avant d'en lire une autre
        sc.nextLine();
        String str = sc.nextLine();      
        System.out.println("FIN ! ");
    }

    


    

}