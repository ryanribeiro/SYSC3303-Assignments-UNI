import java.util.ArrayList;

public class Table {

	private ArrayList<Ingredient> tableContents;
	private boolean clear = true;
	
	public Table () {
		this.tableContents = new ArrayList<>();
	}
	
	public synchronized void put(Ingredient ingredient) {
		tableContents.add(ingredient);
		this.setClear(false);
	}
	
	public void clearTable() {
		tableContents.clear();
		this.setClear(true);
	}
	
	public synchronized ArrayList<Ingredient> getTableContents() {
		return tableContents;
	}

	public boolean isClear() {
		return clear;
	}

	public void setClear(boolean clear) {
		this.clear = clear;
	}
}
