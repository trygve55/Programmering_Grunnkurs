import java.io.*;
import java.util.Scanner;
import java.util.Locale;

class Database {
	File saldoFile;
	File transaksjonsFile;
	int transaksjonNr = 0;
	
	
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
	
	
	public double[] getTransaksjon() {
		double[] transaksjoner;
		try {
			String[] file = new String[100];
			FileReader fileReader = new FileReader(transaksjonsFile);
			BufferedReader bufferReader = new BufferedReader(fileReader);
			int antallLinjer = 0;
			boolean exit = false;
			
			for (int i = 0;!exit; i++) {
				file[i] = bufferReader.readLine();
				if (file[i] == null) {
					exit = true;
				} else {
					antallLinjer++;
				}
			}
			System.out.println(antallLinjer);
			transaksjoner = new double[antallLinjer];
			for (int i = 0;i < antallLinjer;i++) {
				System.out.println(file[i]);
				String[] line = file[i].split(" ");
				if (line[0].matches("U")) {
					transaksjoner[i] = - Double.parseDouble(line[1]);
				} else if (line[0].matches("I")) {
					transaksjoner[i] = Double.parseDouble(line[1]);
				}
			}
			try {
				
			} catch (Exception e) {
				System.out.println("Lager ny saldo fil.");
			}
		} catch (Exception e) {
			System.out.println(e);
			transaksjoner = null;
		}
		return transaksjoner;
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
		saldo += transaksjon;
		System.out.println("Saldo: " + saldo);
		database.setSaldo(saldo);
		if (saldo >= 0) {
			return true;
		} else {
			database.nukeIt();
			return false;
		}
	}
	
	public String getTransaksjon() {
		String out = "";
		double[] transaksjoner = database.getTransaksjon();
		for (int i = 0;i < transaksjoner.length;i++) {
			transaksjon(transaksjoner[i]);
			out += transaksjoner[i];
			System.out.println(transaksjoner[i]);
		}
		
		return out;
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
		
		konto.getTransaksjon();
		konto.setSaldo(konto.getSaldo());
		
		// while (!exit) {
			// System.out.println("Saldoen er nå: " + konto.getSaldo() + " kr");
			
			// int in = input.getInt("\n1 - Transaksjon.\n2 - Set saldo.\n-1 - Avslutt.", -1, 2);
			// System.out.println("\n");
			
			// switch (in) {
				// case -1: 	exit = true; break;
				
				// case 1:		if (!konto.transaksjon(input.getDouble("Størelse på transaksjon: ", -9999999, 9999999))) {
								// exit = true;
								// System.out.println("Konto er nå tom, alt slettes!");
							// }
							// break;
				
				// case 2:		konto.setSaldo(input.getDouble("Saldoen skal settes til: ", 0, 9999999)); break;
			// }
		// }
	}
}