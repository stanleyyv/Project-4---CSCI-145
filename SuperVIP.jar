
public class SuperVIP extends VIP {
	
	public SuperVIP(int initialMoney, Chips chips, String playerName, String id) {
		super(initialMoney, chips, playerName, id);	
	}
	
	public int getBonus() {
		int bonus = 0;
		if (betCount >= 10) {
			bonus = 20;
		}
		if (betCount > 20) {
			bonus = 50;
		}
		return bonus;
	}
	
	//Overridden method
	public int getCashback() {
		return (int)(0.05*totalBets + 0.5) + getBonus();
	}
}


