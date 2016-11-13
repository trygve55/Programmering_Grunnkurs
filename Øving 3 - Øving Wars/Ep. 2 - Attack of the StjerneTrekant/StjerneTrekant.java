import static javax.swing.JOptionPane.*;

class StjerneTrekant {

	 static int getInt(String s, int ii, int iii) {    //ii = min iii = max
	 			int i = 0;
	 			String input;
	 			do
	 			{
	 				input = showInputDialog(s);
	 				try {
	 					i = Integer.parseInt(input);
	 				} catch (NumberFormatException e) {
	 					showMessageDialog(null, "Skriv et heltall!");
	 				}
	 				if (i < ii) {
	 					showMessageDialog(null, "Skriv et tall som er større en " + (ii - 1) + "!");
	 				}
	 				if (i > iii) {
	 								showMessageDialog(null, "Skriv et tall som er mindre en " + (iii + 1) + "!");
	 				}
	 			} while (i == 0 || i < ii || i > iii );
	 			return i;
	}

	static void drawS(int i) {
		for (int ii = 1; ii <= i; ii++) {
			for (int iii = 1; iii <= ii; iii++) {
				System.out.print("* ");
			}
			System.out.print("\n");
		}
	}

	static void drawT(int i) {
		for (int ii = 1; ii <= i; ii++) {
			for (int iii = 1; iii <= i - ii; iii++) {
				System.out.print(" ");
			}
			for (int iii = 1; iii <= ii; iii++) {
				System.out.print("* ");
			}
			System.out.print("\n");
		}
	}

	public static void main(String[] args) {
		int lines = getInt("Hvor mange linje skal det være?", 3, 80);
		//drawS(lines);
		drawT(lines);
	}
}