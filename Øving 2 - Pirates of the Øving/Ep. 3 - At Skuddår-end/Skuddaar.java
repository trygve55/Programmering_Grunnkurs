import static javax.swing.JOptionPane.*;

class Skuddaar {
	public static int getInt(String text) {
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

	static public void main(String[] args) {
		int aar = getInt("Skriv inn et årstall:");
		if (aar % 4 == 0  && aar%100 > 0 || aar % 400 == 0) {
			showMessageDialog(null, aar + " er et skuddår.");
		} else {
			showMessageDialog(null, aar + " er ikke et skuddår.");
		}
	}
}