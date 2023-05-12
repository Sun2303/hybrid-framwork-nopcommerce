package tpVungTau;

public class SunCoffee1 {
	public String espresso = "Cafe Espresso";
	
	protected String latte = "Cafe Latte";
	
	String bacXiu = "White Coffee";
	
	private String coffeeSun = "Coffee of Sun";
	
	
	public void shipEspresso() {
		System.out.println("Ship cafe: " + espresso);
	}
	
	protected void shipLatte() {
		System.out.println("Ship cafe: " + latte);
	}
	
	void shipBacXiu() {
		System.out.println("Ship bac xiu: " + bacXiu);
	}
	
	private void shipSunCoffee() {
		System.out.println("Ship coffee of Sun: " + coffeeSun);
	}
	
	public static void main(String[] agrs) {
		SunCoffee1 menu = new SunCoffee1();
		menu.shipEspresso();
		menu.shipBacXiu();
		menu.shipLatte();
		menu.shipSunCoffee();
	}
	
}
