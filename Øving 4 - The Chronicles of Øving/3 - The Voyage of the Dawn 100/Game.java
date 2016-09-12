import java.util.Random;
import static javax.swing.JOptionPane.*;

class Spiller {
	int sumPoeng;
	String spillerNavn;
	boolean hardMode;

	public Spiller(String s, boolean b) {
		this.sumPoeng = 0;
		this.spillerNavn = s;
		this.hardMode = b;
	}

	public int getSumPoeng() {
		return sumPoeng;
	}

	public String getName() {
		return spillerNavn;
	}

	public boolean erFerdig() {
		boolean b = false;
		if (hardMode) {

			if (sumPoeng == 100) {
				b = true;
			} else if (sumPoeng > 100) {
				this.sumPoeng = 0;
				b = false;
				System.out.println(getName() + " har nådd en sum som er over 100 og derfor blir summen satt til 0 igjen.");
			}

		} else {
			b = (sumPoeng >= 100);
		}

		return b;
	}

	public void kastTerningen() {
		java.util.Random terning = new java.util.Random();
		int kast = terning.nextInt(6)+1;
		if (kast == 1) {
			this.sumPoeng = 0;
			System.out.println(spillerNavn + " kaster " + kast + " og summen blir satt ned til 0 igjen.");
		} else {
			sumPoeng += kast;
			System.out.println(spillerNavn + " kaster " + kast + " og får summen " + sumPoeng);
		}
	}
}

class Game {
	public static void main(String[] args) {

		boolean hardMode = (0 == showConfirmDialog(null, "Hardmode?", null, YES_NO_OPTION));

		Spiller spillerEn = new Spiller("Geir", hardMode);
		Spiller spillerTo = new Spiller("Key", hardMode);

		int antallKast = 0;

		while (spillerEn.erFerdig() == false && spillerTo.erFerdig() == false) {
			antallKast++;
			spillerEn.kastTerningen();
			spillerTo.kastTerningen();
		}

		if (spillerEn.erFerdig() == true && spillerTo.erFerdig() == false) {
			System.out.println("Etter " + antallKast + " kast så vinner " + spillerEn.getName() + ".");
		}

		if (spillerEn.erFerdig() == false && spillerTo.erFerdig() == true) {
			System.out.println("Etter " + antallKast + " kast så vinner " + spillerTo.getName() + ".");
		}

		if (spillerEn.erFerdig() == true && spillerTo.erFerdig() == true) {
			System.out.println("Etter " + antallKast + " kast så vinner " + spillerTo.getName() + " og " + spillerEn.getName() + " samtidig.");
		}

	}
}