import javax.swing.JFrame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JComponent;
import java.awt.geom.*;
import java.awt.Color;

/** @author Ramandeep Johal
  * A Simple pacman like game where the predotor is controlled by the player
  * the predaotor is controlled by the left and right mouse buttons
  * left button - turn left
  * right button - turn right
  * if the predator comes in contact with the prey it will devour it 
  * 2 types of prey
  * simple prey move up and down, or left and right at a slow speed
  * complex prey will move in the same pattern as the simple prey but at an increased speed
  * the complex prey will also evade the predator if it comes too close, the only way to devour
  * complex prey is to chase them to the end of the window.
  */

public class Pacman
{
    public static void main(String[] args)
    {
 JFrame frame = new GameFrame();
 frame.setTitle("Pacman");
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 frame.setVisible(true) ;
    }
} 
