 import java.util.Scanner;
 
 class Person {
	String fornavn, etternavn;
	int f�dsels�r;
	
	public Person(String fornavn, String etternavn, int f�dsels�r) {
		this.fornavn = fornavn;
		this.etternavn = etternavn;
		this.f�dsels�r = f�dsels�r;
	}
	
	public String getFornavn() {
		return fornavn;
	}
	
	public String getEtternavn() {
		return etternavn;
	}
	
	public int getF�dsels�r() {
		return f�dsels�r;
	}
 }
 
 class ArbTaker {
	Person personalia;
	long arbtakernr;
	int ansettelses�r, m�nedsl�nn;
	double skatteprosent;
	
	public ArbTaker(Person personalia, long arbtakernr, int ansettelses�r, int m�nedsl�nn, double skatteprosent) {
		this.personalia = personalia;
		this.arbtakernr = arbtakernr;
		this.ansettelses�r = ansettelses�r;
		this.m�nedsl�nn = m�nedsl�nn;
		this.skatteprosent = skatteprosent;
	}
	
	public String getPersonalia() {
		return personalia.getEtternavn() + ", " + personalia.getFornavn() + " (" + personalia.getF�dsels�r() + ")";
	}
	
	public long getArbtakernr() {
		return arbtakernr;
	}
	
	public int getAnsettelses�r() {
		return ansettelses�r;
	}
	
	public int getM�nedsl�nn() {
		return m�nedsl�nn;
	}
	
	public void setM�nedsl�nn(int m�nedsl�nn) {
		this.m�nedsl�nn = m�nedsl�nn;
	}
	
	public double getSkatteprosent() {
		return skatteprosent;
	}
	
	public void setSkatteprosent(double skatteprosent) {
		this.skatteprosent = skatteprosent;
	}
	
	public int getSkatteTrekkPerM�ned() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		int m�ned = kalender.get(java.util.Calendar.MONTH);
		if (m�ned == 6) {
			return 0;
		} else if (m�ned == 12) {
			return (int) ((double)getM�nedsl�nn()*getSkatteprosent()/200);
		} else {
			return (int) ((double)getM�nedsl�nn()*getSkatteprosent()/100);
		}
	}
	
	public int getBruttol�nnPer�r() {
		return getM�nedsl�nn()*12;
	}
	
	public int getSkatteTrekkPer�r() {
		return (int) ((double) getSkatteTrekkPerM�ned()*10.5);
	}
	
	public String getNavnFormen() {
		return personalia.getEtternavn() + ", " + personalia.getFornavn();
	}
	
	public int getAlder() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - personalia.getF�dsels�r();
	}
	
	public int get�rAnsatt() {
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		return kalender.get(java.util.Calendar.YEAR) - getAnsettelses�r();
	}
	
	public boolean isAnsattOver�r(int �r) {
		return (get�rAnsatt() > �r);
	}
	
	public String toString() {
		return "\nNavn: " + getNavnFormen() + "\nF�dsels�r: " + personalia.getF�dsels�r() + "\nAlder: " + getAlder() + " �r\n�r ansatt: " + get�rAnsatt() + " �r \nArbeidstaker nummer: " + getArbtakernr() + "\n\nSkatteprosent: " + getSkatteprosent() + "%\nBrutto m�nedsl�nn: " + getM�nedsl�nn() + " kr\nM�nedlig skattetrekk: " + getSkatteTrekkPerM�ned() + " kr\n�rlig bruttol�nn: " + getBruttol�nnPer�r() + " kr\n�rlig skattetrekk: " + getSkatteTrekkPer�r() + " kr";
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
				System.out.println("Input m� v�re " + minLength + " tegn eller lenger!");
			}
		}
		
		return inputString;
	}
}
 
 class Klient {
	 public static void main(String[] args) {
		Input input = new Input();
		java.util.GregorianCalendar kalender = new java.util.GregorianCalendar();
		int �r = kalender.get(java.util.Calendar.YEAR);
		boolean noExit = true;
		Person person = new Person("Ola", "Nordmann", 1971);
		ArbTaker arbTaker = new ArbTaker(person, 1234567890L, 1990, 30000, 19.0);
		
		while (noExit) {
			
			System.out.println(arbTaker.toString());
			
			switch(input.getInt("\n1 - Legg inn ny person. \n2 - Sett ny m�nedsl�nn. \n3 - Sett ny skatteprosent. \n-1 - Avslutt.", -1, 3)) {
				
				case -1:	noExit = false;
							break;
							
				case 1:		person = new Person(input.getString("\nFornavn: ", 1), input.getString("\nEtternavn: ", 1), input.getInt("\nF�dsels�r: ", 1900, �r));
							arbTaker = new ArbTaker(person, input.getLong("\nArbeidstaker nummer(10 siffer): ", 1000000000L, 9999999999L), input.getInt("\nAnsettelses�r: ", person.getF�dsels�r(), �r) , input.getInt("\nM�nedsl�nn: ", 0, 999999999), input.getDouble("\nSkatteprosent: ", 0.0, 100.0));
							break;
							
				case 2:		arbTaker.setM�nedsl�nn(input.getInt("\nNy m�nedsl�nn: ", 0, 999999999));
							break;
							
				case 3:		arbTaker.setSkatteprosent(input.getDouble("\nNy skatteprosent: ", 0.0, 100.0));
							break;
			}
		}
	 }
 }