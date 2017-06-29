import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/** this interafce is used with shapes to 
  * draw and move them on screen
  * as well as  detect collisions
  */
public interface MoveableShape 
{ 
void move(); 
boolean collides(MoveableShape other); 
void draw(Graphics2D g2); 
Rectangle getBounds();
String getDirection();
} 