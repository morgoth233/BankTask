import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;


public class Demo {

	public static void main(String[] args) {
		
		
		//Short Deposit с период от 3 месеца и лихва 3% и Long Deposit с период от 12 месеца и лихва 5%.
		Deposit shortDeposit = new Deposit("Short Deposit",3,3);
		Deposit longDeposit = new Deposit("Long Deposit",12,5);
		//Home Credit с лихва 6% и Consumer Credit с лихва 10%
		Credit homeCredit = new Credit("Home Credit",6);
		Credit consumerCredit = new Credit("Consumer Credit",10);
		
		Bank dsk = new Bank("DSK", "Sofia", 0);
		
		Client[] clients = new Client[10];
		
		clients[0] = new Client("Georgi", "Sofia", 500, 1000);
		clients[1] = new Client("Samuil", "Sofia", 500, 2000);
		clients[2] = new Client("Cezar", "Sofia", 500, 3000);
		clients[3] = new Client("Misho", "Sofia", 500, 4000);
		clients[4] = new Client("Koiocho", "Sofia", 500, 5000);
		clients[5] = new Client("Mihail", "Sofia", 500, 6000);
		clients[6] = new Client("Stamat", "Sofia", 500, 7000);
		clients[7] = new Client("Stanimir", "Sofia", 500, 8000);
		clients[8] = new Client("Miroslav", "Sofia", 500, 9000);
		clients[9] = new Client("Pesho", "Sofia", 500, 10000);

		
		//all clients make deposits
		for (int i = 0; i < clients.length; i++) {
	
			Random r = new Random();
			double randumSum = ((clients[i].getMoney() * (r.nextInt(100-80) + 80))/100);//ot 80% do 100% ot client.money
			clients[i].makeDeposit(longDeposit,randumSum, dsk);
		}
		
		
		dsk.printInfo();
		//clients take credits
		for (int i = 0; i < clients.length; i++) {
			Random r = new Random();
			double randumSum = (r.nextInt(10000-1000) + 1000);//credit ot 1000 do 10_000lv
			System.out.println(clients[i].getName() + " wants to take " + randumSum + " credit");
			clients[i].takeCredit(consumerCredit,randumSum,36,dsk);//3 godishen kredit
			
		}
		
		dsk.printInfo();
		
		System.out.println("=======CLIENTS INFO=======");
		for (int i = 0; i < clients.length; i++) {
			System.out.println();
			clients[i].printInfo();
		}
	}
	

}
