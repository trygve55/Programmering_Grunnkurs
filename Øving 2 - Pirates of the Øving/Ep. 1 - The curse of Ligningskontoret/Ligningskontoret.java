import static javax.swing.JOptionPane.*;

class Ligningskontoret	{
	static public void main(String[] args) {
		boolean exit = false;
		String input = "";
		int dag = 0, kontor = 0;
		while (exit == false) {
			input = showInputDialog("Hvilken dag i måneden er du født?");
			exit = true;
			try {
				dag = Integer.parseInt(input);
			} catch(NumberFormatException e) {
				exit = false;
				showMessageDialog(null, "Skriv et tall som er mellom 1 og 31");
			}
			if (exit == true) {
				if (dag <= 8 && dag >= 1) {
					kontor = 113;
				} else if (dag <= 14 && dag >= 9) {
					kontor = 120;
				} else if (dag <= 25 && dag >= 15) {
					kontor = 125;
				} else if (dag <= 31 && dag >= 26) {
					kontor = 134;
				} else {
					exit = false;
					showMessageDialog(null, "Skriv et tall som er mellom 1 og 31");
				}
				if (exit == true) {
					showMessageDialog(null, "Du blir referert til kontor " + kontor + ".");
				}
			}
		}
	}
}