//Drew Jepsen
//CS110
//MineSquare extends Square and represents a mine in the game 
public class MineSquare extends Square {
   /** uncover sets the square's element to * and uncoveres it if the squaer is not flagged
   @return true if uncover was successful
   @return false if uncover failed
   */
   public boolean uncover(){
      if (!(super.isFlagged())){
         super.setUncovered();
         super.setElement("*");
         return true;
      }
      else
         return false;
   }/** isMine only returns true as this is a mine
   @return true 
   */
   public boolean isMine(){
      return true;
   }
}