package functionalPrograms;

/** My first Java stand-alone program */
public class MyFirstProgram {

	/**
	 * This method manually calculates the integer power of a double
	 * 
	 * @param base The base of the expression. A double value
	 * @param exp  The integer exponent to use
	 * @return The result of the caluclation as a double
	 */
	public static double power(double base, int exp) {
		double product = 1;
		// If base is 0 and exp is not, return 1
		if (base == 0 && exp != 0) {
			return 1;
		} else if (base == 0 && exp == 0) {
			return Integer.MAX_VALUE;
		}

		// Check exp against 0 and declare default case
		switch (exp) {
			case 0:
				return 1;
			default:
				if (exp < 0) {
					for (int i = 1; i <= -exp; i++) {
						product = product / base;
					}
				}

				// Otherwise calculate the normal expression
				else {
					for (int i = 1; i <= exp; i++) {
						product = product * base;
					}
				}
				return product;
		}
	}

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
		System.out.println(power(2, -3));
	}
}

