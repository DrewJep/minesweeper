//Drew Jepsen
//CS110
//The game grid, holding Square objects
//import random for coord generation
import java.util.Random;
public class Grid{
   /**
   Conditions returned after uncovering squares
   */
   enum Status{
      OK,WIN,MINE
   }
   //Instance Variables
   private Square[][] grid;
   private int width;
   private int height;
   private int numMines;
   private int numSquaresUncovered;
   /** Grid Constructor 
   @param h variable int height
   @param w variable int width
   @param m variable int numMines
   */
   public Grid(int h, int w, int m){
      height=h;
      width=w;
      numMines=m;
      grid=new Square[h][w];
      //create the mine spaces first
      int[][] mineCoords=new int[numMines][2]; 
      for (int i=0;i<numMines;i++){
         Random rn = new Random();
         mineCoords[i][0]=rn.nextInt(height);
         mineCoords[i][1]=rn.nextInt(width);
      }
      //if there are duplicates then it will roll a new random coord
      //There is a chance that duplicates can exist but statisitcally there won't
      for(int i=0;i<numMines-1;i++)
         for(int j=i+1;j<numMines;j++)
            if(mineCoords[i][0]==mineCoords[j][0]&&mineCoords[i][1]==mineCoords[j][1]){
               Random rn = new Random(1);
               mineCoords[i][0]=rn.nextInt(height);
               mineCoords[i][1]=rn.nextInt(width);
            }
      //go through the grid and make the mines squares
      for (int i=0;i<height;i++)
         for (int j=0;j<width;j++)
            for (int k=0;k<numMines;k++)
               if (i==mineCoords[k][0]&&j==mineCoords[k][1])
                  grid[i][j]=new MineSquare();   
      //make any squares that are not mine into NumberSquares         
      for (int i=0;i<height;i++)
         for (int j=0;j<width;j++)
            if (grid[i][j]==null)
               grid[i][j]=new NumberSquare(getNeighbors(i,j),i,j);
                  
   }
   /** getNeighbors is only called by the constructor
   @param r position in the rows
   @param c position in the columns
   @return total number of Mines around that 
   */
   public int getNeighbors(int r, int c){
      int totalMines=0;
      //iterates through the adjacent squares to the parameters
      for(int i=r-1;i<=r+1;i++)
         //if the adjacent square is outside the range, then ingore them
         if(i>=0 && i<height)
            for(int j=c-1;j<=c+1;j++)
               if(j>=0 && j<width)
                  if (grid[i][j]!=null)
                     //if that adjacent square is a mine then add 1 to the totalMines
                     if (grid[i][j].isMine())
                        totalMines++;
      return totalMines;
   }
   /** uncoverSquare uses recursion to uncover the passed in position and all non mines around it
   @param r position in the row
   @param c position in the column
   @return enumerated type Status: either OK, WIN or MINE based on the end of the call
   */
   public Status uncoverSquare(int r, int c){
      try{
         //if the given position is flagged, then throw an exception
         if (grid[r][c].isFlagged())
            throw new FlagUncoveredException("Can not uncover a flagged square");
         //if that space is a Mine then return Status.MINE
         if (grid[r][c].isMine())
            return Status.MINE;
         NumberSquare numSquare = (NumberSquare)grid[r][c];
         
         //if the space was already uncovered then return OK and end the call
         if (numSquare.isUncovered())
            return Status.OK;
         //Base Cases 
         //if the square being uncovered's neighborMines>0 then this branch of recursion is ended
         //and it checks if the user has won by uncovering all non mine squares
         if (numSquare.getNeighborMines()>0){
            grid[r][c].uncover();
            numSquaresUncovered++;
            if (numSquaresUncovered==(width*height)-numMines)
               return Status.WIN;
            else
               return Status.OK; 
         }  
         grid[r][c].uncover();
         numSquaresUncovered++;
         //recursion, call uncoverSquare on all adjacent squares
         for(int i=r-1;i<=r+1;i++)
         if(i>=0 && i<height)
            for(int j=c-1;j<=c+1;j++)
               if(j>=0 && j<width)
                  uncoverSquare(i,j);
         //after all the recursion is complete then it checks if the user has convered all mines and won
         if (numSquaresUncovered==(width*height)-numMines)
            return Status.WIN;
         else
            return Status.OK;
      //catches for if the user inputs a value out of the range or if they try to uncover a flagged square  
      } catch (IndexOutOfBoundsException e){
         System.out.println("A given position is out of the range");
         return Status.OK;
      } catch (FlagUncoveredException e){
         System.out.println("You must unflag a flagged square before uncovering it");
         return Status.OK;
      }     
   }
   /** exposeMines is only ever called if the game is over
   goes through the entire grid and it will uncover all mines 
   */
   public void exposeMines(){
      for (int i=0;i<height;i++)
         for(int j=0;j<width;j++)
            if (grid[i][j].isMine())
               grid[i][j].uncover();
   }
   /** flagSquare flagges the passed in square
   @param r position in the row
   @param c postionn in the column
   */
   public void flagSquare(int r, int c){
      //if the square is not uncovered then flag it 
      if(!(grid[r][c].isUncovered())){
         grid[r][c].flagSquare();
         //after flagging the square then if flagged is true set the element to f or set it to x
         if (grid[r][c].isFlagged())
            grid[r][c].setElement("f");
         else 
            grid[r][c].setElement("x");
      }
   }
   /** toString returns a String for of the entire grid with the rows and columns numbered from 0 to either width-1 or height-1 with proper padding
   @return s String containing the all the Squares and their current elements
   */
   public String toString(){
      String s="  ";
      for (int i=0;i<width;i++)
         s+=String.format("%-3d",i);
      s+="\n";
      for(int i = 0; i<height; i++){
         s+=String.format("%-2d",i);
         for(int j = 0; j<width; j++){
            s+=String.format("%-3s",grid[i][j]);
         }
         s+="\n";
      }
      return s; 
   }
}