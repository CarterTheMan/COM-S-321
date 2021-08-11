public class d extends operator{

	String dType;

	public d(String input) {
		String opcode = input.substring(0, 11);
		String address = input.substring(11, 20);
		String op = input.substring(20, 22);
		String Rn = input.substring(22, 27);
		String Rt = input.substring(27, 32);
		if (opcode.equals("11111000010")) {
			dType = LDUR(Rt, Rn, address);
		} else if (opcode.equals("11111000000")) {
			dType = STUR(Rt, Rn, address);
		} else {
			dType = "";
			return;
		}
	}

	public static String LDUR(String Rt, String Rn, String Address) {
		String rt = correctReg(Rt);
		String rn = correctReg(Rn);
		int address = Integer.parseInt(Address,2);
		return "LDUR " + rt + ", [" + rn + ", #" + address + "]";
	}

	public static String STUR(String Rt, String Rn, String Address) {
		String rt = correctReg(Rt);
		String rn = correctReg(Rn);
		int address = Integer.parseInt(Address,2);
		return "STUR " + rt + ", [" + rn + ", #" + address + "]";
	}

	public String toString() {
		return dType;
	}
}