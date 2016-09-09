import static javax.swing.JOptionPane.*;
import java.text.DecimalFormat;

class kjoettdeig {

	public static double kgPris(double kg, double pr) {
		return pr/kg;
	}


	public static void main(String[] args) {


		double AVekt = 0.450, APris = 35.90, BVekt = 0.500, BPris = 39.50;

		DecimalFormat f = new DecimalFormat("#.00");

		double AKgPris = kgPris(AVekt, APris);
		double BKgPris = kgPris(BVekt, BPris);



		showMessageDialog(null, "Kgprisen for A er " + f.format(AKgPris) + "kr og for B er den " + f.format(BKgPris) + "kr.");

		if (AKgPris == BKgPris) {
			showMessageDialog(null, "Kgprisen er lik.");
		} else if (AKgPris > BKgPris) {
			showMessageDialog(null, "B er billigst.");
		} else {
			showMessageDialog(null, "A er billigst.");
		}
	}
}