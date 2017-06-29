import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.geom.*;
import java.awt.Dimension ;
import java.awt.Color;
import java.awt.Label;
import java.util.ArrayList ;
import java.util.Random ;
import java.awt.event.MouseListener ;
import java.awt.event.MouseAdapter ;
import java.awt.event.MouseEvent ;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import javax.swing.*;

/** GamePanel holds
  * -all of the creatures including the predator
  * -the timer label
  */
public class GamePanel extends JPanel
{  
 
  ArrayList<MoveableShape> creatures ;
  final int NUMBER_OF_SIMPLE_CREATURES = 10 ;
  final int NUMBER_OF_COMPLEX_CREATURES = 10 ;
  Predator predator;
  Label playTime = new Label("--:--");
  long elapsedTime, startTime, elapsedSeconds, elapsedMinutes, secondsDisplay;
  String gameTimeElapsed;
  final Timer timer;
  Random random = new Random() ;
  
  /** constructs a GamePanel with NUMBER_OF_SIMPLE_CREATURES SimpleCreatures spawned randomly
    * NUMBER_OF_COMPLEX_CREATURES COMPLEXCreatures alsospawned randomly
    * a Predator Creature spawned in the middle
    * and a label that shows how long the user has been playing
    * The random number generator makes sure the creatures are spawned far away
    * enough from the edge of the screen so they do not move off the screen in the course
    * of their movements
    */
  public GamePanel()
  {

    
    creatures = new ArrayList<MoveableShape>() ;
    for (int i = 0 ; i < NUMBER_OF_SIMPLE_CREATURES ; i ++) {
      int xCreature = SimpleCreature.CREATURE_MOVEMENT_RANGE + random.nextInt(GameFrame.FRAME_WIDTH - 2*SimpleCreature.CREATURE_MOVEMENT_RANGE - SimpleCreature.CREATURE_SIZE) ;
      int yCreature = SimpleCreature.CREATURE_MOVEMENT_RANGE + random.nextInt(GameFrame.FRAME_HEIGHT - 2*SimpleCreature.CREATURE_MOVEMENT_RANGE - SimpleCreature.CREATURE_SIZE) ;
      MoveableShape creature = new SimpleCreature(xCreature, yCreature) ;
      creatures.add(creature);
    }
    for (int i = 0 ; i < NUMBER_OF_COMPLEX_CREATURES ; i ++) {
      int xCreature = ComplexCreature.COMPLEX_CREATURE_MOVEMENT_RANGE + random.nextInt(GameFrame.FRAME_WIDTH - 2*ComplexCreature.COMPLEX_CREATURE_MOVEMENT_RANGE - ComplexCreature.CREATURE_SIZE) ;
      int yCreature = ComplexCreature.COMPLEX_CREATURE_MOVEMENT_RANGE + random.nextInt(GameFrame.FRAME_HEIGHT - 2*ComplexCreature.COMPLEX_CREATURE_MOVEMENT_RANGE - ComplexCreature.CREATURE_SIZE) ;
      MoveableShape creature = new ComplexCreature(xCreature, yCreature) ;
      creatures.add(creature);
    }
    
    
    predator = new Predator(GameFrame.FRAME_WIDTH/2, GameFrame.FRAME_HEIGHT/2);
    
    add(playTime);
    
    
    /** timer listener object
      * every tick the following actions are preformed
      * time label is updated to show how long the user has been playing
      * all the creatures in the creatures arraylist are moved
      * the predator is moved
      * any collisions are detected between the predator and the creatures
      * if any collisions are detected the creature that collided with the predator is removed
      * if there are no creatures left the game is over and the program exits
      */
    class TimerListener implements ActionListener
    {
      public void actionPerformed(ActionEvent event)
      {
        elapsedTime = System.currentTimeMillis() - startTime;
        elapsedSeconds = elapsedTime / 1000;
        secondsDisplay = elapsedSeconds % 60;
        elapsedMinutes = elapsedSeconds / 60;
        gameTimeElapsed = String.format("%02d:%02d", elapsedMinutes, secondsDisplay);
        playTime.setText(gameTimeElapsed);
        
        for (int i = 0 ; i < creatures.size() ; i ++) {
          
          if(creatures.get(i) instanceof ComplexCreature)
          {
            
            if(((ComplexCreature)creatures.get(i)).withInRange(predator))
            {
              ((ComplexCreature)creatures.get(i)).runAway(predator);
            }else{
              creatures.get(i).move();
            }
            
          }else{
            creatures.get(i).move();
          }
          
        }
        
        predator.move();
        
        for (int i = 0 ; i < creatures.size() ; i ++) {
          if(predator.collides(creatures.get(i)))
          {
            creatures.remove(i);
          }
        }
        
        repaint();
        
        if(creatures.size() == 0)
        {
          timer.stop();
          JOptionPane.showMessageDialog(null, "You beat the game in " + gameTimeElapsed, "Winner", JOptionPane.PLAIN_MESSAGE);
          System.exit(0);
        }
        
      }
    }
    
    // Create ActionListener object for the Timer
    ActionListener timerListener = new TimerListener();
    final int DELAY = 10; // Milliseconds between timer ticks
    // Create Timer and attach timer listener object
    timer = new Timer(DELAY, timerListener);

    /** Listener class for the mouse
      * if the left mouse button is pressed the predator will turn left
      * if the right mouse button is pressed the predator will turn right
      */
    class MyListener extends MouseAdapter
    {
      public void mousePressed(MouseEvent event)
      {
        if(event.getButton() == 1)
        {
          predator.turnLeft();
        }
        
        if(event.getButton() == 3)
        {
          predator.turnRight();
        }
      }
    }
    
    //addMouseListener to this panel
    addMouseListener(new MyListener()) ;
    
    JOptionPane.showMessageDialog(null, "Press OK to start", "Pacman", JOptionPane.PLAIN_MESSAGE);
    startTime = System.currentTimeMillis();
    timer.start();
    
  } 
  
  /** paintComponent method draws the predator and creatures on screen
    * @param g the graphics component to use to draw all the creatures
    */
  public void paintComponent(Graphics g)
  {  
    super.paintComponent(g) ;
    Graphics2D g2 = (Graphics2D) g;
    
    predator.draw(g2);
    
    for (int i = 0; i < creatures.size(); i++)
    {
      creatures.get(i).draw(g2);
    }

    
  }
}
