import static javax.swing.JOptionPane.*;

class Tallsystem {

	//Spør etter int input med argumentet som tekst i dialogvinduet.
	public static int getInt(String text) {

		boolean exit = false;
		int intIn = 0;
		String input = "";

		while (exit == false) {

			input = showInputDialog(text);
			exit = true;

			try {
				intIn = Integer.parseInt(input);
			} catch (Exception e) {
				showMessageDialog(null,"Skriv et gyldig heltall");
				exit = false;
			}
		}
		return intIn;
	}

	//Gjør et tall til char(med HEX)
	public static char numToString(int var) {

		char c = '0';

		if (var < 10) {
			c = (char) (48 + var);
		} else {
			c = (char) (55 + var);
		}
		return c;
	}

	// Skriver utregninger
	public static void printCalc(int tallSystem, int tall, int power) {

		System.out.println(
		tall + " - " + (int)Math.pow(tallSystem,power) + " * " + (tall / (int)Math.pow(tallSystem,power)) +
		" =\n" + tall + " - " + (int)Math.pow(tallSystem,power)*(tall / (int)Math.pow(tallSystem,power)) +
		" = " + (tall - (int)Math.pow(tallSystem,power)*(tall / (int)Math.pow(tallSystem,power))));

	}

	//main
	public static void main(String[] args) {

		int tallSystem = getInt("Velg tallsystem:");
		int tall = getInt("Velg et tall:");
		int power = 40;
		boolean exit = false;
		String text = tall + " er i " + tallSystem + "-tallsystemet : ";

		while (exit == false) {

			if ((tall - (int)Math.pow(tallSystem,power)) >= 0) {

				exit = true;

			} else {

				power -= 1;
			}

		}
		while (power >= 0) {

			if ((tall - (int)Math.pow(tallSystem,power)) >= 0) {

				printCalc(tallSystem, tall, power);
				text += numToString(tall / (int)Math.pow(tallSystem,power));
				tall -= (int)Math.pow(tallSystem,power)*(tall / (int)Math.pow(tallSystem,power));

			} else {

				text += "0";

				}

			power -= 1;
		}

		showMessageDialog(null,text);
	}
}