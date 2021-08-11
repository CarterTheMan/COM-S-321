public class b extends operator{
    
	String bType;
	String address;
	int type;
	
    public b(String input) {
    	String opcode = input.substring(0,6);
    	String addr = input.substring(6,32);
    	address = addr;
    	if (opcode.equals("000101")) {
    		bType = Btype(1, addr);
    		type = 1;
    	} else {
    		bType = Btype(2, addr);
    		type = 2;
    	}
    }
    
    private static String Btype(int type, String address) {
    	if (type == 1) {
    		return "B " + address;
    	} else {
    		return "BL " + address;
    	}
    }
    
    public String getAddress() {
    	return(address);
    }
    
    public void setAddress(String newAddress) {
    	if(type == 1) {
    		bType = "B " + newAddress;
    	}else {
    		bType = "BL " + newAddress;
    	}
    }
    
    public String toString() {
    	return bType;
    }
}