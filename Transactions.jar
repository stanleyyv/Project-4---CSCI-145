import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {
	public static int transNumber = 1;
	public static int round = 1;
	public static ArrayList<String> betTransaction = new ArrayList<String>();
	public static ArrayList<String> payTransaction = new ArrayList<String>();
	public static String result;
	public static int startAtThis = 0;
	
	public static void printTransaction(PrintWriter outFile) {
		if (betTransaction.size()-startAtThis > 0) {
			outFile.println("Round " + round + " (" + result + ")");
			outFile.println("Trans Player BAmount [ $100|  $25|   $5|   $1] BType Pay  [ $100|  $25|   $5|   $1]");
		}
		for(int i = startAtThis; i < betTransaction.size(); i++) {
			outFile.println(betTransaction.get(i)+ ""+ payTransaction.get(i));
			startAtThis++;
		}
	}

	public static void setResult(String s) {
		result = s;
	}
	
	public static void addToBetTrans(Player p, Chips betAmount, int betType) {
		String str = "";
		//Formatting Trans
		String trans = "" + transNumber;
		while (trans.length() < 5) {
			trans += " ";
		}
		//Formatting Player
		String player = "";
		if (p.getName().substring(0,6).equals("Player")) {
			player = p.getName().substring(7, p.getName().length());
		}
		else if (p.getName().length() > 6) {
			player = p.getName().substring(0, 6);
		}
		else {
			player = p.getName();
		}
		while (player.length() < 6) {
			player += " ";
		}
		//Formatting bAmount
		String bAmount = "" + betAmount.cashoutValue();
		while (bAmount.length() < 7) {
			bAmount += " ";
		}
		//Formating beginning half of line
		str += "\n" + trans + " " + player + " " + bAmount + " " + betAmount + " ";
		if(betType == 1) {
			str += " R     ";
		} else if(betType == 2) {
			str += " B     ";
		} else if(betType == 3) {
			str += " N     ";
		}
		betTransaction.add(str);
	}
	
	public static void addToPayTrans(Chips c) {
		String s = "";
		//Formatting Pay
		String pay = "" + c.cashoutValue();
		while (pay.length() < 4) {
			pay += " ";
		}
		//Formatting second part of line
		s += pay + " " + c;
		payTransaction.add(s);
	}
}

