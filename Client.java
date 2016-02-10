import java.util.ArrayList;

public class Client {
	
	private String name;
	private String address;
	private double money;
	private double salary;
	private ArrayList<Deposit> deposits;
	private ArrayList<Credit> credits;
		
	public Client(String name, String address, double salary, double money) {
		this.name = name;
		this.address = address;
		this.money = money;
		this.salary = salary;
		this.deposits = new ArrayList<Deposit>();
		this.credits = new ArrayList<Credit>();
	}

	public void makeDeposit(Deposit deposit, double sum, Bank bank){
		if (this.money>0) {
			deposits.add(new Deposit(deposit.name,deposit.period,deposit.increase,sum));
			bank.newDeposit(this,new Deposit(deposit.name,deposit.period,deposit.increase,sum));
			this.money -= sum;
		}else{
			System.out.println("Calm down, you don't have so much money");
		}
		
	}
	
	public void takeCredit(Credit creditType, double sum, int period, Bank bank){
		if (bank.newCredit(this,new Credit(creditType.name,period,creditType.increase,sum))) {
			credits.add(new Credit(creditType.name,period,creditType.increase,sum));
			System.out.println(this.name + " month submit for this credit is: " + (sum+sum*creditType.increase*0.01/period) + "lv.");
			this.money += sum;
		}				
	}
	
	public void submitMoney(Bank bank, Credit credit){
		for(BankProduct bp: bank.getBankProducts().get(this)){
			if (bp.equals(credit)) {//(bp instanceof Credit) ako iskame da plati vnoskite si za vsichki krediti koito ima
				bank.setMoney(bank.getMoney()+bp.monthSubmit);
			}else{
				System.out.println("you don't have such credit");
			}
		}
		
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getName() {
		return name;
	}

	public double getSalary() {
		return salary;
	}
	
	public void printInfo(){
		
		System.out.println("Name: "+ this.name);
		System.out.println("Address: " + this.address);
		System.out.println("Money: " + this.money);
		System.out.println("Salary: " + this.salary);
		System.out.println("------------------------");
		System.out.println("List with bank accounts:");
		System.out.println("------------------------");
		System.out.println("DEPOSITS:");
		for(int i=0;i<deposits.size();i++){
			System.out.println(deposits.get(i).name);
			System.out.println(deposits.get(i).money+"lv.");
			System.out.println("Period: " + deposits.get(i).period+ " months");
			System.out.println("Increase: " + deposits.get(i).increase + "%");
			System.out.printf("Monthly increase: %.2flv.\n", deposits.get(i).monthIncrease);			
		}
		System.out.println();
		System.out.println("CREDITS:");
		for(int i=0;i<credits.size();i++){
			System.out.println(credits.get(i).name);
			System.out.println(credits.get(i).money+"lv.");
			System.out.println("Period: " + credits.get(i).period+ " months");
			System.out.println("Increase: " + credits.get(i).increase + "%");
			System.out.println("Monthly submit: " + credits.get(i).monthSubmit +"lv");			
		}
	}

}
