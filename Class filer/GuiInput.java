import javax.swing.JOptionPane.*;

class GuiInput {
	public GuiInput() {}
	
	public int getInt(String dialog, int minimum, int maksimum) {
		
		String input = "";
		int inputTall = 0;
		boolean noExit = true;
		
		while (noExit) {
			input = showInputDialog(dialog);
			
			try {
    			inputTall = input.nextInt();
			} catch (Exception e) {
				System.out.println("ikke int");
				continue;
			}
			
			if (inputTall < minimum) {
				System.out.println("Tallet kan ikke vaere lavere en " + minimum + "!");
			} else if (inputTall > maksimum) {
				System.out.println("Tallet kan ikke vaere hoyere en " + maksimum + "!");
			} else {
				noExit = false;
			}
		}
		return inputTall;
	}
	
	public boolean getYesNo(String dialog) { //yes = true
		return (0 == showConfirmDialog(null, dialog, null, YES_NO_OPTION));
	}
}

