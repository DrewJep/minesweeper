//Drew Jepsen
//CS110
/** EXTRA CREDIT CONSIDERATION
 I have added the following functionality, I would like evaluated for extra credit:
   ** Throw an exception when the user attempts to uncover a flagged square
   ** Difficulty Select
   ** Game restart Option
   ** True Minesweeper with Recursion
   ** Early Submission
*/
//Driver runs the Minesweeper class 
//The getGrid size is in Driver due to needing Minesweeper to extend Grid for the enum type 
import java.util.Scanner;
public class Driver {
   public static void main(String[] args){
      boolean play=true;
      while(play){
         int[] layout=getGridSize();
         //create the Minesweeper game with the user's selected level
         Minesweeper game=new Minesweeper(layout[0],layout[1],layout[2]);
         //play the game 
         play=game.play();
      }
   }/** getGridSize asks the user for which grid set up they want
   @return list int[] containing the number of rows, columns and mines for the game
   */
   public static int[] getGridSize(){
      //necessary variables
      boolean check=true;
      int[] list=new int[3];
      while (check) {
         //Ask user which setup they want
         Scanner input = new Scanner(System.in);
         System.out.println("Do You wanna play:\n1: 8x8 with 8 mines\n2: 10x12 with 10 mines\n3: 16x20 with 50 mines\n(Enter either 1, 2 or 3)");
         try{
            //parse the option of the user
            int choice=Integer.parseInt(input.nextLine());
            //return which level the user selected
            if (choice==1){
               list=new int[]{8,8,8};
               return list;
            }
            else if (choice==2){ 
               list=new int[]{10,12,10};
               return list;
            }
            else if (choice==3){
               list=new int[]{16,20,50};
               return list;
            }
            //if the user inputs the wrong number then tell them
            else
               System.out.println("Invalid Option");
         //if the user doesn't input a number then tell them
         } catch (NumberFormatException e) {
            System.out.println("Invalid Option");
         }
      }   
      return list;
   }  
}