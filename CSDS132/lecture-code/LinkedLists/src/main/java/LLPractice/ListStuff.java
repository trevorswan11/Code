package llpractice;

import javax.swing.JFrame;

/** Examples of using lists and generics */
public class ListStuff {

    /**
     * Display and set the size of all the frames in a list of JFrames
     * 
     * @param list a linked list of JFrames
     */
    public static void displayFrames(LinkedList<? extends JFrame> list) {
        for (JFrame frame : list) {
            frame.setSize(300, 150);
            frame.setVisible(true);
        }
    }
}