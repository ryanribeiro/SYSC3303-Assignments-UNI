import java.util.ArrayList;

public class Table {

	private ArrayList<Ingredient> tableContents;
	private boolean clear = true;
	private boolean full = false;
	
	public Table () {
		this.tableContents = new ArrayList<>();
	}
	
	public synchronized void put(Ingredient ingredient) {
		while (this.isFull()) {
			try {
				wait();
			} catch (InterruptedException e){
				System.err.println("Error occurred when trying to wait() after putting an ingredient on a full table.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		tableContents.add(ingredient);
		if (tableContents.size() == 2)
			this.setFull(true);
		this.setClear(false);
	}
	
	public synchronized void clearTable() {
		tableContents.clear();
		this.setClear(true);
		this.setFull(false);
	}
	
	public synchronized ArrayList<Ingredient> getTableContents() {
		return tableContents;
	}
	
	public synchronized ArrayList<Ingredient> takeTableContents() {
		ArrayList<Ingredient> ingredients = this.getTableContents();
		this.clearTable();
		return ingredients;
	}

	public synchronized boolean isClear() {
		return clear;
	}

	public synchronized void setClear(boolean clear) {
		this.clear = clear;
	}
	
	public synchronized boolean isFull() {
		return full;
	}

	public synchronized void setFull(boolean full) {
		this.full = full;
	}
}
