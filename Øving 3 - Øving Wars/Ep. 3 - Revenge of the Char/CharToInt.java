import static javax.swing.JOptionPane.*;

class CharToInt {
		 static int getChar(String s) {
			char c = 0;
			String input;
			do
			{
				input = showInputDialog(s);
				try {
					c = input.charAt(0);
				} catch (Exception e) {
					showMessageDialog(null, "Skriv et tegn!");
				}
			} while (c == 0);
			return c;
	}

	static void printValue(int i, int ii) {
		while (i <= ii) {
			System.out.println("tegnet " + (char) i + " har verdien " + i);
			i++;
		}
	}

	public static void main(String [] args) {
		int start = getChar("Start tall:");
		int stop = getChar("Stop tall:");
		printValue(start, stop);
	}
}