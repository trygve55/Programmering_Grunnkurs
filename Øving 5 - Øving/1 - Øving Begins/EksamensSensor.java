import java.util.Scanner;

class Sensurering {
	final String eksamensFag;
	final int eksamensType;
	final double eksamensTid;     	//hour
	final int antallBesvarelser;

	public Sensurering(String eksamensFag, int eksamensType, double eksamensTid, int antallBesvarelser) {
		this.eksamensFag = eksamensFag;
		this.eksamensType = eksamensType;
		this.eksamensTid = eksamensTid;
		this.antallBesvarelser = antallBesvarelser;
	}

	public String getEksamensInfo() {
		return 	"Eksamens type: " + getType() + 
				"\nEksamens fag: " + getFag() + 
				"\nEksamens tid: " + getTid() +
				"\nAntall besvarelser: " + getBesvarelser() + 
				"\nLønn for eksamensesor: " + getLonn() + " timer.\n";
	}
	
	private double getLonn() {
		switch(eksamensType) {
			case 1:		return 3 + getTid();
				
			case 2:		return ((getBesvarelser() > 10) ? 0.15*10*getTid() + getTid()*getBesvarelser()*0.1  : 0.15*getTid()*getBesvarelser());
			
			case 3:		return 8;

			default:	throw new IllegalArgumentException("Ikke gyldig eksamens type");
		}
	}

	private String getFag() {
		return eksamensFag;
	}
	
	private String getType() {
		switch(eksamensType) {
			case 1:		return "Muntlig";
				
			case 2:		return "Skriftlig";
			
			case 3:		return "Prosjekt";

			default:	throw new IllegalArgumentException("Ikke gyldig eksamens type");
		}
	}
	
	private double getTid() {
		return eksamensTid;
	}
	
	private int getBesvarelser() {
		return antallBesvarelser;
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
	
	public double getDouble(String dialog, double minimum, double maksimum) {
		
		double inputTall = 0;
		boolean noExit = true;
		
		while (noExit) {
			System.out.println(dialog);
			Scanner sc = new Scanner(System.in);
			
			try {
    			inputTall = sc.nextDouble();
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

class EksamensSensor {

	public static void main(String[] args) {

		Input input = new Input();
		boolean noExit = true;
		int eksamensType = 0, antallBesvarelser = 0;
		String eksamensFag = "";
		double eksamensTid = 0.0;
		
		
		while (noExit) {
			
			while (eksamensType == 0) {
				eksamensType = input.getInt("Hvilken type eksamen skal rettes?\n1 - Muntlig eksamen\n2 - Skriftlig eksamen\n3 - Prosjektoppgave\n -1 - For å avslutte", -1, 3);
			}
			
			if (eksamensType == -1) noExit = false;
			else {
			
				while (eksamensFag.length() == 0) {
					eksamensFag = input.getString("Eksamensfag:");
				}
				
				if (eksamensType == 2) antallBesvarelser = input.getInt("Hvor mange besvarelser inneholder eksamen?", 0, 999);
				
				if (eksamensType != 3) eksamensTid = input.getDouble("Hvor mange timer ble satt av til eksamen?", 0, 24);

				Sensurering eksamen = new Sensurering(eksamensFag, eksamensType, eksamensTid, antallBesvarelser);
				System.out.println(eksamen.getEksamensInfo());
				
				eksamensType = 0;
				eksamensFag = "";
			}
		}
	}
}