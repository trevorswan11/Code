package CodeMISC;

import javax.swing.*;

public class HelloWorldApp {
	public static void main(String[] args) {
		JFrame frame = new JFrame("Hello World Example");
    	JPanel panel = new JPanel(); // Create a JPanel
        JLabel label = new JLabel("Hello World!");
		System.out.println("hello");
        panel.add(label); // Add the label to the panel
		frame.add(panel); // Add the panel to the frame

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
		System.out.println("test");
        frame.setVisible(true);
    }
}
