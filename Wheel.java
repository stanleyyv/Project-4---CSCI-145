import java.util.Random;
import java.util.Scanner;

// Class Wheel for CSCI 145 Project 4 Fall 17
// Modified by: Stanley Vuong

//************************************************************************
//   Class Wheel represents a roulette wheel and its operations.  Its
//   data and methods are static because there is only one wheel.
//************************************************************************
class Wheel
{
    // public name constants -- accessible to others
    public int BLACK     =  0;			// even numbers
    public int RED       =  1;			// odd numbers
    public int GREEN     =  2;			// 00 OR 0
    public int NUMBER    =  3;			// number bet
    public final int MIN_NUM   =  1;			// smallest number to bet
    public final int MAX_NUM   = 36;			// largest number to bet   
    public int minBet;
    public int maxBet;
    
    private Chips houseChips;
    private int cash;
    private int balance;
    private String model;
    private int initialBalance;

    // private name constants -- internal use only
    private final int MAX_POSITIONS = 38;	// number of positions on wheel	//CAN CHANGE THIS TO A LOWER NUMBER FOR NUMBERBET TESTING
    private final int NUMBER_PAYOFF = 35;	// payoff for number bet
    private final int COLOR_PAYOFF  = 2;	// payoff for color bet

    // private variables -- internal use only
    private int ballPosition;				// 00, 0, 1 .. 36
    private int color;						// GREEN, RED, OR BLACK
    private Player[] table = new Player[5];
    

    //====================================================================
    //  The Player constructor sets up  name and initial available money.
    //=====================================================================
    public Wheel (String model, int minBet, int maxBet, Chips houseChips)
    {
    	this.model = model; 
    	this.minBet = minBet;
    	this.maxBet = maxBet;
    	cash = 0;
    	this.houseChips = houseChips;
    	balance = houseChips.cashoutValue();
      	initialBalance = houseChips.cashoutValue();
   	}

    //=====================================================================
    //  Presents welcome message
    //=====================================================================
    public void welcomeMessage() //Also loads input files
    {
    	System.out.println("----------------------------------------------------------------");
      	System.out.println(" _____                 _          _    _\n" + 
      			"|  _  \\               | |        | |  | |\n" + 
      			"| | \\ |   ___   _   _ | |  ____  | |_ | |_   ____\n" + 
      			"| |_/ /  / _ \\ | | | || | / __ \\ |  _||  _| / __ \\\n" + 
      			"| __ \\  / / \\ \\| | | || |/ _____|| |  | |  / _____|\n" + 
      			"| | \\ \\ \\ \\_/ /| \\_| || |\\ \\___  | |  | |  \\ \\____\n" + 
      			"|_|  \\_\\ \\___/  \\____||_| \\____/  \\_\\  \\_\\  \\____/");
      	System.out.println("Project 4 - CSCI 145 - Stanley Vuong");
      	System.out.println("----------------------------------------------------------------");
      	System.out.println("You can place a bet on black, red, or a number.");
      	System.out.println("A color bet is paid " + COLOR_PAYOFF + " the bet amount.");
      	System.out.println("A number bet is paid " + NUMBER_PAYOFF + " the bet amount.");
      	System.out.println("You can bet on a number from " + MIN_NUM + " to " + MAX_NUM + ".");
      	System.out.println("Gamble responsibly.  Have fun and good luck!\n");
    }

    //MAIN MENU
    public int mainMenu() {
    	Scanner scan = new Scanner(System.in);
    	boolean invalid = true;
    	int choice = 0;
    	while (invalid) {
	    	System.out.println("===================\nMain Menu\n");
	    	System.out.println("1. Select a game");
	    	System.out.println("2. Add a new player to the list");
	    	System.out.println("3. Quit");
	    	System.out.print("Please choose an option. --> ");
	    	choice = scan.nextInt();
	    	if (choice < 1 || choice > 3) {
	    		System.out.println("INVALID OPTION, PLEASE TRY AGAIN");
	    	}
	    	else {
	    		invalid = false; // Breaks loop once valid option
	    	}
    	}
    	return choice;
    }
    //GAME MENU
    
    public int gameMenu() {
    	Scanner scan = new Scanner(System.in);
    	boolean invalid = true;
    	int choice = 0;
    	while (invalid) {
	    	System.out.println("===================\nGame Menu [Game: " + model + " ($"+minBet+" - $"+maxBet+" Betting Table)]\n");
	    	System.out.println("1. Add a player to the game");
	    	System.out.println("2. Play one round");
	    	System.out.println("3. Return to the main menu");
	    	System.out.print("Please choose an option. --> ");
	    	choice = scan.nextInt();
	    	if (choice < 1 || choice > 3) {
	    		System.out.println("INVALID OPTION, PLEASE TRY AGAIN");
	    	}
	    	else {
	    		invalid = false; // Breaks loop once valid option
	    	}
    	}
    	return choice;
    }
    
    //Give seat to player in the table
    public void giveSeatTo(Player p) {
    	boolean seatTaken = false;
    	for (int seat = 1; seat <= table.length; seat++) { //seats are 1, 2, 3, 4, or 5
			if (table[seat-1] == null) {
				table[seat-1] = p;
				System.out.println("\t" + p.getName() + " has taken seat #" + seat + ".");
				seatTaken = true;
				break; //player takes first available seat only
			}
    	}
    	if (!seatTaken) {
    		System.out.println("The current table is full.");
    	}
    }
    
    public Player[] getTable() {
    	return table;
    }
    
    public int getTablePlayerCount() {
    	int playersOnTable = 0;
		for (int seat = 1; seat <= table.length; seat++) {
			if (table[seat-1]!=null) {
				playersOnTable++;
			}
		}
		return playersOnTable;
    }
    
    public String getModel() {
    	return model;
    }
    
    public void addCash(int cash) {
    	this.cash += cash;
    }
    
    public void recalcBalance() {
    	balance = houseChips.cashoutValue() + cash;
    }
    
    //=====================================================================
    //  Presents betting options
    //=====================================================================
    public int betOptions(String name)
    {
    	Scanner scan = new Scanner(System.in);
    	boolean invalid = true;
    	int choice = 0;
    	while (invalid) {
	      	System.out.println("Betting Options:");
	      	System.out.println("    1. Bet on black (even numbers)");
	      	System.out.println("    2. Bet on red (odd numbers)");
	      	System.out.println("    3. Bet on a number between " + MIN_NUM +
	      			" and " + MAX_NUM);
	      	System.out.print("Please enter an option. --> ");
	    	choice = scan.nextInt();
	    	if (choice < 1 || choice > 3) {
	    		System.out.println("INVALID OPTION, PLEASE TRY AGAIN");
	    	}
	    	else {
	    		invalid = false; // Breaks loop once valid option
	    	}
		}
		return choice;
    }
    
    //=====================================================================
    //  Spins the wheel
    //=====================================================================
    public void spin() {
    	int playersOnTable = 0;
    	for (int seat = 1; seat <= table.length; seat++) {
    		if (table[seat-1] != null) {
    			playersOnTable++;
    		}
	    }
    	if (playersOnTable > 0) {
	    	Random rand = new Random();
	    	ballPosition = rand.nextInt(MAX_POSITIONS);// Position 37 is "00"
	    	String ballPos;
	    	if (ballPosition % 2 == 0 && ballPosition != 0) {
	    		color = BLACK;
	    		ballPos = (""+ballPosition);
	    	}
	    	else if (ballPosition % 2 == 1 && ballPosition !=MAX_POSITIONS-1) {
	    		color = RED;
	    		ballPos = (""+ballPosition);
	    	}
	    	else if (ballPosition == 0) {
	    		color = GREEN;
	    		ballPos = (""+ballPosition);
	    	}
	    	else {
	    		color = GREEN;//This for is position 37
	    		ballPos = ("00");
	    	}
	    	String colorLanded;
	    	switch (color) {
	    		case 0:
	    			colorLanded = "Black";
	    			break;
	    		case 1:
	    			colorLanded = "Red";
	    			break;
	    		default:
	    			colorLanded = "Green";
	    	}
	    	System.out.println("===================\n*** Color: " + colorLanded + ", Number: " + ballPos);
	    	getSpin();
    	}	
    	else {
    		System.out.println("*** THERE ARE NO PLAYERS AT THE TABLE. ***");
    		System.out.println("*** Please add players to allow dealer to spin the wheel. ***");
    	}
    }
    
    public void getSpin() {    	
    		String c = "";
    		int a = color;
    		int p = ballPosition;
    		if(a == 0) {
    			c = "Black";
    		} else if(a == 1) {
    			c = "Red";
    		} else if(a == 2) {
    			c = "Green";
    		}
    		Transactions.setResult(c + " " + p);
    }
    
    
    public void payTable() {
    		for (int seat = 1; seat <= table.length; seat++) {
    			table[seat].payment(this);
    		}
    }
    
	public static Chips convertToChips(int cashValue) {
		Chips obj = new Chips(0,0,0,0);
		
		int remainder = cashValue;
		
		int chip100 = remainder / 100;
		remainder = remainder % 100;
		
		int chip25 = remainder / 25;
		remainder = remainder % 25;
		
		int chip5 = remainder / 5;
		remainder = remainder % 5;
		
		int chip1 = remainder;
		
		obj.getChipArray()[0] = chip100;
		obj.getChipArray()[1] = chip25;
		obj.getChipArray()[2] = chip5;
		obj.getChipArray()[3] = chip1;
		
		return obj;
	}
	
    public Chips[] payoff(Chips betAmount, int[] betType, int betsArr[]) {//NOW IT'S AN ARRAY
    	Chips[] payArr = new Chips[betsArr.length];
    	for (int currentBet = 0; currentBet < betsArr.length; currentBet++) {
    	   	Chips pay = new Chips(0,0,0,0); //default chips is 0,0,0,0
	    	if(betType[currentBet] == 3) {
    			if(betsArr[currentBet] == ballPosition) {
    				pay.addChipsOf(convertToChips(betAmount.cashoutValue()*NUMBER_PAYOFF));
	    		}
	    	}
			if ((betType[currentBet] == 1 && color == BLACK) || (betType[currentBet] == 2 && color == RED)){
				pay.addChipsOf(convertToChips(betAmount.cashoutValue()*COLOR_PAYOFF));
			}
			payArr[currentBet] = pay;
			this.getChips().subChipsOf(pay); // wheel will pay here
    	}
		return payArr; 
    }
    
    public Chips getChips() {
    	return houseChips;
    }
    
    public String toString() {
		String s = "";
		s = model;
		s += "\nEnding Balance: $" + balance + "\n    Cash:       $";
		s += cash;
		s += "\n    $100 chips: " + houseChips.getChipArray()[0];
		s += "\n    $25 chips:  " + houseChips.getChipArray()[1];
		s += "\n    $5 chips:   " + houseChips.getChipArray()[2];
		s += "\n    $1 chips:   " + houseChips.getChipArray()[3];
		//Changed roudLosingAmount to (initial-final)
		recalcBalance();
		if ((initialBalance - balance) >= 0) {
			s += "\n\nLOSING amount for this session: $" + (initialBalance-balance) + "\n";
		}
		else {
			s += "\n\nWINNING amount for this session: $" + (balance-initialBalance) + "\n";
		}
		return s;
    }    
}


