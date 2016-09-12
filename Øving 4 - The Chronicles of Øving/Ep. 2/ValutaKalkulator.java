import java.util.Scanner;
import java.text.DecimalFormat;

class Valuta {
	private String valutaNavn;
	private double valutaKurs;

	public Valuta(String s, double d) {
		this.valutaNavn = s;
		this.valutaKurs = d;
	}

	public String tilNok (double d) {
		return formatValuta(d*valutaKurs);
	}

	public String fraNok (double d) {
		return formatValuta(d/valutaKurs);
	}
	public double getKurs() {
		return valutaKurs;
	}

	public String getValuta() {
		return valutaNavn;
	}

	public String formatValuta(double d) {
		DecimalFormat money = new DecimalFormat("#.00");
		return money.format(d);
	}

	public String toString() {
		return "Valuta: " + valutaNavn + ", kurs : " + valutaKurs;
	}
}

class ValutaKalkulator {

	private static double getDouble(String s) {
		String input;
		double d;
		while (true) {
			System.out.println(s);
			Scanner scan = new Scanner(System.in);
			try {
				d = scan.nextDouble();
				break;
			} catch (Exception e) {
				System.out.print("Ikke gyldig tall.");
			}
		}
		return d;
	}

	private static int getInt(String s) {
		String input;
		int i;
		while (true) {
			System.out.println(s);
			Scanner scan = new Scanner(System.in);
			try {
				i = scan.nextInt();
				break;
			} catch (Exception e) {
				System.out.print("Ikke gyldig tall.");
			}
		}
		return i;
	}


	public static void main(String[] args) {

		Valuta USD = new Valuta("USD", 7.00);
		Valuta EUR = new Valuta("EUR", 10.00);
		Valuta SEK = new Valuta("SEK", 0.97);

		boolean noExit = true;
		int valgtValuta, opKonv;
		double valuta;

		while (noExit) {
			valgtValuta = 0;
			opKonv = 0;
			valuta = 0;
			while (valgtValuta == 0 && noExit) {
				switch (getInt("Velg valuta: \n1. USD \n2. EUR \n3. SEK\n4. Avslutt")) {
					case 1: 	valgtValuta = 1;
								break;

					case 2:		valgtValuta = 2;
								break;

					case 3:		valgtValuta = 3;
								break;

					case 4:		noExit = false;
								break;

					default: 	System.out.println("Ikke gyldig oprasjon");
								break;
					}
				}

			if (noExit == false) break;

			while (opKonv == 0) {
				switch (getInt("Velg oprasjon: \n1. Konverter til NOK\n2. Konverter fra NOK\n3. Få valutakurs i forhold til NOK")) {
					case 1: 	opKonv = 1;
								break;

					case 2:		opKonv = 2;
								break;

					case 3:		opKonv = 3;
								break;

					default: 	System.out.println("Ikke gyldig oprasjon");
								break;

				}
			}

			while (opKonv == 1 || opKonv == 2) {
				valuta = getDouble("Hvor mye valuta vil du konvertere?");
				if (valuta != 0) {
					break;
				}
			}

			if (opKonv == 1) {
				switch(valgtValuta) {
					case 1: System.out.println(USD.formatValuta(valuta) + " " + USD.getValuta() + " er "+ USD.tilNok(valuta) + " NOK.");
							break;

					case 2: System.out.println(USD.formatValuta(valuta) + " " + EUR.getValuta() + " er "+ EUR.tilNok(valuta) + " NOK.");
							break;

					case 3: System.out.println(USD.formatValuta(valuta) + " " + SEK.getValuta() + " er "+ SEK.tilNok(valuta) + " NOK.");
							break;

					default: 	System.out.println("Feil");
								break;

				}
			}

			if (opKonv == 2) {
				switch(valgtValuta) {
					case 1: System.out.println(USD.formatValuta(valuta) + " " + USD.getValuta() + " er "+ USD.fraNok(valuta) + " NOK.");
							break;

					case 2: System.out.println(USD.formatValuta(valuta) + " " + EUR.getValuta() + " er "+ EUR.fraNok(valuta) + " NOK.");
							break;

					case 3: System.out.println(USD.formatValuta(valuta) + " " + SEK.getValuta() + " er "+ SEK.fraNok(valuta) + " NOK.");
							break;

					default: 	System.out.println("Feil");
								break;

				}
			}

			if (opKonv == 3) {
				switch(valgtValuta) {
					case 1: System.out.println("1 " + USD.getValuta() + " er "+ USD.getKurs() + " NOK.");
							break;

					case 2: System.out.println("1 " + EUR.getValuta() + " er "+ EUR.getKurs() + " NOK.");
							break;

					case 3: System.out.println("1 " + SEK.getValuta() + " er "+ SEK.getKurs() + " NOK.");
							break;

					default: 	System.out.println("Feil");
								break;

				}
			}


		}
	}
}