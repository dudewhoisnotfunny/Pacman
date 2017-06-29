import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import java.awt.Polygon;
import java.awt.geom.*;
import java.awt.Color;
import java.util.Random ;

/** SimpeCreature extends the Creature Class
  * Models a  Creature from the pacman game
  * Its movement is autonomous
  * implements the MoveableShaper interface
  */
public class SimpleCreature extends Creature 
{
  
  //Shape variables that model the body
  protected Ellipse2D.Double head;
  protected Polygon tail;
  
  //size of the creature
  public static final int CREATURE_SIZE = 25;
  
  //how far the creature will move away from its spawn
  public static final int CREATURE_MOVEMENT_RANGE = 50;
  
  //speed at which it wil move
  protected final int CREATURE_SPEED = 1;
  
  //Holds the current direction of the creatures movement
  protected String direction;
  
  /** Construsts a creature object with its top left corner at the specified X, and Y coordinate
    * and a random direction of movement, either up and down or left and right
    * @param x The X coordinate of the top left corner of the creature
    * @param y The Y coordinate of the top left corner of the creature
    */
  public SimpleCreature(int x, int y)
  {
    
    super(x, y);
    
    head = new Ellipse2D.Double(x, y, CREATURE_SIZE, CREATURE_SIZE);
    int[] tailXcoordinates = {x, x, (int)(x+(0.25*CREATURE_SIZE)), (int)(x+(0.5*CREATURE_SIZE)), (int)(x+(0.75*CREATURE_SIZE)), x+CREATURE_SIZE, x+CREATURE_SIZE};
    int[] tailYcoordinates = {y+(CREATURE_SIZE/2), (int)(y+(1.5*CREATURE_SIZE)), y+CREATURE_SIZE, (int)(y+(1.5*CREATURE_SIZE)), y+CREATURE_SIZE, (int)(y+(1.5*CREATURE_SIZE)), y+(CREATURE_SIZE/2)};
    tail = new Polygon(tailXcoordinates, tailYcoordinates, 7);
    
    Random random = new Random() ;
    if(random.nextInt(2) == 0)
    {
      direction = "north";
    }else{
      direction = "west";
    }
  }
  
  
  /** Returns the bounding rectangle of the creature
    * @returns A rectangle object that bounds the creature
    */
  public Rectangle getBounds()
  {
    Rectangle2D headBounds = (Rectangle2D)head.getBounds();
    Rectangle2D tailBounds = (Rectangle)tail.getBounds();
    Rectangle bounds = (Rectangle)headBounds.createUnion(tailBounds);
    return bounds;
  }
  
  
  
  /** moves the creature either up and down or left and right
    * once the creature's distance from its spawn is CREATURE_MOVEMENT_RANGE
    * it will turn 180 and begin moving in that direction
    */
  public void move()
  {
    if(direction.equals("north"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()-CREATURE_SPEED, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, -CREATURE_SPEED);
    }
    
    if(direction.equals("south"))
    {
      head = new Ellipse2D.Double(head.getX(), head.getY()+CREATURE_SPEED, CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(0, CREATURE_SPEED);
    }
    
    if(direction.equals("west"))
    {
      head = new Ellipse2D.Double(head.getX()-CREATURE_SPEED, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(-CREATURE_SPEED, 0);
    }
    
    if(direction.equals("east"))
    {
      head = new Ellipse2D.Double(head.getX()+CREATURE_SPEED, head.getY(), CREATURE_SIZE, CREATURE_SIZE);
      tail.translate(CREATURE_SPEED, 0);
    }
    
    if((Math.abs(head.getY() - yOrgin) == CREATURE_MOVEMENT_RANGE) || (Math.abs(head.getX() - xOrgin) == CREATURE_MOVEMENT_RANGE))
    {
      turn180();
    }  
  }
  
  /** returns the current direction of travel
    * @return A string repersenting direction (north, south, east, west)
    */
  public String getDirection()
  {
    return direction;
  }
  
  /** sets the direction variable to whatever direction is 180 degrees
    * from its current direction
    * noth - south
    * east - west
    * south - north
    * west - east
    */
  public void turn180()
  {
    switch (direction){
      case "north" : direction = "south";
      break;
      
      case "east" : direction = "west";
      break;
      
      case "south" : direction = "north";
      break;
      
      case "west" : direction = "east";
      break;
    }
  }
  
  
  /** Uses the getBounds method to determine if this creature is in contact with another creature
    * @return True if the creature is in contact with another Moveable Shape false otherwise
    */
  public boolean collides(MoveableShape other)
  {
    if(this.getBounds().intersects(other.getBounds()))
    {
      return true;
    }
    
    return false;
  }
  
  /** Draws the creature to the screen
    * @param g2 the graphics component to draw the creature
    */
  public void draw(Graphics2D g2)
  {
    g2.setColor(Color.RED);
    g2.fill(head);
    g2.fill(tail);
  }
  
  
}