package code;

/** My first Java stand-alone program */
public class MyFirstProgram {

	/**
	 * The method run first by the program
	 * 
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		System.out.println("I am alive");

		// anything you want to do to launch your program goes in this method
		javax.swing.JFrame frame = new javax.swing.JFrame();
		frame.setSize(300, 150);
		frame.setTitle("Hello!");
		frame.setVisible(true);

		// a look at the command line arguments
		System.out.println("The command line arguments are:");
		for (int i = 0; i < args.length; i++)
			System.out.println(args[i]);
	}
}
