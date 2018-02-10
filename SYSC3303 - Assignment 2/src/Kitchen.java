
public class Kitchen {

	private Agent agent;
	private Chef chefOne, chefTwo, chefThree;
	
	public Kitchen (Agent agent, Chef one, Chef two, Chef three) {
		this.setAgent(agent);
		this.setChefOne(one);
		this.setChefTwo(two);
		this.setChefThree(three);
	}
	
	public static void main(String[] args) {
		Agent agent = new Agent(Ingredient.BREAD, Ingredient.JAM, Ingredient.BUTTER);
		Chef chefOne = new Chef(Ingredient.BREAD);
		Chef chefTwo = new Chef(Ingredient.JAM);
		Chef chefThree = new Chef(Ingredient.BUTTER);
		Kitchen kitchen = new Kitchen(agent, chefOne, chefTwo, chefThree);
		
		
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Chef getChefOne() {
		return chefOne;
	}

	public void setChefOne(Chef chefOne) {
		this.chefOne = chefOne;
	}

	public Chef getChefTwo() {
		return chefTwo;
	}

	public void setChefTwo(Chef chefTwo) {
		this.chefTwo = chefTwo;
	}

	public Chef getChefThree() {
		return chefThree;
	}

	public void setChefThree(Chef chefThree) {
		this.chefThree = chefThree;
	}
}
