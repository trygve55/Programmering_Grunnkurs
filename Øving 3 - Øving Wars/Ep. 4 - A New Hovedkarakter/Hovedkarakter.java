import static javax.swing.JOptionPane.*;
import static java.lang.Math.*;

class Hovedkarakter {

	static char getKarakter(String s, char cc, char ccc) {
				int c = 0;
				String input;
				do
				{
					input = showInputDialog(s);
					try {
						input = input.toUpperCase();
						c = input.charAt(0);
					} catch (Exception e) {
						showMessageDialog(null, "Skriv et tegn!");
					}
					if (c < cc || c > ccc) {
						showMessageDialog(null, "Er ikke en karakter!");
					}
				} while (c == 0 || c < cc || c > ccc);
				return (char) (6 - (c - 65));
	}

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
		int reply = showConfirmDialog(null, "Regne med studiepoeng?", "", YES_NO_OPTION);
		System.out.print(reply);
		int total = 0, dele = getInt("Hvor mange karakterer er det?", 1, 100);
		if (reply == 1) {
			for (int i = 0; i < dele; i++) {
				total += getKarakter("Skriv inn en karakter", 'A', 'E');
			}
		} else {
				int nysp = 0;
				for (int i = 0; i < dele; i++) {
					dele += nysp = getInt("Hvor mange studiepoeng er det i faget?", 1 , 200);
					total += nysp * getKarakter("Skriv inn en karakter", 'A', 'E');
			}
		}
		showMessageDialog(null, "Gjennomsnitskarakteren er " + (char)(71 - round((double)total/dele)) + ".");


	}
}