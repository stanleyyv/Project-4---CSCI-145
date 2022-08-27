import java.io.*;
import java.util.Scanner;

//************************************************************************
//   Class Roulette contains the main driver for a roulette betting game.
//************************************************************************
class Menu
{
	static int machine = 0;
    //=====================================================================
    //  Contains the main processing loop for the roulette game.
    //=====================================================================
	public static void main(String[] args) {

		//ACTUAL GAME
		Scanner scan = new Scanner(System.in);
		Queue line = new Queue();
		Wheel[] wheelsArr = setUpWheels(); //Scans in info from games.txt
		wheelsArr[machine].welcomeMessage(); 
		PrintWriter outFile = setUpTransactionsOutput();
		int mainMenuChoice = wheelsArr[machine].mainMenu(); //Input choice for MainMenu
		while (mainMenuChoice != 3) { //If 3 (Quit), loop ends or never starts
			if (mainMenuChoice == 1) { //If 1 (Select a game)
				printAvailableGames(wheelsArr);
				String game = selectGame(wheelsArr); //selectGame() internally checks validity
				int gameChoice = 0;
				while (gameChoice != 3 && !game.equals("")) { //GameMenu start. game must have name
					gameChoice = wheelsArr[machine].gameMenu();
					if (gameChoice == 1) { //"Add a player to the game"
						if(line.getSize() < 1) {
							System.out.print("Queue is empty. Create a new player? (y/n) --> ");
							char choice = scan.next().charAt(0);
							if(choice == 'y' || choice == 'Y') { 
								line.createCustomPlayer();
							}
						}
						if (line.getSize() > 0) {
							wheelsArr[machine].giveSeatTo(line.getFrontPlayer());
						}
					}
					if (gameChoice == 2) { //If 2 (Play one round)
						for (int seat = 1; seat <= wheelsArr[machine].getTable().length; seat++) { //bet across table
							if(wheelsArr[machine].getTable()[seat-1] != null) {
								//Prints Current turn: and Player object through its toString()
								System.out.println("===================\nCurrent turn: \n" + wheelsArr[machine].getTable()[seat-1]);
								char c = 'z';
								while (c != 'y' && c != 'Y' && c != 'n' && c !='N') {
									System.out.print("Would you like to exchange cash for chips (y/n)? --> ");
									c = scan.next().charAt(0);
									if (c != 'y' && c != 'Y' && c != 'n' && c !='N') {
										System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
									}
								}
								if(c == 'y' || c == 'Y') { //Option 
									wheelsArr[machine].getTable()[seat-1].getChips().CashToChips(wheelsArr[machine], seat);
								} 
							}
							
							if (wheelsArr[machine].getTable()[seat-1]!=null) {
								if (wheelsArr[machine].getTable()[seat-1].ableToPlay(wheelsArr[machine])) {
									wheelsArr[machine].getTable()[seat-1].makeBet(scan, wheelsArr[machine]);
								}
								else {
									//Kicks player off table if unable to play
									wheelsArr[machine].getTable()[seat-1] = null;
								}
							}
						}
						wheelsArr[machine].spin();
						for (int seat = 1; seat <= wheelsArr[machine].getTable().length; seat++) {//pay across table
							if (wheelsArr[machine].getTable()[seat-1]!=null) {
								wheelsArr[machine].getTable()[seat-1].payment(wheelsArr[machine]);
							}
						}
						for (int seat = 1; seat <= wheelsArr[machine].getTable().length; seat++) {//play again across table
							if (wheelsArr[machine].getTable()[seat-1]!=null) {
								//Print to file
								Transactions.printTransaction(outFile);
								wheelsArr[machine].getTable()[seat-1].playAgain(scan, wheelsArr[machine], seat);
							}
						}
						Transactions.round++;//Increment round
					}//end outer if
				}//end while
			}
			if (mainMenuChoice == 2) { //If 2 (Add a new player to the list)
				line.createCustomPlayer();
			}
			mainMenuChoice = wheelsArr[machine].mainMenu(); //Allow player enter new MainMenu choice
		}
		System.out.println("Generating report ...");
		System.out.println("Shutting down all games.");
		for (int i = 0; i < wheelsArr.length; i++) {
			System.out.println(wheelsArr[i]);
		}
		outFile.close(); //MUST CLOSE FILE TO CLEAR BUFFER TO LOAD INTO OUTPUT FILE
	}//end main
	
	public static Wheel[] setUpWheels() { 
		Scanner decideFile = new Scanner(System.in);
		//Scans info from games.txt and creates an array of Wheel objects (different games)
		Wheel[] w = null;
		String fileName = "games.txt";
		boolean invalid = true;
		while (invalid) {
			System.out.println("Which file do you want to run?");
			System.out.println("    1. games.txt (Basic)");
			System.out.println("    2. gamesMulModels.txt (Extra Credit)");
			System.out.print("Choose (1 or 2). --> ");
			int choice = decideFile.nextInt();
			if (choice == 1) {
				invalid = false;
			}
			else if (choice == 2) {
				fileName = "gamesMulModels.txt"; //can choose to upload extra credit file
				invalid = false;
			}
			else {
				System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
			}
		}
		try {
			File gameFile = new File(fileName);
			Scanner scan = new Scanner(gameFile);
			int maxMachines = 5;
			int totalMachines = 0;
			
			w = new Wheel[maxMachines];
			while (scan.hasNext()) {
				
				String model = scan.next();		
				int countOfMachines = scan.nextInt();
				if (totalMachines > maxMachines) { //Caps amount of machines to 5
					totalMachines = 5;
				}
				System.out.println("Number of machines (Model "+model+"): " + countOfMachines);
				int modelNum = 1;
				for (int i = totalMachines; i < (countOfMachines+totalMachines); i++) {
					String modelWithNumber = model + modelNum;
					modelNum++;
					int min = scan.nextInt();
					int max = scan.nextInt();
					Chips houseChips = new Chips(scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
					w[i] = new Wheel(modelWithNumber, min, max, houseChips);
				}
				totalMachines += countOfMachines;
			}
			//If there isn't a maximum amount of machines (less than 5).
			if (totalMachines < maxMachines) {
				Wheel[] sizedDown = new Wheel[totalMachines];
				for (int i = 0; i < totalMachines; i++) {
					sizedDown[i] = w[i];
				}
				//Replace max size wheel array with sized down array
				w = sizedDown;
				//Otherwise, the array is assumed max out with machines
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " + fileName + " was not found.");
		}
		return w;
	}
	
	public static void printAvailableGames(Wheel[] w) {
		System.out.println("===================\nAvailable games: ");
    	for (int i = 0; i<w.length; i++) {
    		System.out.println("    "+ w[i].getModel() + " (Betting range: " + (w[i].minBet < 10? " $"+w[i].minBet : "$"+w[i].minBet ) + (w[i].maxBet < 100 ? (" -  $" + w[i].maxBet): (" - $" + w[i].maxBet)) + ")"); 
    	}
	}
	
	public static String selectGame(Wheel[] w) {
		Scanner scan = new Scanner(System.in);
		boolean invalid = true;
		String game ="";
		if (w.length < 1) {
			System.out.println("There are no games available.");
			return game;
		}
		while (invalid) {
			System.out.print("Select a game. --> ");
			game = scan.next();
			for (int i = 0; i < w.length; i++) {
				if (game.equals(w[i].getModel())) {
					machine = i;
					invalid = false;
				}
			}
			if (invalid) {
				System.out.println("\nINVALID OPTION, PLEASE TRY AGAIN.");
			}
		}
		return game;
	}
	
	public static PrintWriter setUpTransactionsOutput() {
		String outputName = "transactions.txt";
		try {
			PrintWriter outFile = new PrintWriter(outputName);
			return outFile;
		} 
		catch (FileNotFoundException exeption) {
			System.out.println("There was a problem with the output.");
		}
		return null; //HOPEFULLY THIS NEVER HAPPENS
	}
}




