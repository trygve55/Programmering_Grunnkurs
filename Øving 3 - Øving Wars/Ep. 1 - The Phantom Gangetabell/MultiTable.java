import static javax.swing.JOptionPane.*;

class MultiTable {

	public static void Table(int tall) {
		System.out.println(tall + "-gangen:");
		for (int i = 1; i <= 10; i++) {
			System.out.println(tall + " x " + i + " = " + (tall * i));
		}
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
				} while (i == 0 || i < ii || i > iii);
				return i;
	}

	public static void main(String[] args) {
		int tall1 = getInt("Hilken gangetabel skal det startes med?", 2, 1000);
		int tall2 = getInt("Hilken gangetabel skal det avsluttes med?",tall1, 1001);
		while (tall1 <= tall2) {
			Table(tall1);
			tall1++;
		}
	}

}