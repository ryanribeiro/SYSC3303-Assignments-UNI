
public class Chef implements Runnable {

	private Ingredient ingredient;
	private Table table;
	
	public Chef (Ingredient ingredient, Table table) {
		this.setIngredient(ingredient);
		this.setTable(table);
	}
	
	public void run() {
		while(table.isTableOpen()) {
			
		}
	}

	public synchronized void consume() {
		
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
