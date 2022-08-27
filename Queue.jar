import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class  Queue {
	ArrayList<Player> queueList = new ArrayList<Player>();
	
	public Queue() {
		String fileName = "players.txt";
		try {
			File playerFile = new File(fileName);
			Scanner scan = new Scanner(playerFile);
			while (scan.hasNext()) {
				int playerType = scan.nextInt();
				int money = scan.nextInt();
				Chips chips = new Chips(0,0,0,0); // blank chips means no chips.
				String id = "";
				String name = "";
				if (playerType == 1 || playerType == 2) {
					id = scan.next();
					name = scan.next() + " " + scan.next();
				}
				//Adds player into line
				createNewPlayer(playerType, money, chips, name, id);
			}
		}
		catch (FileNotFoundException e) {
			System.out.println("The file " + fileName + " was not found.");
		}
	}
	
	public Player getFrontPlayer() { //RENAMED, Returns front player and removes from top of queue
		Player temp = null;
		Scanner scan = new Scanner(System.in);
		boolean invalid = true;
		char choice;
		do {
			if (queueList.size() == 0) { //Empty queueList must be checked.
				System.out.println("There are no more players in the queue. Please create a new player.");
				createCustomPlayer();
				temp = queueList.get(0);
				queueList.remove(0); //removes from queue because they're automatically added in game
				return temp;
			}
			System.out.print("\tIs '"+ queueList.get(0).getName() + "' still here? (y/n) --> ");
			choice = scan.next().charAt(0);
			if (choice == 'y' || choice == 'Y') {
				temp = queueList.get(0);
				queueList.remove(0); //removes first person from queue
				invalid = false;
			}
			else if (choice == 'n' || choice == 'N') {
				queueList.remove(0);
			}
			else {
				System.out.println("INVALID INPUT, PLEASE TRY AGAIN.");
			}
		} while (invalid);
		return temp;
	}
	
	public int getSize() { //ADDED TO ACCESS SIZE
		return queueList.size();
	}
	
	public String toString() {
		String result = "";
		for (int i = 0; i < queueList.size(); i++) {
			result += queueList.get(i) + "\n";
		}
		return result;
	}
	
	public void createCustomPlayer() {
		Scanner scan = new Scanner(System.in);
		boolean invalid = true;
    	int choice = 0;
    	while (invalid) {
    		System.out.println("===================\nWhat kind of Player do you want to add?\n");
	    	System.out.println("1. Regular Player");
	    	System.out.println("2. VIP");
	    	System.out.println("3. Super VIP");
	    	System.out.print("Please choose an option. --> ");
	    	choice = scan.nextInt();
    		if (choice < 1 || choice > 3) {
	    		System.out.println("INVALID OPTION, PLEASE TRY AGAIN");
	    	}
	    	else {
	    		invalid = false; // Breaks loop once valid option
	    	}
    	}
    	System.out.print("\nHow much money will this player bring in? --> ");
    	invalid = true;
    	int money = 0;
    	while (invalid) {
    		money = scan.nextInt();
    		if (money < 100 || money > 10000) { //hard coded maximum buy-in
    			System.out.println("Minimum buy-in is $100. Maximum buy-in is $10,000");
    		}
    		else {
    			invalid = false; //breaks loop and accepts buy-in
    		}
    	}
    	Chips chips = new Chips(0,0,0,0);//******
    	String name = "";
    	String id = "";
    	if (choice!= 1) {
    		System.out.print("What is this person's name (FirstName LastName)? --> ");
    		name = scan.next();
    		name += " " + scan.next();
    		System.out.print("What is this person's id number (####)? --> ");
    		id = (scan.next()).substring(0, 3);
    	}
		int playerType = choice - 1;
		createNewPlayer(playerType, money, chips, name, id);
		if (playerType == 0) {
			System.out.println("\nThis player is assigned default title of "+ queueList.get(queueList.size()-1).getName() +".");
		}
	}
	
	private void createNewPlayer(int playerType, int money, Chips chips, String name, String id) {
		switch(playerType) {
		case 2:
			SuperVIP superVIP = new SuperVIP(money, chips, name, id);
			queueList.add(superVIP);
			break;
		case 1:
			VIP vip = new VIP(money, chips, name, id);
			queueList.add(vip);
			break;
		default:
			Player player = new Player(money, chips);
			queueList.add(player);
			break;
		}
	}
}




