package llpractice;

import javax.swing.JFrame;

/* Create a class that adds new features to JFrame.
* Add geometry-like features.
*/
public class GeoFrame extends JFrame {
    /*
     * create a method that "transposes" the window flipping the width to
     * height and height to width. (It returns nothing.)
     */
    public void transpose() {
        this.setSize(this.getHeight(), this.getWidth());
    }

    /* scale the size of the window by a factor */
    public void scale(double factor) {
        this.setSize((int) (factor * this.getWidth()),
                (int) (factor * this.getHeight()));
    }

    /*
     * compare a window to this window and returns true if the windows
     * have the same area and return false if they do not
     */
    public boolean sameAreaAs(JFrame frame) {
        int thisArea = this.getWidth() * this.getHeight();
        int otherArea = frame.getWidth() * frame.getHeight();
        return otherArea == thisArea;
    }

    // the original title of the window
    private String originalTitle = "";
    // remember if the size is on the title
    private boolean sizeOnTitle = false;

    /*
     * a method that places the dimensions of the window on to the
     * window title. It should postfix the desired title with the
     * dimensions
     * The input indicates whether or not we put the size on the title
     */
    public void addSizeToTitle(boolean addSize) {
        if (addSize)
            super.setTitle(originalTitle + " (" + this.getWidth() + "x" +
                    this.getHeight() + ")");
        else
            super.setTitle(originalTitle);
        sizeOnTitle = addSize;
    }

    /* Override setTitle to remember the original title */
    @Override
    public void setTitle(String title) {
        this.originalTitle = title;
        this.addSizeToTitle(sizeOnTitle);
    }

    /*
     * Override setSize to adjust the title when the size changes (by the method
     * call)
     */
    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.addSizeToTitle(sizeOnTitle);
    }

    public static void main(String[] args) {
        GeoFrame g = new GeoFrame();
        g.setVisible(true);
        g.setSize(600,600);
        g.setTitle("Trevor");
        g.addSizeToTitle(true);
    }
}
