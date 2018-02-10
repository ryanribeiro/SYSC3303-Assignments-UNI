import java.util.ArrayList;
import java.util.Random;

public class Agent implements Runnable {

	private static final int NUMBER_OF_SANDWICHES = 20;
	private ArrayList<Ingredient> ingredients;
	private Table table;
	
	public Agent (Ingredient one, Ingredient two, Ingredient three, Table table) {
		this.ingredients = new ArrayList<>();
		this.setupIngredients(one, two, three);
		this.setTable(table);
	}
	
	public void run() {
		int i = 0;
		for (; i < NUMBER_OF_SANDWICHES; i++) {
			ArrayList<Ingredient> twoIngredients = this.getTwoIngredients();
		}
	}

	public synchronized void setupIngredients(Ingredient one, Ingredient two, Ingredient three) {
		this.ingredients.add(one);
		this.ingredients.add(two);
		this.ingredients.add(three);
	}
	
	public synchronized ArrayList<Ingredient> getTwoIngredients() {
		//Generates an array of two distinct integers between 0 and 2 (e.g. 0,1 or 0,2 or 1,0 etc.)
		int[] randomInts = new Random().ints(0,2).distinct().limit(2).toArray();
		ArrayList<Ingredient> twoIngredients = new ArrayList<>();
		
		//Creates and array with two distinct ingredients based on the previously generated distinct random integers
		twoIngredients.add(this.getIngredient(randomInts[0]));
		twoIngredients.add(this.getIngredient(randomInts[1]));
		
		return twoIngredients;
	}
	
	public synchronized Ingredient getIngredient(int index) {
		return this.ingredients.get(index);
	}

	public synchronized Table getTable() {
		return table;
	}

	public synchronized void setTable(Table table) {
		this.table = table;
	}
}
