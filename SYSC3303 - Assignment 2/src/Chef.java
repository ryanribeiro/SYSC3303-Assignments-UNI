import java.util.ArrayList;

/**
 * Chef class is responsible for having a single Ingredient, and then waiting for the other two Ingredients
 * to be placed on a Table. The Chef then makes a sandwich, and consumes it.
 * 
 * @author Ryan Ribeiro
 *
 */
public class Chef implements Runnable {

	//The specific Ingredient that the Chef has an unlimited supply of
	private Ingredient ingredient;
	//Reference to the table which the Chef will look to take the Ingredients from to make a sandwich
	private Table table;
	
	/**
	 * Constructor for the Chef class
	 * 
	 * @param ingredient the specific Ingredient that the Chef will have an unlimited supply of
	 * @param table reference to the Table which the Chef will look to take the Ingredients from to make a sandwich
	 */
	public Chef (Ingredient ingredient, Table table) {
		this.setIngredient(ingredient);
		this.setTable(table);
	}
	
	/**
	 * Called to start up the operations that the Chef must do. This includes checking the contents of the Table to
	 * see if they can make a sandwich with the two Ingredients on the Table and their own Ingredient, waiting if
	 * they cannot or producing one and consuming it if they can. 
	 * 
	 * @author Ryan Ribeiro
	 */
	public void run() {
		//ArrayList of the Ingredients that are on the Table
		ArrayList<Ingredient> tableContents;
		//As long as there are still Ingredients being places on the Table by the Agent, the Chefs will continue
		//to check the Table
		while(table.isTableOpen()) {
			//Gets an ArrayList back with the Ingredients that are on the Table
			tableContents = table.checkTableContents();
			//Checks if either Ingredient on the Table is the same as the Ingredient the Chef has 
			if ((tableContents.get(0).equals(ingredient) || (tableContents.get(1).equals(ingredient)))) {
				//If either is the same, the Chef must wait for a different set of Ingredients
				try {
					synchronized(table) {
						table.wait();
					}
				} catch (InterruptedException e) {
					System.err.println("Error occurred when a chef thread was trying to wait() after checking a table's contents.");
					e.printStackTrace();
					System.exit(1);
				}
			} else {
				//Otherwise, the Chef calls consume to make and eat a sandwich
				this.consume();
			}
			
			//Sleeps for a second to allow the user to read the text being produced
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread().getName() + " thread had an error when trying to sleep.");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	/**
	 * Consume calls a method in Table to 'take' the Ingredients, 'make' a sandwich, and eat it.
	 * 
	 * @author Ryan Ribeiro
	 */
	public synchronized void consume() {
		System.out.println(Thread.currentThread().getName() + " ate a sandwich!");
		this.table.takeTableContents();
	}
	
	/**
	 * Returns the Ingredient which the Chef has an unlimited supply of.
	 * 
	 * @return the Ingredient the Chef has an unlimited supply of.
	 */
	public synchronized Ingredient getIngredient() {
		return ingredient;
	}

	/**
	 * Sets which Ingredient the Chef should have an unlimited supply of.
	 * 
	 * @param ingredient the Ingredient the Chef has an unlimited supply of.
	 */
	public synchronized void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
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
