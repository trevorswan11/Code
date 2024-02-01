//Names: Simon Eskin, Trevor Swan, Curtis Li
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;


public class MaxWindow extends JFrame {
  private boolean heightMaximized = false;
  private int originalHeight = 0;
  private int originalY = 0;
  //this snaps the window to the left of the screen
  public void snapToLeft(){
    setLocation(0,getY());
  }
  //this sends the window to the top of the screen
  public void snapToTop(){
    setLocation(getX(),0); 
  }
  public void maximizeHeight(boolean x){
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension d = kit.getScreenSize();
    if(x == true&&heightMaximized == false){
      originalHeight = getHeight();
      originalY = getY();
      setLocation(getX(),0);
      setSize(getWidth(),(int)d.getHeight());
      heightMaximized = true;
    }
    else if(x == false&&heightMaximized == true){
      setSize(getWidth(),originalHeight);
      setLocation(getX(),originalY);
      heightMaximized = false;
    }
  }
  public void maximizeWidth(boolean x){
    Toolkit kit = Toolkit.getDefaultToolkit();
    Dimension d = kit.getScreenSize();
    setLocation(0,getY());
    setSize((int)d.getWidth(),getHeight());
  }
}