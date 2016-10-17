import java.util.Scanner;

class Student {
	private String navn;
	private int antOppg;
	
	public Student(String navn, int antOppg) {
		this.navn = navn;
		this.antOppg = antOppg;
	}
	
	public String getNavn() {
		return navn;
	}
	
	public int getAntOppg() {
		return antOppg;
	}
	
	public void økAntOppg(int økning) {
		antOppg += økning;
	}
	
	public String toString() {
		return "Student: " + navn + ", antall oppgaver: " + antOppg;
	}
}

class Oppgaveoversikt {
	private Student[] studenter = new Student[0];
	private int antStudent = 0;
	
	public Oppgaveoversikt() {
		
	}

	public int getAntStudenter() {
		return studenter.length;
	}
	
	public int getOppgLøst(String navn) {
		int index = getStudentIndex(navn);
		return studenter[index].getAntOppg();
	}
	
	public void addStudent(String navn) {
		int index = arrayExpand();
		studenter[index] = new Student(navn, 0);
	}
	
	public void addStudent(String navn, int antOppg) {
		int index = arrayExpand();
		studenter[index] = new Student(navn, antOppg);
	}
	
	public boolean økAntOppgStudent(String navn, int økning) {
		int index = getStudentIndex(navn);
		if (index == -1) return false;
		
		studenter[index].økAntOppg(økning);
		return true;
	}
	
	private int getStudentIndex(String navn) {
		for (int i = 0; i < studenter.length;i++) {
			if (navn.matches(studenter[i].getNavn())) return i;
		}
		
		return -1;
	}
	
	private int arrayExpand() {
		for (int i = 0; i < studenter.length;i++) {
			if (studenter[i] == null) return i;
		}
		
		Student[] newArray = new Student[studenter.length + 1];
		
		for (int i = 0; i < studenter.length;i++) {
			newArray[i] = studenter[i];
		}
		
		this.studenter = newArray;
		return studenter.length - 1;
	}
	
	public String toString() {
		String output = "";
		
		for (int i = 0;i < studenter.length;i++) {
			output += "Student: " + studenter[i].getNavn() + ", Oppgaver løst: " + studenter[i].getAntOppg() + "\n";
		}
		
		return "\n" + output;
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


class StudentKlient {
	public static void main(String[] args) {
		Input input = new Input();
		Oppgaveoversikt oppgOversikt = new Oppgaveoversikt();
		
		boolean exit = false;
		int in = 0;
		
		while (!exit) {
			in = input.getInt("\n1 - Legg til en student. \n2 - Vis antall studenter. \n3 - Vis alle studenter. " + 
			"\n4 - Legg til oppgaver for student. \n5 - Vis antall oppgaver gjort av student \n-1 - Avslutt.", -1, 5);
			System.out.println("\n");
			
			switch(in) {
				case -1:	exit = true; break;
				
				case 1: 	oppgOversikt.addStudent(input.getString("Navn på student: "), input.getInt("Hvor mange oppgaver har studenten løst: ", 0, 999999)); 
							System.out.println("Student lagt til."); break;
				
				case 2:		System.out.println("Antall studenter er " + oppgOversikt.getAntStudenter()); break;
				
				case 3:		System.out.println(oppgOversikt.toString()); break;
				
				case 4:		String inString = input.getString("Navn på student: ");
							oppgOversikt.økAntOppgStudent(inString, input.getInt("Antall oppgaver løst: ", 0, 99999));
							System.out.println("Student har gjort " + oppgOversikt.getOppgLøst(inString) + " oppgaver."); break;
				
				case 5:		System.out.println("Student har gjort " + oppgOversikt.getOppgLøst(input.getString("Navn på student: ")) + " oppgaver."); break;
			}
		}
		
	}
}