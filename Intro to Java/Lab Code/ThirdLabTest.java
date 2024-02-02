// This is a test file for CSDS132 Lab 3
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ThirdLabTest extends JFrame{
    // Create fields - they go in class body but not methods
    private boolean heightMaximized = false;
    private int originalHeight = 0;
    private boolean widthMaximized = false;
    private int originalWidth = 0;
    private int originalX = 0;
    private int originalY = 0;
    // inititalize a variable to make calling the method easier
    public static void main(String[] args){
        ThirdLabTest m1 = new ThirdLabTest();
        m1.setVisible(true);
        // m1.snapToTop();
        // m1.snapToLeft();
        // m1.maximizeHeight(false);
    }
    // snapping the window to the top of the screen.
    public void snapToTop(){
        setLocation(getX(), 0);
    }
    // snapping the window to the left of the screen
    public void snapToLeft(){
        setLocation(0,getY());
    }
    // maximize the height of the window
    public void maximizeHeight(boolean input){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        if (input == true && heightMaximized == false){
            originalHeight = getHeight();
            setSize(getWidth(),(int)d.getHeight());
            snapToTop();
            heightMaximized = true;
        } else if (input == false && heightMaximized == true) {
            setSize(getWidth(),originalHeight);
            heightMaximized = false;
        }
    }
    // maximize the width of the window
    public void maximizeWidth(boolean input){
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        if (input == true && widthMaximized == false){
            originalWidth = getWidth();
            setSize((int)d.getWidth(),getHeight());
            snapToLeft();
            widthMaximized = true;
        } else if (input == false && widthMaximized == true){

        }
    }
}