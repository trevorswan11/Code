/* This class represents a GeometricFrame which is 
 * an advanced version of JFrame
 */
import javax.swing.JFrame;

public class GeometricFrame extends JFrame{
    public static void main(String[] args){
        GeometricFrame g = new GeometricFrame();
        g.setVisible(true);
        g.setSize(200,700);
        g.transpose();
    }
    // this method transposes a window's dimensions
    public void transpose(){
        // you can use this.__() but it is not necessary. Java does it automatically
        int height = this.getHeight();
        int width = getWidth();
        setSize(height,width);
    }
}