
public class Kitchen {
	
	public Kitchen () {
		
	}
	
	public static void main(String[] args) {
		Thread agent, chefOne, chefTwo, chefThree;
		Table table;
		
		table = new Table();		
		agent = new Thread(new Agent(Ingredient.BREAD, Ingredient.JAM, Ingredient.BUTTER, table), "Agent");
		chefOne = new Thread(new Chef(Ingredient.BREAD, table), "Chef One");
		chefTwo = new Thread(new Chef(Ingredient.JAM, table), "Chef Two");
		chefThree = new Thread(new Chef(Ingredient.BUTTER, table), "Chef Three");
		
		agent.start();
		chefOne.start();
		chefTwo.start();
		chefThree.start();
		
	}
}
