import static javax.swing.JOptionPane.*;
import java.lang.Math.*;

class PrimTall {

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

	public static void main(String[] args) {
		int dele = 2, tall = getInt("Skriv et tall som du vil skjekke om er et primtall.", 1, 999999999);
		double svar = 0, svar2 = 0;
		boolean noExit = true;
		while (noExit) {
			svar = (double) tall/dele;
			svar2 = Math.round(svar);
			System.out.print(tall + " / " + dele + " = " + svar + " ?= " + svar2 + " " + (boolean)(svar == svar2));
			if (svar == svar2) {
				showMessageDialog(null, "Ikke primtall");
				break;
			}
			if (dele > tall/2) {
				showMessageDialog(null, "Primtall");
				dele = 2;
				tall = getInt("Skriv et tall som du vil skjekke om er et primtall. (0 for å avslutte)", 0, 999999999);
				if (tall == 0) {
					noExit = false;
				}
			}
			dele++;
		}
	}
}
