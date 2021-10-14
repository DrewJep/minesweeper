//Drew Jepsen
//CS110
//Exception made for Minesweeper if the user tries to uncover a flagged square
public class FlagUncoveredException extends Exception
{
   /** FlagUncoveredException takes in a string and passes it to the Exception class
   @param s String Explaination of what caused the error
   */
   public FlagUncoveredException(String s) 
   {
      super(s);
   }  
}