
public abstract class Task {
	private int id;
	private static int taskCount = 0;
	
	public Task(){
		this.id = taskCount;
		taskCount += 1;
	}
	
	public abstract void solve();
}
