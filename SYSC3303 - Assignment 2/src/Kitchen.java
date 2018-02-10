
public class Kitchen {

//	private Agent agent;
//	private Chef chefOne, chefTwo, chefThree;
//	private Table table;
	
	public Kitchen () {
//	public Kitchen (Agent agent, Chef one, Chef two, Chef three, Table table) {
//		this.setAgent(agent);
//		this.setChefOne(one);
//		this.setChefTwo(two);
//		this.setChefThree(three);
//		this.setTable(table);
	}
	
	public static void main(String[] args) {
		Thread agent, chefOne, chefTwo, chefThree;
		Table table;
		
		table = new Table();		
		agent = new Thread(new Agent(Ingredient.BREAD, Ingredient.JAM, Ingredient.BUTTER, table), "Agent");
		chefOne = new Thread(new Chef(Ingredient.BREAD, table), "Chef One");
		chefTwo = new Thread(new Chef(Ingredient.JAM, table), "Chef Two");
		chefThree = new Thread(new Chef(Ingredient.BUTTER, table), "Chef Three");
		
		agent.start();
		chefOne.start();
		chefTwo.start();
		chefThree.start();
		
	}

//	public Agent getAgent() {
//		return agent;
//	}
//
//	public void setAgent(Agent agent) {
//		this.agent = agent;
//	}
//
//	public Chef getChefOne() {
//		return chefOne;
//	}
//
//	public void setChefOne(Chef chefOne) {
//		this.chefOne = chefOne;
//	}
//
//	public Chef getChefTwo() {
//		return chefTwo;
//	}
//
//	public void setChefTwo(Chef chefTwo) {
//		this.chefTwo = chefTwo;
//	}
//
//	public Chef getChefThree() {
//		return chefThree;
//	}
//
//	public void setChefThree(Chef chefThree) {
//		this.chefThree = chefThree;
//	}
//
//	public Table getTable() {
//		return table;
//	}
//
//	public void setTable(Table table) {
//		this.table = table;
//	}
}
