import java.text.NumberFormat;
import java.util.Locale;

public class Balance {
	private String Owner;
	private String Currency = "US Dollars";
	private double amount;

	public Balance(String Owner, double amount) {
		this.Owner = Owner;
		this.amount = amount;
	}
	
	public void deposit(double money) {
		this.amount += money;
		return;
	}
	
	public void withdraw(double money) {
		if ((this.amount - money) >= 0) {
			this.amount -= money;
		} else {
			// not enough money
		}
	}
	
	// https://www.avajava.com/tutorials/lessons/how-do-i-use-numberformat-to-format-currencies.html
	public static String applyFormat(double amount) {
		Locale locale = new Locale("en", "US");  
		NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(locale);
		return defaultFormat.format(amount);
	}
	
	// getters and setters
	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}

	public String getAmount() {
		return applyFormat(amount);
	}
	
	public double getAmountNoFormat() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
