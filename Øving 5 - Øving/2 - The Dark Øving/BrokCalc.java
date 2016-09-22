import java.util.Scanner;

class Brok {
	final int teller;
	final int nevner;
	int tellerSvar;
	int nevnerSvar;

	public Brok(int teller, int nevner) {
		if (nevner == 0) {
			throw new IllegalArgumentException();
		}
		this.teller = teller;
		this.nevner = nevner;
	}

	public Brok(int teller) {
		this.teller = teller;
		this.nevner = 1;
	}
	public int getNevner() {
		return nevner;
	}
	public int getTeller() {
		return teller;
	}
	public int getNevnerSvar() {
		return nevnerSvar;
	}
	public int getTellerSvar() {
		return tellerSvar;
	}
	public void forkortBrokSvar() {
		int t = 2, fellesNevner = 1;
		while (t <= tellerSvar) {
			if ((tellerSvar % t) == 0 && (nevnerSvar % t) == 0) {
				fellesNevner = t;
			}
			t++;
		}

		this.tellerSvar = tellerSvar/fellesNevner;
		this.nevnerSvar = nevnerSvar/fellesNevner;
	}
	public void substrahere(Brok b) {
		this.tellerSvar = -b.getTeller()*nevner + b.getNevner()*teller;
		this.nevnerSvar = nevner * b.getNevner();
		forkortBrokSvar();
	}
	public void addere(Brok b) {
		this.tellerSvar = b.getTeller()*nevner + b.getNevner()*teller;
		this.nevnerSvar = nevner * b.getNevner();
		forkortBrokSvar();
	}
	public void multiplisere(Brok b) {
		this.tellerSvar = b.getTeller() * teller;
		this.nevnerSvar = nevner * b.getNevner();
		forkortBrokSvar();
	}
	public void dividere(Brok b) {
		this.tellerSvar = b.getNevner() * teller;
		this.nevnerSvar = nevner * b.getTeller();
		forkortBrokSvar();
	}
}

class BrokCalc {
	public static void printEq(String s, Brok brok1, Brok brok2) {
		System.out.println(brok1.getTeller() + "/" + brok1.getNevner() + " " + s + " " + brok2.getTeller() + "/" + brok2.getNevner() + " = " + brok1.getTellerSvar() + " / " + brok1.getNevnerSvar());
	}

	public static int getInt(String s) {
		int i = 0;
		boolean noExit = true;
		while (noExit) {
			noExit = false;
			System.out.println(s);
			Scanner sc = new Scanner(System.in);
			try {
    			i = sc.nextInt();
			} catch (Exception e) {
				noExit = true;
			}
		}
		return i;
	}

	public static void main(String[] args) {

		Brok brok1 = new Brok(getInt("Brok 1 teller:"), getInt("Brok 1 nevner:"));
		Brok brok2 = new Brok(getInt("Brok 2 teller:"), getInt("Brok 2 nevner:"));

		brok1.addere(brok2);
		printEq("+", brok1, brok2);
		brok1.substrahere(brok2);
		printEq("-", brok1, brok2);
		brok1.multiplisere(brok2);
		printEq("*", brok1, brok2);
		brok1.dividere(brok2);
		printEq(":", brok1, brok2);
	}
}