 import java.util.Scanner;
 
 class Person {
	String fornavn, etternavn;
	int fødselsår;
	
	public Person(String fornavn, String etternavn, int fødselsår) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.fødselsår = fødselsår;
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public int getFødselsår() {
		return fødselsår;
	}
 }
 
 class ArbTaker {
	Person personalia;
	long arbtakernr;
	int ansettelsesår, månedslønn;
	double skatteprosent;
	
	public ArbTaker(Person personalia, long arbtakernr, int ansettelsesår, int månedslønn, double skatteprosent) {
		this.personalia = personalia;
		this.arbtakernr = arbtakernr;
		this.ansettelsesår = ansettelsesår;
		this.månedslønn = månedslønn;
		this.skatteprosent = skatteprosent;
	}
	
	public String getPersonalia() {
		return personalia.getEtternavn() + ", " + personalia.getFornavn() + " (" + personalia.getFødselsår() + ")";
	}
	
	public long getArbtakernr() {
		return arbtakernr;
	}
	
	public int getAnsettelsesår() {
		return ansettelsesår;
	}
	
	public int getMånedslønn() {
		return månedslønn;
	}
	
	public void setMånedslønn(int månedslønn) {
		this.månedslønn = månedslønn;
	}
	
	public double getSkatteprosent() {
		return skatteprosent;
	}
	
	public void setSkatteprosent(double skatteprosent) {
		this.skatteprosent = skatteprosent;
	}
	
	public int getSkatteTrekkPerMåned() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		int måned = kalender.get(java.util.Calendar.MONTH);
		if (måned == 6) {
			return 0;
		} else if (måned == 12) {
			return (int) ((double)getMånedslønn()*getSkatteprosent()/200);
		} else {
			return (int) ((double)getMånedslønn()*getSkatteprosent()/100);
		}
	}
	
	public int getBruttolønnPerÅr() {
		return getMånedslønn()*12;
	}
	
	public int getSkatteTrekkPerÅr() {
		return (int) ((double) getSkatteTrekkPerMåned()*10.5);
	}
	
	public String getNavnFormen() {
		return personalia.getEtternavn() + ", " + personalia.getFornavn();
	}
	
	public int getAlder() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - personalia.getFødselsår();
	}
	
	public int getÅrAnsatt() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - getAnsettelsesår();
	}
	
	public boolean isAnsattOverÅr(int år) {
		return (getÅrAnsatt() > år);
	}
	
	public String toString() {
		return "\nNavn: " + getNavnFormen() + "\nFødselsår: " + personalia.getFødselsår() + "\nAlder: " + getAlder() + " år\nÅr ansatt: " + getÅrAnsatt() + " år \nArbeidstaker nummer: " + getArbtakernr() + "\n\nSkatteprosent: " + getSkatteprosent() + "%\nBrutto månedslønn: " + getMånedslønn() + " kr\nMånedlig skattetrekk: " + getSkatteTrekkPerMåned() + " kr\nÅrlig bruttolønn: " + getBruttolønnPerÅr() + " kr\nÅrlig skattetrekk: " + getSkatteTrekkPerÅr() + " kr";
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
	
	public long getLong(String dialog, long minimum, long maksimum) {
		
		long inputTall = 0;
		boolean noExit = true;
		
		while (noExit) {
			System.out.println(dialog);
			Scanner sc = new Scanner(System.in);
			
			try {
    			inputTall = sc.nextLong();
			} catch (Exception e) {
				System.out.println("ikke long");
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
	
	public String getString(String dialog, int minLength) {
		
		
		String inputString = "";
		while (inputString.length() < minLength) {
			System.out.println(dialog);
			Scanner sc = new Scanner(System.in);
			inputString = sc.nextLine();
			if (inputString.length() < minLength) {
				System.out.println("Input må være " + minLength + " tegn eller lenger!");
			}
		}
		
		return inputString;
	}
}
 
 class Klient {
	 public static void main(String[] args) {
		Input input = new Input();
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		int år = kalender.get(java.util.Calendar.YEAR);
		boolean noExit = true;
		Person person = new Person("Ola", "Nordmann", 1971);
		ArbTaker arbTaker = new ArbTaker(person, 1234567890L, 1990, 30000, 19.0);
		
		while (noExit) {
			
			System.out.println(arbTaker.toString());
			
			switch(input.getInt("\n1 - Legg inn ny person. \n2 - Sett ny månedslønn. \n3 - Sett ny skatteprosent. \n-1 - Avslutt.", -1, 3)) {
				
				case -1:	noExit = false;
							break;
							
				case 1:		person = new Person(input.getString("\nFornavn: ", 1), input.getString("\nEtternavn: ", 1), input.getInt("\nFødselsår: ", 1900, år));
							arbTaker = new ArbTaker(person, input.getLong("\nArbeidstaker nummer(10 siffer): ", 1000000000L, 9999999999L), input.getInt("\nAnsettelsesår: ", person.getFødselsår(), år) , input.getInt("\nMånedslønn: ", 0, 999999999), input.getDouble("\nSkatteprosent: ", 0.0, 100.0));
							break;
							
				case 2:		arbTaker.setMånedslønn(input.getInt("\nNy månedslønn: ", 0, 999999999));
							break;
							
				case 3:		arbTaker.setSkatteprosent(input.getDouble("\nNy skatteprosent: ", 0.0, 100.0));
							break;
			}
		}
	 }
 }