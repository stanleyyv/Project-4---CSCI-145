import java.util.Scanner;

public class VIP extends Player {
	//Add new instances
	protected double cashback = 0.05;
	protected String id;
	
	public VIP(int initialMoney, Chips chips, String playerName, String id) {
		super(initialMoney, chips);
		name = playerName;
		this.id = id;
	}
	//Additional Methods
	public String getId() {
		return id;
	}
	
	public int getCashback() {
		return (int)(0.05*totalBets + 0.5);
	}
	
	//Overridden method
    public boolean playAgain(Scanner scan, Wheel wheel, int seat)
    {
      	String answer;
      	boolean invalid = true;
      	while (invalid) {
      		System.out.print (name + " : Play again? (y/n) --> ");
	      	answer = scan.next();
	      	if (answer.equals("y") || answer.equals("Y") || answer.equals("n") || answer.equals("N")) {
	      		if (answer.equals("n") || answer.equals("N")) {
	      			System.out.print("\t" + name + " has quit this game, with a bonus of $");
	      			System.out.println(getCashback() + ".");
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
    }  // return true means to play again
}

