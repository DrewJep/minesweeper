//Drew Jepsen
//CS110
//Minesweeper runs the game buy using all the parts of the grid methods 
//this extends grid just to be able to use the enum type Status
import java.util.Scanner;
public class Minesweeper extends Grid{
   /** Minesweeper passes the width, height and numMines to the grid constuctor
   @param w int variable width to be passed to grid constuctor
   @param h int variable height to be passed to grid constuctor
   @param numM int variable numMines to be passed to grid constuctor
   */
   public Minesweeper(int w, int h, int numM){
      //create the grid and fill it with Square objects
      super(w,h,numM);
   }/** play runs the game
   @return true or false based on if the user wants to play again
   */
   public boolean play(){
      //run starts the while loop of the game
      Status run=Status.OK;
      while (run==Status.OK){
         try {
            String[] split=getMove();
            //if the user enterd q,then end the loop and game by calling gameStatuts
            if (split[0].toUpperCase().equals("Q"))
               return gameStatus(Status.MINE);
            //if the user entered u then call uncoverSquare and pass in the rest of the user's input
            else if (split[0].toUpperCase().equals("U"))
               run=super.uncoverSquare(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
            //if the user entered f then call flagSquare and pass in the rest of the user's input
            else if (split[0].toUpperCase().equals("F"))
               super.flagSquare(Integer.parseInt(split[1]),Integer.parseInt(split[2]));
            else
               System.out.println("Invalid Input");
         //if the user's input is incorrect then tell the user and restart the loop
         } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
         } catch (ArrayIndexOutOfBoundsException e) {
             System.out.println("A given position is out of the range");
         }
      }
      //call gameStatus after the loop is over
      return gameStatus(run); 
   }/** getMove asks the user for this round's input
   @return String[] containing the user's input split by the spaces
   */
   public String[] getMove(){
      //ask user for input
      Scanner input = new Scanner(System.in);
      System.out.println(super.toString());
      System.out.println("Options: (U)ncover r c, (F)lag r c, (Q)uit ");
      String move=input.nextLine();
      //split the user's input into an array
      String [] split=move.split("\\s+");
      return split;
   }/** gameStatus takes in the emun type Status based on the condition on the game and prints a response based on it
   @param result Status enum type based on outcome of the game
   @return a boolean depending on if the user wants to keep playing 
   */
   public boolean gameStatus(Status result){
      //if result is set to Status.MINE then expose the mines and tell the user that they lost
      if (result==Status.MINE){
         System.out.println("LOSER");
         super.exposeMines();
      }
      //if run is set to Status.WIN then inform the user that they won
      else if (result==Status.WIN){
         System.out.println("WINNER");
      }
      //print the final grid
      System.out.println(super.toString());
      //ask the user if they want to play again
      Scanner input2 = new Scanner(System.in);
      System.out.println("Do You want to play again (Y/N)?");
      String user=input2.nextLine();
      if (user.toUpperCase().equals("Y"))
         return true;
      else 
         return false;  
   } 
}