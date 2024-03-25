package InterfaceChallenge;


import javax.swing.JFrame;

/* A window that has nice geometric features */
public class GeometricFrame extends JFrame implements Friendly {
  
  /* Flips the height and the width of the window */
  public void transpose() {
    this.setSize(this.getHeight(), this.getWidth());
  }
  
  /* Scale the size of the window by a scaling factor */
  public void scale(double factor) {
    this.setSize((int)(this.getWidth() * factor), (int)(this.getHeight() * factor));
  }
  
  /* Determine if this window has the same area as the input window */
  public boolean sameAreaAs(JFrame frame) {
    return (frame.getWidth() * frame.getHeight()) == (this.getWidth() * this.getHeight());
  }
  
  // a field to remember the original title
  private String originalTitle = "";
  
  // remember if the size is on the title
  private boolean sizeIsOnTitle = false;
  
  /* Place the size on the window title */
  public void addSizeToTitle(boolean showSize) {
    if (showSize)
      super.setTitle(this.originalTitle + " " + this.getWidth() + " x " + this.getHeight());
    else
      super.setTitle(this.originalTitle);
    this.sizeIsOnTitle = showSize;
  }
  
  /* Change how the inherited setTitle method works:
   *   - save the title in the originalTitle field
   *   - change the title on the window
   */
  public void setTitle(String title) {
    this.originalTitle = title;
    this.addSizeToTitle(this.sizeIsOnTitle);
  }
  
  /* Change how the inherited setSize method works to first set the size and then update the title */
  public void setSize(int width, int height) {
    super.setSize(width, height);
    this.addSizeToTitle(this.sizeIsOnTitle);
  }
  
  /* A nice greeting */
  public String greeting() {
    return "Hello, I am a " + getWidth() + " by " + getHeight() + " window!";
  }
}