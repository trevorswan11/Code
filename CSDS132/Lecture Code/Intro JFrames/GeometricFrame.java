/* This class represents a GeometricFrame which is 
 * an advanced version of JFrame
 */
import javax.swing.JFrame;

public class GeometricFrame extends JFrame{
    private String originalTitle;
    private boolean isSizeOnTitle;
    public static void main(String[] args){
        // Necessary code to test properly
        GeometricFrame g = new GeometricFrame();
        GeometricFrame h = new GeometricFrame();
        g.setVisible(true);
        g.setSize(200,700);
        // h.setVisible(true);
        // h.setSize(100, 400);

        // Method tests can be found below
        // g.transpose();
        // g.scale(1.2);
        // g.isSameArea(h);
        g.addSizeToTitle(true);
        
    }
    // this method transposes a window's dimensions
    public void transpose(){
        // you can use this.__() but it is not necessary. Java does it automatically
        int height = this.getHeight();
        int width = getWidth();
        setSize(height,width);
    }

    // This method scales the window size
    public void scale(Double size) {
        int width = (int)(this.getWidth() * size);
        int height = (int)(this.getHeight() * size);
        this.setSize(width, height);
    }

    /* Compare a window to this window and returns true if the windows have
     * the same area and return false if they do not */
    public boolean isSameArea(GeometricFrame myFrame) {
        int thisArea = this.getWidth() * this.getHeight();
        int myArea = myFrame.getWidth() * myFrame.getHeight();
        
        if (thisArea == myArea) {
            return true;
        }
        else { 
            return false;
        }
    }

    /* This method adds the size to the frame title */
    public void addSizeToTitle(boolean showSize) {
        if (showSize) {
            super.setTitle(this.getTitle() + "("+this.getWidth()+"x"+this.getHeight()+")");
            this.isSizeOnTitle = true;
        }
        else {
            super.setTitle(this.originalTitle);
            this.setTitle(this.getTitle());
        }
    }

    /* this overrides the setTitle method from the parent */
    public void setTitle(String title) {
        super.setTitle(title);
        this.originalTitle = title;
    }
}