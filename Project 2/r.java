public class r extends operator {

	String rType;

	public r(String input) {
		String opcode = input.substring(0, 11);
		String Rm = input.substring(11, 16);
		String Shamt = input.substring(16, 22);
		String Rn = input.substring(22, 27);
		String Rd = input.substring(27, 32);
		if (opcode.equals("10001011000")) {
			rType = ADD(Rm, Rn, Rd);
		} else if (opcode.equals("10001010000")) {
			rType = AND(Rm, Rn, Rd);
		} else if (opcode.equals("11001011000")) {
			rType = SUB(Rm, Rn, Rd);
		} else if (opcode.equals("11101011000")) {
			rType = SUBS(Rm, Rn, Rd);
		} else if (opcode.equals("11111111101")) {
			rType = PRNT(Rd);
		} else if (opcode.equals("11111111100")) {
			rType = "PRNL";
		} else if (opcode.equals("10011011000")) {
			rType = MUL(Rm, Rn, Rd);
		} else if (opcode.equals("11010110000")){
			rType = BR(Rn);
		} else if (opcode.equals("11001010000")) {
			rType = EOR(Rm, Rn, Rd);
		} else if (opcode.equals("11010011011")) {
			rType = LSL(Rn, Rd, Shamt);
		} else if (opcode.equals("11010011010")) {
			rType = LSR(Rn, Rd, Shamt);
		} else if (opcode.equals("10101010000")) {
			rType = ORR(Rm, Rn, Rd);
		} else if (opcode.equals("11111111110")) {
			rType = "DUMP";
		} else if (opcode.equals("11111111111")) {
			rType = "HALT";
		} else {
			rType = "";
			return;
		}
	}

	public static String ADD(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "ADD " + rd + ", " + rn + ", " + rm;
	}

	public static String AND(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "AND " + rd + ", " + rn + ", " + rm;
	}

	public static String BR(String Rn) {
		String rn = correctReg(Rn);
		return "BR " + rn;
	}

	public static String EOR(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "EOR " + rd + ", " + rn + ", " + rm;
	}

	public static String LSL(String Rn, String Rd, String Shamt) {
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		int shamt = Integer.parseInt(Shamt,2);
		return "LSL " + rd + ", " + rn + " #" + shamt;
	}

	public static String LSR(String Rn, String Rd, String Shamt) {
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		int shamt = Integer.parseInt(Shamt,2);
		return "LSR " + rd + ", " + rn + " #" + shamt;
	}

	public static String ORR(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "ORR " + rd + ", " + rn + ", " + rm;
	}

	public static String SUB(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "SUB " + rd + ", " + rn + ", " + rm;
	}

	public static String SUBS(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "SUBS " + rd + ", " + rn + ", " + rm;
	}

	public static String MUL(String Rm, String Rn, String Rd) {
		String rm = correctReg(Rm);
		String rd = correctReg(Rd);
		String rn = correctReg(Rn);
		return "MUL " + rd + ", " + rn + ", " + rm;
	}

	public static String PRNT(String Rd) {
		String rd = correctReg(Rd);
		return "PRNT " + rd;
	}

	public String toString() {
		return rType;
	}
}