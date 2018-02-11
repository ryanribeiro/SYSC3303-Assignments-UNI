
/**
 * Kitchen is more so symbolic than actually necessary. A Kitchen has chefs, an agent, and a table.
 * It just makes sense for everything to be created and run from a Kitchen class.
 * 
 * @author Ryan Ribeiro
 *
 */
public class Kitchen {
	
	public Kitchen () {
		//Nothing actually happens here. Kitchen is more symbolic than actually necessary.
	}
	
	public static void main(String[] args) {
		Thread agent, chefOne, chefTwo, chefThree;
		Table table;
		
		//Creates tables, an agent (with all ingredients), and three chefs (each with different ingredients)
		table = new Table();		
		agent = new Thread(new Agent(Ingredient.BREAD, Ingredient.JAM, Ingredient.BUTTER, table), "Agent");
		chefOne = new Thread(new Chef(Ingredient.BREAD, table), "Chef One");
		chefTwo = new Thread(new Chef(Ingredient.JAM, table), "Chef Two");
		chefThree = new Thread(new Chef(Ingredient.BUTTER, table), "Chef Three");
		
		//Starts the agent thread, and the three chef threads
		agent.start();
		chefOne.start();
		chefTwo.start();
		chefThree.start();		
	}
}
