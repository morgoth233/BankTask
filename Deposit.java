
public class Deposit extends BankProduct{
	
	Deposit(String name,int period,double increase){
		super.name = name;
		super.period = period;
		super.increase = increase;
		super.monthIncrease = (money*increase)/100;
	}
	
	Deposit(String name,int period,double increase,double money){
		super.name = name;
		super.money = money;
		super.increase = increase;
		if (period<1||period>60) {
			super.period = 30;;
			System.out.println("period is set to 30 months");
		}else{
			super.period = period;
		}
		super.monthIncrease = (money*increase*0.01)/period;
	}
	
	

}
