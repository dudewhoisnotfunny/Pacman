/** abstract creature class
  */
public abstract class Creature implements MoveableShape
{
  
  protected int xOrgin, yOrgin;//Holds the spawn coordinates of the creature
  
  /** @param x The X coordinate of the creature
    * @param y the Y coordinate of the creature
    */
  public Creature(int x, int y)
  {
    xOrgin = x;
    yOrgin = y;
  }
  
}