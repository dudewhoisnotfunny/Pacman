import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.*;
import java.awt.Color;
import java.util.Random ;

/** Extends SimpleCreature
  * ComplexCreature is the same as SimpleCreature except for its movement and speed 
  * implements the MoveableShaper interface
  */
public class ComplexCreature extends SimpleCreature
{
  /* variables overwrtie the ones in the super class, increasing
   *  the movement range and the speed
   */
  public static final int COMPLEX_CREATURE_MOVEMENT_RANGE = 150;
  private int complexCreatureSpeed = 2;
  private final int COMPLEX_CREATURE_RUNAWAY_ZONE = 100;
  private boolean chasing = false;
  
  /** Construsts a creature object with its top left corner at the specified X, and Y coordinate
    * and a random direction of movement, either up and down or left and right
    * @param x The X coordinate of the top left corner of the creature
    * @param y The Y coordinate of the top left corner of the creature
    */
  public ComplexCreature(int x, int y)
  {
    super(x,y);
  }
  
  /** Overwrites the move methods in SimpleCreature
    * now uses the new speed and movement range variables 
    * so the creature will move faster and farther
    */
  
  public void move()
  {
    
    if(!GameFrame.gameBoard.contains(getBounds()))
    {
      turn180();
    }
    
    
    if(direction.equals("north"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()-complexCreatureSpeed, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, -complexCreatureSpeed);
    }
    
    if(direction.equals("south"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()+complexCreatureSpeed, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, complexCreatureSpeed);
    }
    
    if(direction.equals("west"))
    {
      head = new Ellipse2D.Double(head.getX()-complexCreatureSpeed, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(-complexCreatureSpeed, 0);
    }
    
    if(direction.equals("east"))
    {
      head = new Ellipse2D.Double(head.getX()+complexCreatureSpeed, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(complexCreatureSpeed, 0);
    }
    
    if((Math.abs(head.getY() - yOrgin) == COMPLEX_CREATURE_MOVEMENT_RANGE) || (Math.abs(head.getX() - xOrgin) == COMPLEX_CREATURE_MOVEMENT_RANGE))
    {
      turn180();
    }

  }
  
  /** if the creature is already being chased then it checks to see if the direction of the other creature is still the same as its own and returns true
    * if the other creature's direction changed then it is no longer chasing the creature so false is returned
    * otherwise it checks to see if the other creature is within COMPLEX_CREATURE_RUNAWAY_ZONE distance of this creature
    * @param other the creature to see if it is within range
    * @return True if the creature is being chased or within range, false otherwise
    */
  public boolean withInRange(Creature other)
  {
    if(chasing)
    {
      if(!getDirection().equals(other.getDirection()))
      {
        chasing = false;
        return false;
      }
    }
    return getBounds().union(other.getBounds()).getWidth() <= COMPLEX_CREATURE_RUNAWAY_ZONE && getBounds().union(other.getBounds()).getHeight() <= COMPLEX_CREATURE_RUNAWAY_ZONE ;
  }
  
  /** Moves the creature away from the creatureToRunAwayFrom
    */
  public void runAway(Creature creatureToRunAwayFrom)
  {
    
    chasing = true;
    direction = creatureToRunAwayFrom.getDirection();

    if(!GameFrame.gameBoard.contains(getBounds()))
    {
      turn180();
    }
    if(direction.equals("north"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()-complexCreatureSpeed, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, -complexCreatureSpeed);
    }
    
    if(direction.equals("south"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()+complexCreatureSpeed, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, complexCreatureSpeed);
    }
    
    if(direction.equals("west"))
    {
      head = new Ellipse2D.Double(head.getX()-complexCreatureSpeed, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(-complexCreatureSpeed, 0);
    }
    
    if(direction.equals("east"))
    {
      head = new Ellipse2D.Double(head.getX()+complexCreatureSpeed, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(complexCreatureSpeed, 0);
    }
  }
  
  
  
  /** Overwrites the draw the method in SimpleCreature
    * so the creature is green instead of red
    * @param g2 the graphics component to draw the creature
    */
  public void draw(Graphics2D g2)
  {
    g2.setColor(Color.GREEN);
    g2.fill(head);
    g2.fill(tail);
  }
  
  
}