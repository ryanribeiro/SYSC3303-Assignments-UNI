import java.util.ArrayList;

public class Chef implements Runnable {

	private Ingredient ingredient;
	private Table table;
	
	public Chef (Ingredient ingredient, Table table) {
		this.setIngredient(ingredient);
		this.setTable(table);
	}
	
	public void run() {
		ArrayList<Ingredient> tableContents;
		while(table.isTableOpen()) {
			tableContents = table.checkTableContents();
			if ((tableContents.get(0).equals(ingredient) || (tableContents.get(1).equals(ingredient)))) {
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
				this.consume();
			}
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				System.err.println(Thread.currentThread().getName() + " thread had an error when trying to sleep.");
				e.printStackTrace();
				System.exit(1);
			}
		}
	}

	public synchronized void consume() {
		System.out.println(Thread.currentThread().getName() + " ate a sandwich!");
		this.table.takeTableContents();
	}
	
	public synchronized Ingredient getIngredient() {
		return ingredient;
	}

	public synchronized void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public synchronized Table getTable() {
		return table;
	}

	public synchronized void setTable(Table table) {
		this.table = table;
	}
}
