//Drew Jepsen
//CS110
//represents a Numbered square extending squares
public class NumberSquare extends Square {
   //Instance Variables
   private int neighborMines;
   private int myRow;
   private int myCol;
   /** NumberSquare Constructor 
   @param n int variable neighborMines
   @param r int variable myRow
   @param c int variable myCol
   */
   public NumberSquare(int n, int r, int c){
      super();
      neighborMines=n;
      myRow=r;
      myCol=c;
   }/** uncover revels the square if it is not flagged
   @return true if uncover was successful
   @return false if uncover failed
   */
   public boolean uncover(){
      if (!(super.isFlagged())){
         super.setUncovered();
         if (neighborMines==0)
            setElement("_");
         else
            setElement(String.valueOf(neighborMines));
         return true;
      } 
      else 
         return false;
   }/** getNeighborMines returns neighborMines variable
   @return neighborMines variable
   */
   public int getNeighborMines(){
      return neighborMines;
   }/** isMine always returns false because a NumberSquare is always not a mine
   @return false
   */
   public boolean isMine(){
      return false;
   } 
}