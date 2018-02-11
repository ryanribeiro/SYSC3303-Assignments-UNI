import java.util.ArrayList;
import java.util.Random;

/**
 * Agent class is responsible for picking two sandwich ingredients, and placing them both on the table.
 * 
 * @author Ryan Ribeiro
 *
 */
public class Agent implements Runnable {

	//Number of sandwiches to be made (number of times to loop the code)
	private static final int NUMBER_OF_SANDWICHES = 20;
	//ArrayList if the three ingredients provided
	private ArrayList<Ingredient> ingredients;
	//Reference to the table which the ingredients will be placed on
	private Table table;
	
	/**
	 * Constructor for the Agent class
	 * 
	 * @param one first Ingredient of the three
	 * @param two second Ingredient of the three
	 * @param three third Ingredient of the three
	 * @param table reference to the table which ingredients will be placed on
	 * @author Ryan Ribeiro
	 */
	public Agent (Ingredient one, Ingredient two, Ingredient three, Table table) {
		this.ingredients = new ArrayList<>();
		//Calls a function, which is synchronized, to setup the array list
		this.setupIngredients(one, two, three);
		this.setTable(table);
	}
	
	/**
	 * Called to start up the operations that the Agent must do. This includes looping for a certain number
	 * of times, placing two different ingredients on the table, and waiting for the table to be empty to
	 * do it again.
	 * 
	 * @author Ryan Ribeiro
	 */
	public void run() {
		//Loop control variable
		int i = 0;
		//Creating a specified number of sandwiches
		for (; i < NUMBER_OF_SANDWICHES; i++) {
			//ArrayList of the two different ingredients to be placed on the table. Obtained by call to function getTwoIngredients()
			ArrayList<Ingredient> twoIngredients = this.getTwoIngredients();
			
			System.out.println("\nSandwich number " + (i+1) + " has been started.");
			System.out.println(Thread.currentThread().getName() + " put " + this.getIngredientName(twoIngredients.get(0)) + " on the table.");
			//Puts first randomly chosen ingredient on the table
			table.put(twoIngredients.get(0));
			System.out.println(Thread.currentThread().getName() + " put " + this.getIngredientName(twoIngredients.get(1)) + " on the table.");
			//Puts second randomly chosen, but different, ingredient on the table
			table.put(twoIngredients.get(1));
			
			//Sleeps for a second to allow the user to read the text being produced
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println("Agent thread had an error when trying to sleep.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		//NUMBER_OF_SANDWICHES have been made, so the table is now closed.
		table.setTableOpen(false);
		System.out.println("\nAll sandwiches have been made and consumed. Exiting now.");
	}

	/**
	 * Used to assign a name to a given Ingredient, as they are enums and do not have a name associated with them by default.
	 * 
	 * @param ingredient the Ingredient which you want to know the name of
	 * @return the name of the given Ingredient
	 * @author Ryan Ribeiro
	 */
	private synchronized String getIngredientName(Ingredient ingredient) {
		//Simple switch statement to check which enum is is, and returns the appropriate name
		switch (ingredient) {
			case BREAD:
				return "bread";
			case JAM:
				return "jam";
			case BUTTER:
				return "butter";
			default:
				return "ERROR in obtaining ingredient name";
		}
	}

	/**
	 * Used to ensure that the ArrayList of Ingredients is setup in a synchronized manner.
	 * 
	 * @param one first Ingredient of the three
	 * @param two second Ingredient of the three
	 * @param three third Ingredient of the three
	 * @author Ryan Ribeiro
	 */
	public synchronized void setupIngredients(Ingredient one, Ingredient two, Ingredient three) {
		this.ingredients.add(one);
		this.ingredients.add(two);
		this.ingredients.add(three);
	}
	
	/**
	 * Randomly selects two different ingredients to be put on the table, and returns them as an ArrayList.
	 * 
	 * @return an ArrayList that contains the two different ingredients that are to be placed on the table
	 * @author Ryan Ribeiro
	 */
	public synchronized ArrayList<Ingredient> getTwoIngredients() {
		//Generates an array of two distinct integers between 0 and 2 (e.g. 0,1 or 0,2 or 1,0 etc.)
		int[] randomInts;
		synchronized(this) {
			//Generates two random numbers, between 0 and 3 (non inclusive), which is a 0, 1, or 2, that are distinct from one
			//another, and converts it to an array of integers
			randomInts = new Random().ints(0,3).distinct().limit(2).toArray();
		}		
		ArrayList<Ingredient> twoIngredients = new ArrayList<>();
		
		//Creates and array with two distinct ingredients based on the previously generated distinct random integers
		twoIngredients.add(this.getIngredient(randomInts[0]));
		twoIngredients.add(this.getIngredient(randomInts[1]));
		
		return twoIngredients;
	}
	
	/**
	 * Gets the Ingredient at a specified index.
	 * 
	 * @param index the index of the specified Ingredient in the ArrayList of Ingredients
	 * @return the specified Ingredient at the given index
	 * @author Ryan Ribeiro
	 */
	public synchronized Ingredient getIngredient(int index) {
		return this.ingredients.get(index);
	}

	/**
	 * Returns the Table associated with this instance of Agent.
	 * 
	 * @return a Table object associated with this instance of Agent
	 * @author Ryan Ribeiro
	 */
	public synchronized Table getTable() {
		return table;
	}

	/**
	 * Sets the Table associated with this instance of Agent
	 * 
	 * @param table the Table that this instance of Agent is associated with
	 * @author Ryan Ribeiro
	 */
	public synchronized void setTable(Table table) {
		this.table = table;
	}
}
