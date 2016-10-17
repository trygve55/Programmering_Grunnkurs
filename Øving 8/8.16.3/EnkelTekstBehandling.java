import java.util.Scanner;

class Tekst {
	String tekst;
	
	public Tekst(String tekst) {
		this.tekst = tekst;
	}
	
	public int getLengde() {
		return tekst.length();
	}
	
	public double getMeanWordLength() {
		String[] ord = tekst.split(" ");
		int lengde = 0;
		
		for (int i = 0; i < ord.length; i++) {
			lengde += ord[i].length();
		}
			
		return (double) lengde/ord.length;
	}
	
		public int getWords() {
		String[] ord = tekst.split(" ");
			
		return ord.length;
	}
	
	public String getMeanWordPerPeriodLength() {
		String[] setning = tekst.split("[.!?:]");
		double[] lengde = new double[setning.length];
		String returnString = "";
		
		for (int k = 0;k < setning.length;k++) {
			String[] ord = setning[k].split("[ .!?:]");
			
			
			for (int i = 0; i < ord.length; i++) {
				lengde[k] += ord[i].length();
			}
			lengde[k] /= ord.length + ((k != 0) ? -1 : 0);
		}
		
		for (int i = 0; i < lengde.length;i++) {
			if (i < lengde.length - 1) {
				returnString += lengde[i] + ", ";
			} else {
				returnString += lengde[i];
			}
		}
		return returnString;
	}
	
	public String getTekst() {
		return tekst;
	}
	
	public String replaceWord(String ord, String ordTil) {
		tekst = tekst.replace(ord,ordTil);
		return tekst;
	}
	
	public String upperCase() {
		return tekst.toUpperCase();
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


class EnkelTekstBehandling {
	public static void main(String[] args) {
		
		Input input = new Input();
		Tekst tekst = new Tekst("Hei du der! Du der. Kebab.");
		
		int action = 0, inputVal = 0;
		boolean noExit = true;
		
		while (noExit) {
			action = input.getInt("1 - Legg inn ny tekst.\n2 - Vis teksten.\n3 - Finn gjennomsnitlig lengde på ord i teksten.\n4 - Finn gjennomsnitlig lengde på ord per setning.\n5 - Bytt ut et ord i teksten med et anna.\n6 - Skriv ut tekst med store bokstaver.\n7 - Skriv ut lengde på tekst.\n-1 - Avslutte. \n", -1, 8);
			
			switch(action) {
				case -1:	noExit = false;
							break;
							
				case  1:	tekst = new Tekst(input.getString("Skriv inn en tekst:"));
							break;
							
				case  2:	System.out.println(tekst.getTekst());
							break;
							
				case  3:	System.out.println("Den gjennomsnitlige lengda på ord i teksten er " + tekst.getMeanWordLength() + " bokstaver.");
							break;
							
				case  4:	System.out.println("Den gjennomsnitlige lengda på ord per setning er " + tekst.getMeanWordPerPeriodLength());
							break;
							
				case  5:	System.out.println(tekst.replaceWord(input.getString("Skriv et ord som skal byttes ut:"), input.getString("Skriv ordet som skal settes inn:")));
							break;
							
				case  6:	System.out.println(tekst.upperCase());
							break;
							
				case  7:	System.out.println(tekst.getLengde());
							break;
							
				case 8:		System.out.println("Antall ord er" + tekst.getWords() + ".");
				
			}
			action = 0;
		}
	}
}