import java.util.Scanner;

public class Chips {
	private int[] chipCount = new int[4];
	
	public Chips(int chip100, int chip25, int chip5, int chip1) {
		chipCount[0] = chip100;
		chipCount[1] = chip25;
		chipCount[2] = chip5;
		chipCount[3] = chip1;
	}
	
	public void addChipsOf(Chips these) {
		for (int i = 0; i < chipCount.length; i++) {
			chipCount[i] += these.getChipArray()[i];
		}
	}
	
	public void subChipsOf(Chips these) {
		for (int i = 0; i < chipCount.length; i++) {
			chipCount[i] -= these.getChipArray()[i];
		}
	}
	
	public int[] getChipArray() {
		return chipCount;
	}
	
	public int cashoutValue() {
		int cash;
		cash = chipCount[0]*100;
		cash += chipCount[1]*25;
		cash += chipCount[2]*5;
		cash += chipCount[3];
		return cash;
	}

	public void CashToChips(Wheel w, int seat) { //Cash to chips exchange
		Scanner scan = new Scanner(System.in);
		boolean invalid = true;
		int amount = 0;
		while (invalid) { //Validation loop to make sure multiple of 100
			System.out.print("How much cash to exchange? (0 or multiples of 100) --> ");
			amount = scan.nextInt();
	    	if (amount % 100 != 0 || amount > w.getTable()[seat-1].getMoney()) { //checks if person has enough money
	    		System.out.println("INVALID OPTION, PLEASE TRY AGAIN.");
	    		if (amount > w.getTable()[seat-1].getMoney()) {
	    			System.out.println(w.getTable()[seat-1].getName() + " only has $" + w.getTable()[seat-1].getMoney());
	    		}
	    	}
	    	else {
	    		invalid = false; // Breaks loop once valid input
	    	}
		}
		
		//Player pays house for chips
		w.getTable()[seat-1].giveMoney(amount);
		//House receives cash for selling chips
		w.addCash(amount);
		
		int multipleOf100 = amount / 100;
		
		int temp100 = 0;
		int temp25 = 0;
		int temp5 = 0;
		int temp1 = 0;
		
		//If cash is 0, the following loop is ignored
		//Will always get same pattern
		for(int i = 1; i <= multipleOf100; i++) {		
			chipCount[1] += 2;
			temp25 += 2;
			w.getChips().getChipArray()[1] -= 2;
			chipCount[2] += 7;
			temp5 += 7;
			w.getChips().getChipArray()[2] -= 7;
			chipCount[3] += 15;
			temp1 += 15;
			w.getChips().getChipArray()[3] -= 15;
		}
		if (temp100 > 0 || temp25 > 0 || temp5 > 0 || temp1 > 0) {
			System.out.print(w.getTable()[seat-1].getName() + " exchanges $" + amount + " for ");
			if (temp100 > 0) {
				System.out.print(temp100 + " $100-chip");
			}
			if (temp100 > 0 && temp25 > 0) {
				System.out.print(", ");
			}
			if (temp25 > 0 || temp5 > 0 || temp1 > 0) {
				System.out.println(temp25 + " $25-chip, " + temp5 + " $5-chip, " + temp1 + " $1-chip");
			}
		}
		else {
			System.out.println("\t" + w.getTable()[seat-1].getName() + " canceled their exchange.");
		}
	}
	
	public String toString() {
		String chipString = "";
		for (int i = 0; i < 4; i++) {
			if (chipCount[i]<10)
				chipString += " ";
			if (chipCount[i]<100)
				chipString += " ";
			if (chipCount[i]<1000)
				chipString += " ";
			if (chipCount[i]<10000)
				chipString += " ";
			chipString += " " + chipCount[i];
		}
		return chipString;
	}
}


