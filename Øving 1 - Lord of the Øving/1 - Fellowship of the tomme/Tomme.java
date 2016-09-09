import static javax.swing.JOptionPane.*;

class Tomme {
	static public void main(String[] args) {
		double ratio = 2.54;
		String input = "";
		double tommer = 0;
		boolean exit = false;
		boolean exit2 = false;
		
		while (exit == false) {
			while (exit2 == false) {
				input = showInputDialog("Antall tommer: ");
				exit2 = true;
				try {
					tommer = Double.parseDouble(input);
				} catch(NumberFormatException e) {
					showMessageDialog(null, "Skriv et gyldig tall din idiot!");
					exit2 = false;
				}
			}
			if (tommer < 0) {
				showMessageDialog(null, "Skriv et positivt tall!(bruk punktum istedenfor komma på decimaler)");
				exit2 = false;
			}
			else {
				exit = true;
			}
		}
		double cm = tommer*ratio;
		showMessageDialog(null, tommer + " er det samme som " + cm + " cm.");
	}
}