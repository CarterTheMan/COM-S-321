public class i extends operator{
    
	String iType;
	
    public i(String input) {
    	String opcode = input.substring(0, 10);
    	String immeidiate = input.substring(10, 22);
    	String Rn = input.substring(22, 27);
    	String Rd = input.substring(27, 32);
    	if (opcode.equals("1001000100")) {
    		iType = Itype(1, Rn, Rd, immeidiate);
    	} else if(opcode.equals("1001001000")) {
    		iType = Itype(2, Rn, Rd, immeidiate);
    	} else if (opcode.equals("1101001000")) {
    		iType = Itype(3, Rn, Rd, immeidiate);
    	} else if (opcode.equals("1011001000")) {
    		iType = Itype(4, Rn, Rd, immeidiate);
    	} else if (opcode.equals("1101000100")) {
    		iType = Itype(5, Rn, Rd, immeidiate);
    	} else if (opcode.equals("1111000100")){
    		iType = Itype(6, Rn, Rd, immeidiate);
    	} else {
    		iType = "";
    		return;
    	}
    }
    
    private static String Itype(int type, String rn, String rd, String imme) {
    	String Rn = correctReg(rn);
    	String Rd = correctReg(rd);
    	int immeidiate = Integer.parseInt(imme, 2);
    	if (type == 1) {
    		return "ADDI " + Rd + ", " + Rn + ",  #" + immeidiate;
    	} else if (type == 2) {
    		return "ANDI " + Rd + ", " + Rn + ", #" + immeidiate;
    	} else if (type == 3) {
    		return "EORI " + Rd + ", " + Rn + ", #" + immeidiate;
    	} else if (type == 4) {
    		return "ORRI " + Rd + ", " + Rn + ", #" + immeidiate;
    	} else if (type == 5) {
    		return "SUBI " + Rd + ", " + Rn + ", #" + immeidiate;
    	} else {
    		return "SUBIS " + Rd + ", " + Rn + ", " + immeidiate;
    	}
    }
    
    public String toString() {
    	return iType;
    }
    
}