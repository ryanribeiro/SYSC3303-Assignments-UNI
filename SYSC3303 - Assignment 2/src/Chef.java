
public class Chef extends Thread{

	private Ingredient ingredient;
	
	public Chef (Ingredient ingredient) {
		this.setIngredient(ingredient);
	}
	
	public void run() {
		
	}

	public Ingredient getIngredient() {
		return ingredient;
	}

	public void setIngredient(Ingredient ingredient) {
		this.ingredient = ingredient;
	}
}
