public class im extends operator{
	
	String imType;
    
    public im(String type) {
    	String opCode = type.substring(0,9);
    	String shift = type.substring(9,11);
    	String move_im = type.substring(11, 27);
    	String rd = type.substring(27,32);
    	if (opCode.equals("110100101")) {
    		imType = MovZ(opCode, shift, move_im, rd);
    	} else {
    		return;
    	}
    
    }
    
    public static String MovZ(String opcode, String shift, String move_im, String rd) {
    	String op = correctReg(opcode);
    	String Shift = correctReg(shift);
    	String immediate = correctReg(move_im);
    	String Rd = correctReg(rd);
    	return "MovZ";
    }
    
    public String toString() {
    	return imType;
    }
}