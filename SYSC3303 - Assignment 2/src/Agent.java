
public class Agent extends Thread{

	private Ingredient ingredientOne, ingredientTwo, ingredientThree;
	
	public Agent (Ingredient one, Ingredient two, Ingredient three) {
		this.setIngredientOne(one);
		this.setIngredientTwo(two);
		this.setIngredientThree(three);
	}
	
	public void run() {
		
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
}
