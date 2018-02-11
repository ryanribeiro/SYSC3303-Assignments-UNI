import java.util.ArrayList;

public class Table {

	private ArrayList<Ingredient> tableContents;
	private boolean full = false;
	private boolean tableOpen = true;
	
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
		synchronized(this) {
			tableContents.add(ingredient);
		}
		if (tableContents.size() == 2) {
			this.setFull(true);
		} else {
			this.setFull(false);
		}
		notifyAll();
		
	}
	
	public synchronized ArrayList<Ingredient> checkTableContents() {
		while (!(this.isFull())) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.err.println("Error occurred when trying to wait() after checking an empty table.");
				e.printStackTrace();
				System.exit(1);
			}
		}
		return tableContents;
	}
	
	public synchronized void takeTableContents() {
		this.tableContents.clear();
		System.out.println("Table has been cleared!");
		this.setFull(false);
		notifyAll();
	}

	public synchronized boolean isFull() {
		return full;
	}

	public synchronized void setFull(boolean full) {
		this.full = full;
	}

	public synchronized boolean isTableOpen() {
		return tableOpen;
	}

	public synchronized void setTableOpen(boolean tableOpen) {
		this.tableOpen = tableOpen;
	}
}
