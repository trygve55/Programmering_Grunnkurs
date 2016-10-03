import java.util.Scanner;

class NyString {
	String tekst;
	
	public NyString(String inputString) {
		this.tekst = inputString;
	}
	
	public void forkort() {
		String[] ord = tekst.split(" ");
		String returnString = "";
		for (int i = 0; i < ord.length; i++) {
			returnString += ord[i].charAt(0);
		}
		tekst = returnString;
	}
	
	public void fjernTegn(char tegn) {
		String returnString = "";
		for (int i = 0; i < tekst.length();i++ ) {
			if (Character.toUpperCase(tekst.charAt(i)) != Character.toUpperCase(tegn)) {
				returnString += tekst.charAt(i);
			}
		}
		tekst = returnString;
	}
	
	public String getTekst() {
		return tekst;
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

class StringKlient {
	public static void main(String[] args) {
		boolean noExit = true;
		Input input = new Input();
		NyString tekst = new NyString("Test tekst 123");
		String inputString = "";
		
		while (noExit) {
			System.out.println("\nValgt tekst er: " + tekst.getTekst());
			switch (input.getInt("1 - Skriv inn ny tekst. \n2 - Forkort tekst til første bokstav i hvert ord. \n3 - Fjern et tegn fra teksten.\n-1 - Avlutter", -1, 3)) {
				case -1:	noExit = false;
							break;
				
				case 1:		tekst = new NyString(input.getString("Skriv inn en ny tekst for behandling:"));
							break;
							
				case 2:		tekst.forkort();
							System.out.println(tekst.getTekst());
							break;
				
				case 3:		inputString = input.getString("Hvilket tegn skal fjernes?");
							tekst.fjernTegn(inputString.charAt(0));
							System.out.println(tekst.getTekst());
							break;
							
				default:	System.out.println("Ikke gyldig valg");
							break;
				
			}
		}
	}
}