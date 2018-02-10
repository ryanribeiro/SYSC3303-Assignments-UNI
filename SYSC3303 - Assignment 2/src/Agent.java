import java.util.ArrayList;

public class Agent implements Runnable {

	private static final int NUMBER_OF_SANDWICHES = 20;
	private Ingredient ingredientOne, ingredientTwo, ingredientThree;
	private Table table;
	
	public Agent (Ingredient one, Ingredient two, Ingredient three, Table table) {
		this.setIngredientOne(one);
		this.setIngredientTwo(two);
		this.setIngredientThree(three);
		this.setTable(table);
	}
	
	public void run() {
		int i = 0;
		for (; i < NUMBER_OF_SANDWICHES; i++) {
			ArrayList<Ingredient> twoIngredients = this.getTwoIngredients();
		}
	}

	public ArrayList<Ingredient> getTwoIngredients() {
		return null;
	}
	
	public Ingredient getIngredientOne() {
		return ingredientOne;
	}

	public void setIngredientOne(Ingredient ingredientOne) {
		this.ingredientOne = ingredientOne;
	}

	public Ingredient getIngredientTwo() {
		return ingredientTwo;
	}

	public void setIngredientTwo(Ingredient ingredientTwo) {
		this.ingredientTwo = ingredientTwo;
	}

	public Ingredient getIngredientThree() {
		return ingredientThree;
	}

	public void setIngredientThree(Ingredient ingredientThree) {
		this.ingredientThree = ingredientThree;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
}
