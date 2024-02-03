// This is a test file for CSDS132 Lab 3
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ThirdLab extends JFrame{
    // Create fields - they go in class body but not methods
    private boolean heightMaximized = false;
    private int originalHeight = 0;
    private boolean widthMaximized = false;
    private int originalWidth = 0;
    private int originalX = 20;
    private int originalY = 20;
    private int screenWidth;
    private int screenHeight;
    
    // inititalize a variable to make calling the method easier
    public static void main(String[] args){
        ThirdLab m1 = new ThirdLab();
        m1.setVisible(true);
        // m1.snapToTop();
        // m1.snapToLeft();
        m1.maximizeHeight(true);
        // m1.maximizeWidth(true);
    }

    // This constructor gathers screen size values for later use
    public ThirdLab(){ // constructors have the same name as the class!
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        screenWidth = (int)d.getWidth();
        screenHeight = (int)d.getHeight();
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
        if (input == true && heightMaximized == false){
            originalHeight = getHeight();
            setSize(getWidth(),screenHeight);
            snapToTop();
            heightMaximized = true;
            // this conditional branch restores the windows original qualities
        } else if (input == false && heightMaximized == true) {
            setSize(getWidth(),originalHeight);
            heightMaximized = false;
            setLocation(originalX,originalY);
        }
    }

    // maximize the width of the window
    public void maximizeWidth(boolean input){
        if (input == true && widthMaximized == false){
            originalWidth = getWidth();
            setSize(screenWidth,getHeight());
            snapToLeft();
            widthMaximized = true;
            // this conditional branch restores the windows original qualities
        } else if (input == false && widthMaximized == true){
            setSize(originalWidth, getHeight());
            widthMaximized = false;
            setLocation(originalX,originalY);
        }
    }
}