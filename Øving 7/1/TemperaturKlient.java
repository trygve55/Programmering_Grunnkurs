import java.util.Scanner;
import java.util.Random;
import java.lang.Math.*;
import java.text.DecimalFormat;

class Temperaturer {
	double[][] temperaturDatabase;
	
	Temperaturer(int days) {
		this.temperaturDatabase = new double[days][24];
		genDays();
	}
	
	private void genDays() {
		java.util.Random random = new java.util.Random();
		for (int i = 0; i < temperaturDatabase.length; i++) {
			for(int k = 0; k < temperaturDatabase[i].length; k++) {
				temperaturDatabase[i][k] = (double) -8 + Math.cos((2.0*Math.PI*(k + 5)/24)) * 10 + random.nextDouble()*40;
			}
		}
	}
	
	public double getAverageTempMonth() {
		double tempTotal = 0;
		for (int i = 0; i < temperaturDatabase.length; i++) {
			for(int k = 0; k < temperaturDatabase[i].length; k++) {
				tempTotal += temperaturDatabase[i][k];
			}
		}
		return (tempTotal / (temperaturDatabase.length * temperaturDatabase[0].length));
	}
	
	public double getAverageTempMonthTime(int time) {
		double tempTotal = 0;
		for (int i = 0; i < temperaturDatabase.length; i++) {
			tempTotal += temperaturDatabase[i][time];
		}
		return tempTotal / (temperaturDatabase.length);
	}
	
	public double getAverageTempMonthDay(int day) {
		
		double tempTotal = 0;
		for (int k = 0; k < temperaturDatabase[0].length; k++) {
			tempTotal += temperaturDatabase[day ][k];
		}
		return tempTotal / (temperaturDatabase[0].length);
	}
	
	public String getTempGroup(int start, int intervall, int antall) {
		
		String output = "";
		int[] grupper = new int[antall + 2];
		
		for (int i = 0; i < grupper.length; i++) {
			for (int k = 0; k < temperaturDatabase.length; k++) {
				if (i ==  0 && getAverageTempMonthDay(k) < start) {
					grupper[0]++;
				} else if (i ==  grupper.length - 1  && getAverageTempMonthDay(k) > start + i * intervall)  {
					grupper[(grupper.length - 1)]++;
				} else if (getAverageTempMonthDay(k) >= start + i * intervall && getAverageTempMonthDay(k) < start + (i + 1) * intervall) {
					grupper[i]++;
				}
			} 
		}
		
		output += "Det er " + grupper[0] + " temperaturer under " + start + " grader.\n";
		
		for (int j = 1;j < grupper.length - 1; j++) {
			output += "Det er " + grupper[j] + " temperaturer som er mellom " +  (start + intervall * (j - 1)) + " og " + (start + intervall * j) + " grader.\n";
		}
		
		output += "Det er " + grupper[grupper.length-1] + " temperaturer over " + (start + intervall * antall) + " grader.\n";
		
		return output;
	}
	
	public int getDaysInMonth() {
		return temperaturDatabase.length;
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
}


class TemperaturKlient {
	
	public static void main(String[] args) {
		Input input = new Input();
		
		Temperaturer temp = new Temperaturer(31);
		System.out.println("En måned med 31 dager har blitt lagt til.");
		
		int action = 0, inputVal = 0;
		boolean noExit = true;
		
		while (noExit) {
			action = input.getInt("1 - Legg inn ny måned.\n2 - Middeltemperaturen denne måneden.\n3 - Middeltemperatur på bestemt dag.\n4 - Middeltemperatur på bestemt klokkeslett.\n5 - Grupper middeltemperaturer per dag \n-1 - Avslutte. \n", -1, 5);
			
			switch(action) {
				case -1:	noExit = false;
							break;
				case  1:	temp = new Temperaturer(input.getInt("Antall dager i måneden: ", -1, 31));
							break;
				case  2:	System.out.println("Middeltemperaturen denne måneden var " + temp.getAverageTempMonth() + ".");
							break;
				case  3:	inputVal = input.getInt("Dag?", 1, temp.getDaysInMonth());
							System.out.println("Middeltemperaturen på dag " + inputVal + " i denne måneden var " + temp.getAverageTempMonthDay(inputVal) + ".");
							break;
				case  4:	inputVal = input.getInt("Time?", 0, 23);
							System.out.println("Middeltemperaturen " + inputVal + ":00 i denne måneden var " + temp.getAverageTempMonthTime(inputVal) + ".");
							break;
				case  5:	System.out.println(temp.getTempGroup(input.getInt("Hvor skal grupperingen av temeraturer starte?", -50, 80), input.getInt("Hvor store intervall skal gruppene være på?", 1, 50), input.getInt("Hvor mange tempraturgrupper skal det være?", 1, 20)));
							break;
				
			}
			action = 0;
			inputVal = 0;
		}
	}
}