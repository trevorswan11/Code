/* CSDS132 Lab 3
 Trevor Swan
 Curtis Li
 Simon Eskin */

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class MaxWindowTest extends JFrame{
    private int originalHeight = 0;
    private boolean heightMaximized = false;
    public static void main(String[] args){
        MaxWindowTest m1 = new MaxWindowTest();
        m1.setVisible(true);
        m1.snapToTop();
        m1.snapToLeft();
        m1.maximizeHeight(false);
        // m1.maximizeWidth();
    }
    // this snaps the window to the top of the screen
    public void snapToTop() {
        setLocation(getX(),0);
    }
    // this snaps the window to the left of the screen
    public void snapToLeft() {
        setLocation(0, getY());
    }
    public void maximizeHeight(boolean x) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        if (x == true && heightMaximized == false) {
            originalHeight = getHeight();
            setLocation(getX(), 0);
            setSize(getWidth(),(int)d.getHeight());
            heightMaximized = true;
        } else if (x == false && heightMaximized == true) {
            setSize(getWidth(), originalHeight);
            heightMaximized = false;
        }
    }
    public void maximizeWidth() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension d = kit.getScreenSize();
        snapToLeft();
        setSize((int)d.getWidth(),getHeight());
    }
}