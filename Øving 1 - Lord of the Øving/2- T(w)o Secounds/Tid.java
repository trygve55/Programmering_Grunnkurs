import static javax.swing.JOptionPane.*;

class Tid {
	static public void main(String[] args) {
		String input = "";
		int hours;
		int minutes = 0;
		int seconds = 0;
		boolean exit = false;
		boolean exit2 = false;
		while (exit == false) {
			while (exit2 == false) {
				input = showInputDialog("Timer: ");
				exit2 = true;
				try {
					hours = Integer.parseInt(input);
				} catch(NumberFormatException e) {
					showMessageDialog(null, "Skriv et gyldig tall din idiot!");
					exit2 = false;
				}
			}
			exit2 = false;
			if (hours < 0) {
				showMessageDialog(null, "Skriv et positivt tall!");
			}
			else {
				exit = true;
			}
		}
		exit = false;
		while (exit == false) {
			while (exit2 == false) {
				input = showInputDialog("Minutter: ");
				exit2 = true;
				try {
					minutes = Integer.parseInt(input);
				} catch(NumberFormatException e) {
					showMessageDialog(null, "Skriv et gyldig tall din idiot!");
					exit2 = false;
				}
			}
			exit2 = false;
			if (minutes < 0) {
				showMessageDialog(null, "Skriv et positivt tall!");
			}
			else {
				exit = true;
			}
		}
		exit = false;
				while (exit == false) {
			while (exit2 == false) {
				input = showInputDialog("Sekunder: ");
				exit2 = true;
				try {
					seconds = Integer.parseInt(input);
				} catch(NumberFormatException e) {
					showMessageDialog(null, "Skriv et gyldig tall din idiot!");
					exit2 = false;
				}
			}
			exit2 = false;
			if (seconds  < 0) {
				showMessageDialog(null, "Skriv et positivt tall!");
			}
			else {
				exit = true;
			}
		}
		int totalSeconds = hours*3600 + minutes*60 + seconds;
		showMessageDialog(null, "Total tid i sekunder: " + totalSeconds);
	}
}