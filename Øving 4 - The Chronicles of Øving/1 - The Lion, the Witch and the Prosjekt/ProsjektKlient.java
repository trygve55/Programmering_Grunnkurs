import static javax.swing.JOptionPane.*;

class Prosjekt {

	private String prosjektTittel;
	private String prosjektAnsvarlig;
	private double prosjektBudsjett;

	public Prosjekt(String s, String ss, double i) {
		this.prosjektTittel = s;
		this.prosjektAnsvarlig = ss;
		this.prosjektBudsjett = i;
	}

	public Prosjekt(String s, String ss) {
		this.prosjektTittel = s;
		this.prosjektAnsvarlig = ss;
	}

	public String getTittel () {
		return (prosjektTittel);
	}

	public String getAnsvarlig () {
		return (prosjektAnsvarlig);
	}

	public double getBudsjett () {
		return (prosjektBudsjett);
	}

	public boolean changeBudsjett(double d) {
		if (0 > prosjektBudsjett + d) {
			showMessageDialog(null, "Budsjettet er ikke stort nokk, bruk mindre midler.");
			return false;
		} else {
			prosjektBudsjett += d;
			return true;
		}
	}

	public String toString() {
		return ("Prosjekt titel: " + prosjektTittel + ", prosjekt ansvarlig: " + prosjektAnsvarlig + ", prosjekt budsjett: " + prosjektBudsjett + ".");
	}
}

class ProsjektKlient {

	private static String getString(String s) {

		String input = null;

		while (true) {
			input = showInputDialog(s);
			if (input.length() == 0) {
				showMessageDialog(null, "Ikke gyldig input.");
			} else {
				break;
			}
		}

		return input;
	}

	private static double getDouble(String s) {
		String input;
		double i;
		while (true) {
			input = showInputDialog(s);
			try {
				i = Double.parseDouble(input);
				break;
			} catch (Exception e) {
				showMessageDialog(null, "Ikke gyldig tall.");
			}
		}
		return i;
	}

	public static void main(String[] args) {
		Prosjekt prosjekt = new Prosjekt(getString("Tittel på prosjekt:"), getString("Navn på prosjektansvarlig:"), getDouble("Startbudsjett for prosjekt:"));

		while (true) {
			System.out.println(prosjekt.toString());
			prosjekt.changeBudsjett(getDouble("Endre Budsjettet. Positive tall for å øke og negative tall for å senke."));
		}
	}
}
