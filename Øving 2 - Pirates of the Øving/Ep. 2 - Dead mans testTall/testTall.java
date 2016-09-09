import static javax.swing.JOptionPane.*;

class testTall {
	public static int intIn(String text) {
		int inputInt = 0;
		String input = "";
		boolean exit = false;
		while (exit == false ) {
			input = showInputDialog(text);
			exit = true;
			try {
				inputInt = Integer.parseInt(input);
			} catch (NumberFormatException e) {
			exit = false;
			showMessageDialog(null, "Skriv et gyldig heltall.");
			}
		}
	return inputInt;
	}

	public static void main(String[] args) {
		String input = "";
		//int uGrense = 5000, nGrense = -5000, deleTall = 3, inputInt = 0;
		int inputInt = intIn("Tall"), deleTall = intIn("Dele tall"),uGrense = intIn("Øvre grense"), nGrense = intIn("Nedre grense");
		boolean exit = false;
		while (true /*exit == false */) {
			//inputInt = intIn("");
			//er positivt
			if (inputInt > 0) {
				showMessageDialog(null, "Tallet er positivt.");
			} else if (inputInt < 0) {
				showMessageDialog(null, "Tallet er negativt.");
			} else {
				showMessageDialog(null, "Tallet er null.");
			}

			//er delelig
			if (inputInt/deleTall == (double) inputInt/deleTall) {
				showMessageDialog(null, inputInt + " er delelig på " + deleTall + ".");
			} else {
				showMessageDialog(null, inputInt + " er ikke delelig på " + deleTall + ".");
			}
			//innenfor intervall
			if (inputInt >= uGrense || inputInt <= nGrense) {
				showMessageDialog(null, inputInt + " er ikke i intervalet mellom " + uGrense + " og " + nGrense + ".");
			} else {
				showMessageDialog(null, inputInt + " er mellom " + uGrense + " og " + nGrense + ".");
			}

		}
	}
}