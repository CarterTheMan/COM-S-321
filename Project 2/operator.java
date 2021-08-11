public abstract class operator {
	@Override 
	public abstract String toString();
	
	public static String correctReg(String register) {
		String input = String.valueOf(Integer.parseInt(register,2));
		if (input.equals("28")) {
			return "SP";
		} else if (input.equals("29")) {
			return "FP";
		} else if (input.equals("30")) {
			return "LR";
		} else if (input.equals("31")) {
			return "XZR";
		} else {
			return "X" + input;
		}
	}
}
