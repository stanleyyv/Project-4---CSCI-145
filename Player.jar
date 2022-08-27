 
import java.util.*;

//************************************************************************
//   Class Player represents one roulette player.
//************************************************************************
class Player
{
	private int money;
	private Chips bet;
	protected String name = "Player ";
	private int[] betTypeArr; //added instance
	private int[] betsArr = new int[1];  //default size of 1
	
	private int startMoney = 0;//track original money brought to casino
	private Chips chips;
	protected int totalBets = 0; //added to avoid overriding makeBet() method
	protected int betCount = 0;
	private static int playerCount = 0; //static to not reset for each object made

    //=====================================================================
    //  The Player constructor sets up  name and initial available money.
    //=====================================================================
    public Player (int initialMoney, Chips chips)
    {
		playerCount++;
		startMoney = initialMoney;
      	money = initialMoney;
      	this.chips = chips;
      	name = ("Player " + playerCount);
   	} // constructor Player


    //=====================================================================
    //  Returns this player's name.
    //=====================================================================
    public String getName()
    {
      	return name;
    }


    //=====================================================================
    //  Returns this player's current available money.
    //=====================================================================
    public int getMoney()
    {
      	return money;
    }
    
    public void giveMoney(int cash) {
    	money -= cash;
    }

    public Chips getChips() {
    	return chips;
    }
    
    public boolean ableToPlay(Wheel wheel) {
    	if (chips.cashoutValue() < wheel.minBet) {//not enough chips
			System.out.println(name + " does not have enough chips.");
			if (money < 100 || money < wheel.minBet) { //Not enough chips, not enough money
				System.out.println("... and not enough money.");
				System.out.println("Gambling addiction helpline: 1-800-522-4700");
				//KICK OFF THE TABLE
				System.out.println("\t"+name+" has been removed from the table.");
				return false;
			}
			
			//Automatically exchange the minimum
			int minExchangeCash = 100;
			Chips automaticExchange = new Chips(0*minExchangeCash/100,2*minExchangeCash/100,7*minExchangeCash/100,15*minExchangeCash/100);
			chips.addChipsOf(automaticExchange);
  			money -= minExchangeCash;
  			wheel.addCash(minExchangeCash);
  			int temp100 = automaticExchange.getChipArray()[0];
  			int temp25 = automaticExchange.getChipArray()[1];
  			int temp5 = automaticExchange.getChipArray()[2];
  			int temp1 = automaticExchange.getChipArray()[3];
  			if (temp100 > 0 || temp25 > 0 || temp5 > 0 || temp1 > 0) {
  				System.out.print(name + " exchanges $" + minExchangeCash + " for ");
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
	  	}
    	//Breakdown larger chips to continue playing game
    	if ((chips.getChipArray()[3] + chips.getChipArray()[2]*5) < wheel.minBet) {
    		if (chips.getChipArray()[0] > 0) {
    			Chips exchange = new Chips(0,2,7,15);
    			chips.getChipArray()[0]--;
    			chips.addChipsOf(exchange);
    			System.out.println("The dealer notices you need to breakdown your chips.");
    			System.out.println(name + " exchanges 1 $100-chip for 2 $25-chip, 7 $5-chip, 15 $1-chip.");
    		}
    		else if (chips.getChipArray()[1] > 0) {
    			Chips exchange = new Chips(0,0,2,15);
    			chips.getChipArray()[1]--;
    			chips.addChipsOf(exchange);
    			System.out.println("The dealer notices you need to breakdown your chips.");
    			System.out.println(name + " exchanges 1 $25-chip for 2 $5-chip, 15 $1-chip.");
    		}
    	}
		if (chips.getChipArray()[2] > 0 && chips.getChipArray()[3] < 5) {
			Chips exchange = new Chips(0,0,0,5);
			chips.getChipArray()[3]--;
			chips.addChipsOf(exchange);
			System.out.println("The dealer notices you need to breakdown your chips.");
			System.out.println(name + " exchanges 1 $5-chip for 5 $1-chip.");
		}
		System.out.println(this);
    	return true;
    }
    //=====================================================================
    //  Prompts the user and reads betting information.
    //=====================================================================
    public void makeBet(Scanner scan, Wheel wheel)
    {
    	int numOfBets = 1;
    	boolean invalid = true;
    	while(invalid) {
    		System.out.print("How many bets to be made? (up to three) --> ");
    		numOfBets = scan.nextInt();
    		if (numOfBets < 1 || numOfBets > 3) {
    			System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
    		}
    		else if (chips.cashoutValue()/numOfBets < wheel.minBet) {
    			System.out.println("Sorry, you do not have enough chips to make that many bets.");
    			System.out.println("You can make up to " + (chips.cashoutValue()/wheel.minBet > 3? " 3": chips.cashoutValue()/wheel.minBet) + " bets.");
    		}
    		else {
    			invalid = false;
    		}
    	}
    	betsArr = new int[numOfBets]; //Parallel arrays for betType
    	betTypeArr = new int[numOfBets];
    	
    	Chips bettingChips = new Chips(0,0,0,0);
    	invalid = true;
		
    	for (int currentBet = 0; currentBet < betsArr.length; currentBet++) {
			//Choosing to bet on red, black, or a number
			boolean valid = true;
			System.out.println("[Bet " + (currentBet+1) + " of " + betsArr.length +"]");
			
			betTypeArr[currentBet] = wheel.betOptions(name); //BetType is stored here
			invalid = true;
			if (betTypeArr[currentBet] == 3 && chips.cashoutValue() > 0) {
	    		do { //Public MIN_NUM & MAX_NUM
	    			System.out.print("Please choose a number between " + wheel.MIN_NUM + " and " + wheel.MAX_NUM + " --> ");
	    			int betOnNumber = scan.nextInt();
	    			valid = true;
		    		if (betOnNumber < 1 || betOnNumber > 36) {
		    			System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
		    			valid = false;
		    		}
	    			for (int j = 0; j < betsArr.length; j++) {
	    				if (betOnNumber == betsArr[j]) {
	    					valid = false;
	    				}
	    			}
	    			if (!valid) {
	    				if (betOnNumber > 1 && betOnNumber < 36) { //In range, but chosen already
	    					System.out.println("This number has already been chosen, please choose another.");
	    				}
	    				
	    			}
	    			betsArr[currentBet] = betOnNumber;
	    		} while (!valid);
	    	}
	    	
			//Choosing the bet amount
			do {
				invalid = true;
				int chip100 = 0, chip25 = 0, chip5 = 0, chip1 = 0;
				int maxBetting = chips.cashoutValue()/(betsArr.length-currentBet);
				System.out.println("(Betting limit $"+wheel.minBet+" - $"+ wheel.maxBet+")");
				while(invalid) {
					//We will condense the four invalid2 while loops eventually
					boolean invalid2 = true;
					while(invalid2) {
						System.out.print("\tHow many $100 chips would you like to bet? --> ");
						chip100 = scan.nextInt();
						if (chips.getChipArray()[0] < chip100 || chip100 < 0) {
							invalid2 = true;
							System.out.println("Sorry, you don't have enough chips to bet that many.");
						}
						else {
							invalid2 = false;
						}
					}
					invalid2 = true;
					while(invalid2) {
						System.out.print("\tHow many $25 chips would you like to bet? --> ");
	    				chip25 = scan.nextInt();
	    				if (chips.getChipArray()[1] < chip25 || chip25 < 0) {
							invalid2 = true;
							System.out.println("Sorry, you don't have enough chips to bet that many.");
						} 
	    				else {
							invalid2 = false;
	    				}
					}
					invalid2 = true;
					while(invalid2) {
						System.out.print("\tHow many $5 chips would you like to bet? --> ");
	    				chip5 = scan.nextInt();
	    				if (chips.getChipArray()[2] < chip5 || chip5 < 0) {
							invalid2 = true;
							System.out.println("Sorry, you don't have enough chips to bet that many.");
						}
	    				else {
							invalid2 = false;
	    				}
					}
					invalid2 = true;
					while(invalid2) {
						System.out.print("\tHow many $1 chips would you like to bet? --> ");
	    				chip1 = scan.nextInt();
	    				if (chips.getChipArray()[3] < chip1 || chip1 < 0) {
							invalid2 = true;
							System.out.println("Sorry, you don't have enough chips to bet that many.");
						} 
	    				else {
							invalid2 = false;
	    				}
					}
		    		
					bettingChips = new Chips(chip100,chip25,chip5,chip1);
							
					if (bettingChips.cashoutValue() < wheel.minBet || bettingChips.cashoutValue() > wheel.maxBet) {
						System.out.println("You bet $" + bettingChips.cashoutValue() + ". Please bet within $"+wheel.minBet+" to $"+wheel.maxBet+".");
					}
					else if (bettingChips.cashoutValue() > maxBetting) {
						System.out.println("With our math, you can't bet more than $" + maxBetting + " at this moment. Try again.");
					}
					else {
						invalid = false;
						bet = bettingChips; //
						wheel.getChips().addChipsOf(bettingChips);
					}
				} //End of making betting chips object`					
				System.out.println(name + " bet a total of $" + bettingChips.cashoutValue()+".");
				if (bettingChips.cashoutValue() <= chips.cashoutValue() && bettingChips.cashoutValue() >= wheel.minBet
	  			&& bettingChips.cashoutValue() <= wheel.maxBet) {
					chips.subChipsOf(bettingChips);
					valid = true;
					totalBets += bet.cashoutValue();//for transaction
					betCount++;//for transaction
				}
				else {
					System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
					if (bet.cashoutValue() <= wheel.minBet || bet.cashoutValue() >= wheel.maxBet ) {
						System.out.println("Please bet between the table limits ($" + wheel.minBet + " to $" + wheel.maxBet + ").");
					}
					if (bet.cashoutValue() >= chips.cashoutValue()) {	
						System.out.println("Not enough money to make that bet amount.");
					}
						System.out.println (this);
					valid = false;
				}
			} while (!valid);
			System.out.println(this);
			Transactions.addToBetTrans(this, bet, betTypeArr[currentBet]);
			Transactions.transNumber++;
    	} //End of giant for loop that creates each bet from 1 player
    }

    public void payment(Wheel wheel) {
	    //Prints out the payment and adjusts the current money in pocket
    	Chips sumOfPayout = new Chips(0,0,0,0);
    	Chips[] payout = wheel.payoff(bet, betTypeArr, betsArr); //Array of bets
    	for (int i = 0; i < betsArr.length; i++) {
    		//Add to player's chips
    		this.chips.addChipsOf(payout[i]);
    		Transactions.addToPayTrans(payout[i]);
	    	sumOfPayout.addChipsOf(payout[i]);
    	}
    	wheel.recalcBalance(); // Balance is cashoutValue of chips + cash,
    	for (int j = 0; j < name.length(); j++) {
    		System.out.print(" ");
    	}
    	System.out.println("    Chips: [ $100|  $25|   $5|   $1]");
    	System.out.println(name + " : Payout: " + sumOfPayout);
    	System.out.println("--------------------");
    }

    //=====================================================================
    //  Determines if the player wants to play again.
    //=====================================================================
    public boolean playAgain(Scanner scan, Wheel wheel, int seat)
    {
      	String answer;
      	boolean invalid = true;
      	while (invalid) {
      		System.out.print (name + " : Play again? (y/n) --> ");
	      	answer = scan.next();
	      	if (answer.equals("y") || answer.equals("Y") || answer.equals("n") || answer.equals("N")) {
	      		if (answer.equals("n") || answer.equals("N")) {
	      			System.out.println("\t" + name + " has quit this game,");
	      			System.out.println(this);
	      			System.out.println("\t"+displayStatus());
	      			wheel.getTable()[seat-1] = null;
	      			return false;
	      		}
	      		else {
	      			invalid = false; //Breaks loop and allows to return true
	      		}
	      	}
	      	else {
	      		System.out.println("INVALID INPUT, PLEASE TRY AGAIN");
	      	}
      	}
      	return true;
    }  // return true means to play play again
    
    public String displayStatus() {
    	if ((money+chips.cashoutValue())<startMoney) {
    		//Loss money
    		return ("A net loss of $"+(startMoney-(money+chips.cashoutValue())));
    	}
    	else if ((money+chips.cashoutValue()>startMoney)) {
    		//Net win of money
    		return ("A net win of $" + ((money+chips.cashoutValue())-startMoney));
    	}
    	else {
    		return ("Broken even with no win nor loss of money.");
    	}
    }
    
    public String toString() {
    	//To fit in name and limit showing too much name
    	String modifiedName = name;
    	if (name.length() > 12) {
    		modifiedName = name.substring(0, 12);
    	}
    	else {
    		for (int i = 0; i < (12-name.length()); i++) {
    			modifiedName += " ";
    		}
    	}
    	//To fit in cash amount correctly
    	String moneyString = "";
    	if (money < 1000000) {
    		moneyString += " ";
    	}
    	if (money < 100000) {
    		moneyString += " ";
    	}
    	if (money < 10000) {
    		moneyString += " ";
    	}
    	if (money < 1000) {
    		moneyString += " ";
    	}
    	if (money < 100) {
    		moneyString += " ";
    	}
    	
    	if (money < 10) {
    		moneyString += " ";
    	}
    	moneyString += "$" + money;
    	
    	String s = "\tName            Cash   Chips: [ $100|  $25|   $5|   $1]\n\t";
    	s += modifiedName + moneyString + "          " + chips;
    	return s;
    }
}


