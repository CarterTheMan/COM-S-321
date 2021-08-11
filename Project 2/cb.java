public class cb extends operator{
	
	String cbType;
	String address;
	int type;
	String register2;
	String register;
    
    public cb(String input) {
    	String opcode = (input.substring(0, 8));
    	String addr = input.substring(8, 27);
    	String Rt = input.substring(27, 32);
    	address = addr;
    	register2 = Rt;
    	register = correctReg(Rt);
    	if (opcode.equals("01010100")) {
    		cbType = CBtype(1, Rt, addr);
    		type = 1;
    	} else if (opcode.equals("10110100")) {
    		cbType = CBtype(2,Rt, addr);
    		type = 2;
    	} else if (opcode.equals("10110101")) {
    		cbType = CBtype(3, Rt, addr);
    		type = 3;
    	} else {
    		cbType = "";
    		type = -1;
    		return;
    	}
    }
    
    private static String CBtype(int type, String reg, String address) {
    	String nzcv = reg.substring(1, 5);
    	String Rt = correctReg(reg);
    	//String addr = correctReg(address);
    	if (type == 1) {
    		if (nzcv.equals("0000")) {
        		return "B.EQ " + address;
    		} else if (nzcv.equals("0001")) {
        		return "B.NE "+ address;
    		} else if (nzcv.equals("0010")) {
        		return "B.HS " + address; //cs for carry set or hs unsigned higher
    		} else if (nzcv.equals("0011")) {
        		return "B.LO " + address; //cc carry clear or lo lower unsigned
    		} else if (nzcv.equals("0100")) {
        		return "B.MI " + address;
    		} else if (nzcv.equals("0101")) {
        		return "B.PL " + address; //overflow
    		} else if (nzcv.equals("0110")) {
        		return "B.VS " + address; //overflow
    		} else if (nzcv.equals("0111")) {
        		return "B.VC " + address; //no overflow
    		} else if (nzcv.equals("1000")) {
        		return "B.HI " + address;
    		} else if (nzcv.equals("1001")) {
        		return "B.LS " + address;
    		} else if (nzcv.equals("1010")) {
        		return "B.GE " + address;
    		} else if (nzcv.equals("1011")) {
        		return "B.LT " + address;
    		} else if (nzcv.equals("1100")) {
        		return "B.GT " + address;
    		} else if (nzcv.equals("1101")) {
        		return "B.LE " + address;
    		} else {
         		return "B.cond " + address;
    		}
    	} else if (type == 2) {
    		return "CBZ " + Rt + ", " + address;
    	} else {
    		return "CBNZ " + Rt + ", " + address;
    	}
    }
    
    public void setAddress(String newAddress) {
    	switch(type) {
    	case(1):
    		String nzcv = register2.substring(1, 5);
    	if (nzcv.equals("0000")) {
    		cbType = "B.EQ " + newAddress;
    	} else if (nzcv.equals("0001")) {
    		cbType = "B.NE "+ newAddress;
    	} else if (nzcv.equals("0010")) {
    		cbType = "B.HS " +newAddress; //cs for carry set or hs unsigned higher
    	} else if (nzcv.equals("0011")) {
    		cbType = "B.LO " +newAddress; //cc carry clear or lo lower unsigned
    	} else if (nzcv.equals("0100")) {
    		cbType = "B.MI " +newAddress;
    	} else if (nzcv.equals("0101")) {
    		cbType = "B.PL " + newAddress; //overflow
    	} else if (nzcv.equals("0110")) {
    		cbType = "B.VS " + newAddress; //overflow
    	} else if (nzcv.equals("0111")) {
    		cbType = "B.VC " + newAddress; //no overflow
    	} else if (nzcv.equals("1000")) {
    		cbType = "B.HI " + newAddress;
    	} else if (nzcv.equals("1001")) {
    		cbType = "B.LS " + newAddress;
    	} else if (nzcv.equals("1010")) {
    		cbType = "B.GE " +newAddress;
    	} else if (nzcv.equals("1011")) {
    		cbType = "B.LT " + newAddress;
    	} else if (nzcv.equals("1100")) {
    		cbType = "B.GT " +  newAddress;
    	} else if (nzcv.equals("1101")) {
    		cbType = "B.LE " + newAddress;
    	} else {
    		cbType = "B.cond " + newAddress;
    	}
    		break;
    	case(2):
    		cbType = "CBZ " + register + ", " + newAddress;
    		break;
    	case(3):
    		cbType = "CBNZ " + register + ", " + newAddress;
    		break;
    	default:
    		break;
    	}
    }
    
    public String getAddress() {
    	return address;
    }
    
    public String toString() {
    	return cbType;
    }
}