import java.util.Random;
import java.lang.Math.*;
import java.util.Scanner;

class RandomGen {
	int[] antall;

	public RandomGen(int arraySize) {
		java.util.Random random = new java.util.Random();
		this.antall = new int[arraySize];

		for (int i = 0; i < antall.length; i++) {
			antall[i] = random.nextInt(10);
		}
	}

	public int[] getArrayNum() {
		int[] numberCount = new int[10];

		for (int a = 0; a < 10; a++) {
			for (int i = 0; i < antall.length; i++) {
				if (a == antall[i]) {
					numberCount[a]++;
				}
			}
		}
		return numberCount;
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

class RandomTest {

	public static void printNumbers(int[] array) {

		for (int i = 0; i < 10; i++) {
			System.out.println("Antall " + i + " er " + array[i]);
		}
	}

	public static void printStars(int[] array, int antallSiffer) {

		String outString = "";
		int maks = 0;
		int eksponent = 0;
		int stjerneForhold = 0;
		String stjerneOffset = "";

		for (int j = 0; j < array.length; j++ ) {
			if (maks < array[j]) {
				maks = array[j];
			}
		}

		while (maks >= Math.pow(10,eksponent)) eksponent++;

		System.out.println("\nEn stjerne er det samme som " + Math.pow(10, eksponent - antallSiffer) + " av tallet.");

		for (int i = 0; i < 10; i++) {

			outString = "";
			stjerneOffset = "";
			stjerneForhold = (int) Math.round(array[i]/Math.pow(10, eksponent - antallSiffer));

			for (int k = 1; k <= stjerneForhold; k++ ) {
				outString += "*";
			}

			if (Math.round(array[i]) < Math.pow(10, eksponent -1)) stjerneOffset = " ";

			System.out.println(+ i + "   " + array[i] + stjerneOffset + "   " + outString);
		}
	}

	public static void main(String[] args) {

		Input input = new Input();

		int tallLest = 0;

		while (true) {
			tallLest = input.getInt("\nHvor mange tilfeldige tall (mellom 0 og 9)vill du generere? \n(-1 for a avslutte)", -1, 1000000000);

			if (tallLest == -1) break;

			RandomGen random = new RandomGen(tallLest);

			printNumbers(random.getArrayNum());
			printStars(random.getArrayNum(), 2);

		}
	}
}