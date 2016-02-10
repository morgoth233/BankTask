import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;

public class Bank {

	private String name;
	private String address;
	private double money;
	private HashMap<Client, HashSet<BankProduct>> bankProducts;

	public Bank(String name, String address, double money) {
		this.name = name;
		this.address = address;
		this.money = money;
		this.bankProducts = new HashMap<Client, HashSet<BankProduct>>();
	}

	public void newDeposit(Client client, BankProduct deposit) {
		if (!bankProducts.containsKey(client)) {
			bankProducts.put(client, new HashSet<BankProduct>());
			bankProducts.get(client).add(deposit);
			this.money += deposit.money;
		}else{
			bankProducts.get(client).add(deposit);
			this.money += deposit.money;
		}
	}

	public boolean newCredit(Client client, BankProduct credit) {
		if (bankProducts.containsKey(client)) {
			int allCreditsMonthSubmit = 0;
			//izchislqvane na mesechnata vnoska po vsichki krediti
			for (BankProduct b : bankProducts.get(client)) {
				allCreditsMonthSubmit += b.monthSubmit;
			}
			if (allCreditsMonthSubmit > client.getSalary() * 0.5) {
				System.out.println(client.getName() + " can't take this credit because the month submit of all his credits " +allCreditsMonthSubmit+ "lv. exceeds 50% of his month salary: " + client.getSalary()/2 +"lv.");
				return false;
			} else {//proverka dali i vnoskata po tekushtiq kredit nqma da nadvishava 50% ot zaplatata na clienta 
				if (credit.monthSubmit > client.getSalary() * 0.5) {
					System.out.println(client.getName() + " can't take this credit because the month submit for this credit " +credit.monthSubmit+ "lv. exceeds 50% of his month salary: " + client.getSalary()/2 +"lv.");					
					System.out.println("The sum with increase for submiting is" + (int)(credit.monthSubmit*credit.period) +"lv.");
					return false;
				}else{
					bankProducts.get(client).add(credit);
					this.money -= credit.money;
					return true;
				}
			}
		} else if (!bankProducts.containsKey(client)) {
			if (credit.monthSubmit > client.getSalary() * 0.5) {
				System.out.println(client.getName() + " can't take this credit because the month submit for this credit " +credit.monthSubmit+ "lv. exceeds 50% of his month salary " + client.getSalary()/2);
				return false;
			}else{
				bankProducts.put(client, new HashSet<BankProduct>());
				this.money -= credit.money;
				return true;
			}
		}
		return false;

	}

	public void payIncrease() {//Client client, BankProduct bankProduct
		//client.setMoney(client.getMoney() + bankProduct.monthIncrease);
		for (Entry<Client, HashSet<BankProduct>> e : this.bankProducts.entrySet()) {
			for (BankProduct b : e.getValue()) {
				if (b instanceof Deposit) {
					b.money += b.monthIncrease;
					//e.getKey().setMoney(getMoney()+b.monthIncrease);//ako iskame lihvata da se izplati na ryka na klienta
				}
			}
		}
	}

	public void printInfo() {
		System.out.println("=======BANK INFO=======");
		System.out.println(this.name + " ima " + this.money
				+ "lv. v nalichnost");
		// calc bank reserve
		double reserve = 0;
		for (Entry<Client, HashSet<BankProduct>> e : this.bankProducts.entrySet()) {
			for (BankProduct b : e.getValue()) {
				if (b instanceof Deposit) {
					reserve += b.money * 0.9;
				}
			}
		}
		System.out.println("Bankoviq rezerv e: " + reserve);
		// print bank clients and their products
		System.out.println("Bankata ima slednite clienti:");
		for (Entry<Client, HashSet<BankProduct>> e : this.bankProducts
				.entrySet()) {
			System.out.println(e.getKey().getName() + " ima: ");
			for (BankProduct b : e.getValue()) {
				System.out.println(b.name + " ot " + b.money + "lv");
			}
		}

	}

	public double getMoney() {
		return money;
	}

	public HashMap<Client, HashSet<BankProduct>> getBankProducts() {
		return bankProducts;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	
	// private void calcIncrease(){
	//
	// }
}
