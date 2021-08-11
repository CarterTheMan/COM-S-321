public class bTarget extends operator{
	int branchTarget;
	String name;
	boolean defined;

	public bTarget() {
		branchTarget = -1;
		name = "";
		defined = false;
	}
	
	public bTarget(String name, int target) {
		this.name = name + ":";
		branchTarget = target;
		defined = false;
	}

	public int getTarget() {
		return branchTarget;
	}
	
	public boolean isUsed() {
		return defined;
	}
	
	public void setUsed() {
		defined = true;
	}

	public String toString() {
		return "Branch" + branchTarget + ":";
	}
}
