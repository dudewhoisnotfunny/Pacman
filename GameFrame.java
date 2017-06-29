import javax.swing.JFrame ;
import javax.swing.JButton ;
import javax.swing.JPanel ;
import java.awt.event.ActionListener ;
import java.awt.event.ActionEvent ;
import java.awt.BorderLayout ;
import java.awt.Rectangle;

/** GameFrame to hold the GamePanel
  */
public class GameFrame extends JFrame
{
  public static final int FRAME_WIDTH  = 1000;
  public static final int FRAME_HEIGHT = 640;
  public static Rectangle gameBoard;
  /**
   Constructs the frame
   */
  public GameFrame()
  {
    createGamePanel() ;
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    gameBoard = new Rectangle(GameFrame.FRAME_WIDTH, GameFrame.FRAME_HEIGHT);
  }

  /** creates and adds a GamePanel to GameFrame
    */
  public void createGamePanel()
  {
    JPanel panel = new GamePanel() ;
    add(panel, BorderLayout.CENTER) ;
  }
}