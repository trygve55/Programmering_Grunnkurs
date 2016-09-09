import static javax.swing.JOptionPane.*;

class Tid2 {
	static public void main(String[] args) {
		String input;
		int totalSeconds = 0;
		boolean exit = false;
		boolean exit2 = false;
		while (exit == false) {
			while (exit2 == false) {
				input = showInputDialog("Totale Sekunder: ");
				exit2 = true;
				try {
					totalSeconds = Integer.parseInt(input);
				} catch(NumberFormatException e) {
					showMessageDialog(null, "Skriv et gyldig tall din idiot!");
					exit2 = false;
				}
			}
			if (totalSeconds < 0) {
				showMessageDialog(null, "Skriv et positivt tall!");
				exit2 = false;
			}
			else {
				exit = true;
			}
		}
		int seconds = totalSeconds % 60;
		int minutes = (totalSeconds % 3600 - totalSeconds % 60)/60;
		int hours = (totalSeconds - totalSeconds % 3600)/3600;
		showMessageDialog(null, "Tiden: " + hours + " timer, " + minutes + " minutter og " + seconds + " sekunder.");
	}
}