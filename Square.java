//Drew Jepsen
//CS110
//the base representation for a Square, containing its element and if its flagged or not and if its uncovered or not
//the class is abstract 
abstract class Square {
   //Instance Variables 
   private String element;
   private boolean flagged;
   private boolean uncovered;
   /** Square default Constructor
   element set to x
   flagged set to false
   uncovered set to false
   */
   public Square(){
      element="x";
      flagged=false;
      uncovered=false;
   }/** Square Constructor
   @param e set to String variable element
   @param f set to boolean variable flagged
   @param u set to boolean variable uncovered
   */
   public Square(String e, boolean f, boolean u){
      element=e;
      flagged=f;
      uncovered=u;
   }/** isFlagged returns flagged boolean
   @return flagged boolean variable
   */
   public boolean isFlagged(){
      return flagged;
   }/** isUncovered returns uncovered boolean
   @return uncovered boolean variable
   */
   public boolean isUncovered(){
      return uncovered;
   }/** flagSquare swaps the boolean of a flagged square
   */
   public void flagSquare(){
      if (flagged)
         flagged=false;
      else
         flagged=true;
   }/** setUncovered sets uncovered to true
   */
   public void setUncovered(){
      uncovered=true;
   }/** setElement sets element to the passed in parameter 
   @param s new string element variable
   */
   public void setElement(String s){
      element=s;
   }/** toString returns the element of the square
   @return String containing element variable
   */
   public String toString(){
      return String.format("%s ",element);
   }
   /**uncover and isMine are abstract 
   @return a boolean 
   */
   public abstract boolean uncover();
   public abstract boolean isMine();

}