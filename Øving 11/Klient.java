import java.io.*;
import java.util.Scanner;
import java.util.Locale;

class Database {
	File saldoFile;
	File transaksjonsFile;
	
	
	public Database(String saldoFileName, String transaksjonsFileName) {
		this.saldoFile = new File(saldoFileName);
		this.transaksjonsFile = new File(transaksjonsFileName);
	}
	
	public void setSaldo(double saldo) {
		try {
			if (!saldoFile.exists()) {
				saldoFile.createNewFile();
			}
			
			FileWriter fileWriter = new FileWriter(saldoFile.getName());
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(saldo + "");
			bufferWriter.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public double getSaldo() {
		double out = 0.0;
		try {
			Scanner scanner = new Scanner(saldoFile);
			scanner.useLocale(Locale.US);
			out = scanner.nextDouble();
		} catch (Exception e) {
			System.out.println("Lager ny saldo fil.");
		}
		return out;
	}
	
	public void saveTransaction(double transaksjon) {
		char tegn;
		
		if (transaksjon >= 0) {
			tegn = 'I';
		} else {
			tegn = 'U';
		}
		
		try {
			FileWriter fileWriter = new FileWriter(transaksjonsFile.getName(), true);
			BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
			bufferWriter.write(tegn + " " + transaksjon + "\n");
			bufferWriter.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void nukeIt() {
		try {
			setSaldo(0.0);
			transaksjonsFile.delete();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}

class Konto {
	Database database = new Database("saldo.txt", "transaksjoner.txt");
	double saldo;
	
	public Konto() {
		this.saldo = database.getSaldo();
	}
	
	public Konto(double saldo) {
		this.saldo = saldo;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
		database.setSaldo(saldo);
	}
	
	public boolean transaksjon(double transaksjon) {
		if (saldo + transaksjon >= 0) {
			saldo += transaksjon;
			database.setSaldo(saldo);
			database.saveTransaction(transaksjon);
			return true;
		} else {
			database.nukeIt();
			return false;
		}
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


class Klient {
	
	public static void main(String[] args) {
		Input input = new Input();
		Konto konto = new Konto();
		
		boolean exit = false;
		
		while (!exit) {
			System.out.println("Saldoen er nå: " + konto.getSaldo() + " kr");
			
			int in = input.getInt("\n1 - Transaksjon.\n2 - Set saldo.\n-1 - Avslutt.", -1, 2);
			System.out.println("\n");
			
			switch (in) {
				case -1: 	exit = true; break;
				
				case 1:		if (!konto.transaksjon(input.getDouble("Størelse på transaksjon: ", -9999999, 9999999))) {
								exit = true;
								System.out.println("Konto er nå tom, alt slettes!");
							}
							break;
				
				case 2:		konto.setSaldo(input.getDouble("Saldoen skal settes til: ", 0, 9999999)); break;
			}
		}
	}
}