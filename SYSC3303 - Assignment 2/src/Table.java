import java.util.ArrayList;

/**
 * Table class is responsible for keeping track of which Ingredients are on the Table at any given time.
 * 
 * @author Ryan Ribeiro
 *
 */
public class Table {

	//ArrayList of the Ingredients that are on the Table
	private ArrayList<Ingredient> tableContents;
	//Boolean indicating if the Table is full (meaning that the Agent has places his Ingredients on the Table)
	private boolean full = false;
	//Boolean indicating if the Table is open (meaning that the number of sandwiches made has not his the limit)
	private boolean tableOpen = true;
	
	/**
	 * Constructor for the Table class
	 * 
	 * @author Ryan Ribeiro
	 */
	public Table () {
		this.tableContents = new ArrayList<>();
	}
	
	/**
	 * Puts an Ingredient on the table. This method returns when the Ingredient has been
	 * put on the Table.
	 * 
	 * @param ingredient Ingredient which is to be placed on the Table
	 * @author Ryan Ribeiro
	 */
	public synchronized void put(Ingredient ingredient) {
		//While the table is full, we must wait to put new Ingredients on it
		while (this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e){
				System.err.println("Error occurred when trying to wait() after putting an ingredient on a full table.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		synchronized(this) {
			//Add the new Ingredient to the ArrayList of Ingredients on the Table
			tableContents.add(ingredient);
		}
		//If the Table has two Ingredients, the Table is full and the Agent cannot place more Ingredients on it
		if (tableContents.size() == 2) {
			this.setFull(true);
		} else {
			this.setFull(false);
		}
		notifyAll();
		
	}
	
	/**
	 * Returns an ArrayList of the Ingredients that are currently on the Table, provided
	 * that the Table is not empty, otherwise it waits for the Table to be full.
	 * 
	 * @return an ArrayList of the Ingredients that are current on the Table
	 * @author Ryan Ribeiro
	 */
	public synchronized ArrayList<Ingredient> checkTableContents() {
		while (!(this.isFull())) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Error occurred when trying to wait() after checking an empty table.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		return tableContents;
	}
	
	/**
	 * 'Takes' the contents from the Table by clearing the ArrayList of Ingredients, and then
	 * sets the state of the table to not Full.
	 * 
	 * @author Ryan Ribeiro
	 */
	public synchronized void takeTableContents() {
		this.tableContents.clear();
		System.out.println("Table has been cleared!");
		this.setFull(false);
		notifyAll();
	}

	/**
	 * Indicates if the Table is full or not.
	 * 
	 * @return a boolean indicating if the Table is full or not
	 * @author Ryan Ribeiro
	 */
	public synchronized boolean isFull() {
		return full;
	}

	/**
	 * Sets the status of the Table to full or not
	 * 
	 * @param full a boolean indicating if the table is full (true) or not (false)
	 * @author Ryan Ribeiro
	 */
	public synchronized void setFull(boolean full) {
		this.full = full;
	}

	/**
	 * Indicates if the Table is open to be checked by Chefs
	 * 
	 * @return boolean indicating if the Table is open for Chefs to check
	 * @author Ryan Ribeiro
	 */
	public synchronized boolean isTableOpen() {
		return tableOpen;
	}

	/**
	 * Sets the status of the Table to open or not
	 * 
	 * @param tableOpen a boolean indicating if the table is open (true) or not (false)
	 * @author Ryan Ribeiro
	 */
	public synchronized void setTableOpen(boolean tableOpen) {
		this.tableOpen = tableOpen;
	}
}
