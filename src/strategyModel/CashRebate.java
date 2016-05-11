package strategyModel;

public class CashRebate extends CashSuper {

	private double moneyRebate = 1d;
	
	public CashRebate(String moneyRebate) {
		this.moneyRebate = Double.parseDouble(moneyRebate);
	}
	
	@Override
	public double acciptCash(double money) {
		
		return money * moneyRebate;
	}

}
