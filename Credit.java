
public class Credit extends BankProduct{
	
	Credit(String name,double increase){
		super.name = name;
		super.increase = increase;
		//super.monthSubmit = (money*increase)/100;
		
			
	}

	Credit(String name,int period,double increase,double money){
		super.name = name;
		super.money = money;
		if (period<1||period>60) {
			super.period = 30;;
			System.out.println("period is set to 30 months");
		}else{
			super.period = period;
		}
		super.increase = increase;
		super.monthSubmit = Math.round((money+(money*increase*0.01))/period);//vnoskata koqto trqbva da pravi vseki mesec ((money+(money*increase*0.01))/period)
			
	}
	
	
	
}
