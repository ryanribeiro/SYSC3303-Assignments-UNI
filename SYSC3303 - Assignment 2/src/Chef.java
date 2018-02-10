
public class Chef implements Runnable {

	private Ingredient ingredient;
	private Table table;
	
	public Chef (Ingredient ingredient, Table table) {
		this.setIngredient(ingredient);
		this.setTable(table);
	}
	
	public void run() {
		
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
