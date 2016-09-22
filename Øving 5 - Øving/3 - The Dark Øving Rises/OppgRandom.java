import java.util.Random;
import static javax.swing.JOptionPane.*;

class MinRandom {
	public MinRandom() {
	}

	public int nesteHeltall(int i, int ii) {
		java.util.Random terning = new java.util.Random();
		return terning.nextInt(ii - i + 1) + i;
	}

	public double nesteDesimaltall(int i, int ii) {
			if (i == ii) {
				return i;
			}
			java.util.Random terning = new java.util.Random();
			return i + terning.nextDouble()*(ii -i);
	}
}

class Input {

	public Input() {
	}

	public int getInt(String s, int ii, int iii) {    //ii = min iii = max
		int i = -2;
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
				showMessageDialog(null, "Skriv et tall som er " + (ii) + " eller større!");
			}
			if (i > iii) {
							showMessageDialog(null, "Skriv et tall som er " + (iii) + "eller mindre!");
			}
		} while (i == -2 || (i < ii) || (i > iii) );
	return i;
	}
}

class OppgRandom {
	public static void main(String[] args) {
		MinRandom rand = new MinRandom();
		Input input = new Input();
		int nedre = 0, ovre = 0;

		while (true) {
			nedre = input.getInt("Nerde grense: (-1 for å avslutte)", -1, 9999999);
			if (nedre == -1) break;
			ovre = input.getInt("Øvre grense:", nedre, 9999999);
			if (ovre == -1) break;

			System.out.println("Heltall: " + rand.nesteHeltall(nedre, ovre));
			System.out.println("Desimaltall: " + rand.nesteDesimaltall(nedre, ovre));

		}
	}

}