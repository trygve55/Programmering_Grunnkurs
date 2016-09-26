import java.lang.Math.*;
import java.util.Scanner;

class TextAnalyse {
	String text;
	int[] antallTegn = new int[30];

	public TextAnalyse(String text) {
		this.text = text;
		text = text.toUpperCase();

		for (int i = 0; i < text.length(); i++) {
			if ((int) 'A' <= text.charAt(i) && (int) 'Z' >= text.charAt(i)) {
				antallTegn[text.charAt(i) - 'A']++;
			} else if ((int) 'Æ' == text.charAt(i)) {
				antallTegn[26]++;
			} else if ((int) 'Ø' == text.charAt(i)) {
				antallTegn[27]++;
			} else if ((int) 'Å' == text.charAt(i)) {
				antallTegn[28]++;
			} else {
				antallTegn[29]++;
			}
		}
	}

	public int getForskjelligeBokstaver() {
		int forskjelligeBokstaver = 0;
		for (int i = 0; i < antallTegn.length - 1; i++) {
			if (antallTegn[i] > 0) forskjelligeBokstaver++;
		}
		return forskjelligeBokstaver;
	}

	public int getAntallBokstaver() {
		int antallBokstaver = 0;
		for (int i = 0; i < antallTegn.length - 1; i++) {
			antallBokstaver +=  antallTegn[i];
		}
		return antallBokstaver;
	}

	public int getProsentIkkeBokstaver() {
		int antallIkkeBokstaver = antallTegn[29];
		int totalAntallTegn = 0;
		for (int i = 0; i < antallTegn.length; i++) {
			totalAntallTegn +=  antallTegn[i];
		}
		return (int) Math.round((double) antallIkkeBokstaver/totalAntallTegn*100);
	}

	public int getAntallTegn(char bokstav) {
		if ('A' <= bokstav && 'Z' >= bokstav) {
			return antallTegn[bokstav - 'A'];
		} else {
			return -1;
		}
	}

	public String getVanligsteBokstav() {
		int vanligsteBokstavMax = 0, vanligsteBokstavAntall = 0, tegnTelt = 0;

		for (int i = 0; i < antallTegn.length; i++) {
			if (vanligsteBokstavMax < antallTegn[i]) {
				vanligsteBokstavMax = antallTegn[i];
				vanligsteBokstavAntall = 1;
			} else if (vanligsteBokstavMax == antallTegn[i]) {
				vanligsteBokstavAntall++;
			}
		}

		if (vanligsteBokstavAntall == 0) return "Det er ingen bokstaver!";

		int[] vanligsteBokstav = new int[vanligsteBokstavAntall];
		String returnString = "";

		for (int i = 0; i < antallTegn.length; i++) {
			if (vanligsteBokstavMax == antallTegn[i]) {
				returnString += (char) (i + 'A');
				tegnTelt++;

				if (vanligsteBokstavAntall > tegnTelt ) {
					returnString += ", ";
				}
			}
		}

		returnString += " (med " + vanligsteBokstavMax + " tegn.)";

		return returnString;
	}

	public String getText() {
		return text;
	}

}

class Input {
	public Input() {}

	public int getInt(String dialog, int minimum, int maksimum) {

		int inputTall = 0;
		boolean noExit = true;

		while (noExit) {
			System.out.println(dialog);
			Scanner sc = new Scanner(System.in);

			try {
    			inputTall = sc.nextInt();
			} catch (Exception e) {
				System.out.println("ikke int");
				continue;
			}

			if (inputTall < minimum) {
				System.out.println("Tallet kan ikke vaere lavere en " + minimum + "!");
				continue;
			} else if (inputTall > maksimum) {
				System.out.println("Tallet kan ikke vaere hoyere en " + maksimum + "!");
				continue;
			} else {
				noExit = false;
			}
		}
		return inputTall;
	}

	public String getString(String dialog) {

		String inputString = "";
		System.out.println(dialog);
		Scanner sc = new Scanner(System.in);
		inputString = sc.nextLine();

		return inputString;
	}
}

class TextKlient {
	public static void main(String[] args) {

		Input input = new Input();
		boolean noExit = true, noExitTegn = true;
		String inputString = "";
		char inputChar = 'A';

		while (noExit) {
			inputString = input.getString("Skriv en tekst du vill analysere: \n (Skriv -1 for aa avslutte)");
			if (inputString.matches("-1")) {
				noExit = false;
			} else {
				TextAnalyse text = new TextAnalyse(inputString);

				System.out.println("\nTeksten som blir analysert er: " + text.getText());
				System.out.println("Antall bokstaver: " + text.getAntallBokstaver());
				System.out.println("Antall forskjellige bokstaver: " + text.getForskjelligeBokstaver());
				System.out.println("Prosent ikke bokstaver: " + text.getProsentIkkeBokstaver() + "%");
				System.out.println("Vanligste bokstav(er): " + text.getVanligsteBokstav());

				noExitTegn = true;
				while (noExitTegn) {
					inputString = input.getString("\nSkriv en bokstav du vil sjekke antall forekomster av: \n (Skriv -1 for aa gaa tilbake)");
					try {
						inputChar = Character.toUpperCase(inputString.charAt(0));
					} catch (Exception e) {
						continue;
					}

					if (inputString.matches("-1")) {
						noExitTegn = false;
					} else if ((int) 'A' <= inputChar && (int) 'Z' >= inputChar || inputChar == 'æ' || inputChar == 'ø' || inputChar == 'å') {
						System.out.println("\nDet er " + text.getAntallTegn(inputChar) + " forekomster av " + inputChar + " i teksten");
					}
				}
			}
		}
	}
}