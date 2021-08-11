import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Decompiler {
	
	
	
	public static void main(String[] args) throws IOException {
		if(args.length != 1) {
			System.out.println("Please give the .machine file along with the Run.sh call" + '\n');
			return;
		}
		String file = args[0];
		ArrayList<operator> instructions = new ArrayList<operator>();
		/*
		 * Currently this code takes in the file locally
		 * and prints out all the machine code
		 * analyzeInstruction() is nearly ready, and
		 * that's what I will continue with tomorrow
		 */
		File f = new File(file);
		FileInputStream scan = new FileInputStream(f);
		int instructionLength = 4;
		byte[] rawInstruction = new byte[instructionLength];
		int totalInstructions = 0;
		String instruction = "";
		int readCount = 0;
		//scan.read(rawInstruction);
		while(scan.read(rawInstruction) != -1) {
			for(int i = 0; i < instructionLength; i++) {
				String b = Integer.toHexString(rawInstruction[i]);
				instruction += String.format("%8s", Integer.toBinaryString(rawInstruction[i] & 0xFF)).replace(' ', '0');
			}
			//System.out.println(instruction);
			analyzeInstruction(instruction, instructions);
			instruction = "";
		}
		int totalBranches = 0;
		//ArrayList<bTarget> branchTargets = new ArrayList<bTarget>();
		/*
		 * For every branch we find, we want to memorize a location target for it, and put it in later
		 */
		bTarget[] isBranch = new bTarget[instructions.size()];
		for(int i = 0; i < isBranch.length; i++) {
			isBranch[i] = new bTarget();
		}
		for(int i = 0; i < instructions.size(); i++) {
			if(instructions.get(i) instanceof b) {
				b branch = (b)instructions.get(i);
				String branchTarget = branch.getAddress();
				int index = i;
				if(branchTarget.charAt(0) == '0') {
					index += Integer.parseInt(branchTarget.substring(1), 2);
				}else {
					index += -Integer.parseInt(branchTarget.substring(1), 2);
				}
				if(!isBranch[index].isUsed()) {
					isBranch[index] = new bTarget("Branch" + totalBranches, index);
					//branchTargets.add(new bTarget("Branch " + totalBranches, index));
					totalBranches++;
					isBranch[index].setUsed();
					branch.setAddress("Branch" + isBranch[index].branchTarget);
				}else {
					branch.setAddress("Branch" + isBranch[index].branchTarget);
				}
				/*calculate index + offset for the branch instruction
				branchTargets.add(new bTarget("Branch " + totalBranches, index + offset));
				totalBranches++;*/
			}else if(instructions.get(i) instanceof cb) {
				cb branch = (cb)instructions.get(i);
				String branchTarget = branch.getAddress();
				int index = i;
				if(branchTarget.charAt(0) == '0') {
					index += Integer.parseInt(branchTarget.substring(1), 2);
				}else {
					index += -Integer.parseInt(branchTarget.substring(1), 2);
				}
				if(!isBranch[index].isUsed()) {
					isBranch[index] = new bTarget("Branch" + totalBranches, index);
					//branchTargets.add(new bTarget("Branch " + totalBranches, index));
					totalBranches++;
					branch.setAddress("Branch" + isBranch[index].branchTarget);
					isBranch[index].setUsed();
				}else {
					branch.setAddress("Branch" + isBranch[index].branchTarget);
				}
			}
		}
		totalBranches = 0;
		for(int i = 0; i < isBranch.length; i++) {
			if(isBranch[i].isUsed()) {
				instructions.add(isBranch[i].getTarget() + totalBranches, isBranch[i]);
				totalBranches++;
			}
		}
		for(int i = 0; i < instructions.size(); i++) {
			System.out.println(instructions.get(i).toString());
		}
	}
	
	/**
	 * This will become the method that puts the instructions onto our list, as it must recognize them, and then call the specific types constructor and add it to the list.
	 * @param instruction
	 */
	public static void analyzeInstruction(String instruction, ArrayList<operator> instructions) {
		String maxOpCode = instruction.substring(0, 11);
		boolean instructionDefined = false;
		//r-type and d-type check
		switch(maxOpCode) {
		case("10001011000"):
			//add
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("10001010000"):
			//and
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11010110000"):
			//BR
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11001010000"):
			//EOR
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11010011011"):
			//LSL
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11010011010"):
			//LSR
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("10101010000"):
			//ORR
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11001011000"):
			//SUB
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11101011000"):
			//SUBS
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("10011011000"):
			//MUL
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11111111101"):
			//PRNT
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11111111100"):
			//PRNL
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11111111110"):
			//DUMP
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11111111111"):
			//HALT
			instructions.add(new r(instruction));
			instructionDefined = true;
			break;
		case("11111000010"):
			//LDUR
			instructions.add(new d(instruction));
			instructionDefined = true;
			break;
		case("11111000000"):
			//STUR
			instructions.add(new d(instruction));
			instructionDefined = true;	
			break;
		default:
			break;
		}
		if(instructionDefined) {
			return;
		}
		//substring it again
		maxOpCode = maxOpCode.substring(0, 10);
		//i-type check
		switch(maxOpCode) {
		case("1001000100"):
			//ADDI
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		case("1001001000"):
			//ANDI
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		case("1101001000"):
			//EORI
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		case("1011001000"):
			//ORRI
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		case("1101000100"):
			//SUBI
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		case("1111000100"):
			//SUBIS
			instructions.add(new i(instruction));
			instructionDefined = true;
			break;
		default:
			break;
		}
		if(instructionDefined) {
			return;
		}
		maxOpCode = maxOpCode.substring(0, 8);
		//CB-type
		switch(maxOpCode) {
		case("01010100"):
			//B.COND
			instructions.add(new cb(instruction));
			instructionDefined = true;
			break;
		case("10110101"):
			//CBNZ
			instructions.add(new cb(instruction));
			instructionDefined = true;
			break;
		case("10110100"):
			//CBZ
			instructions.add(new cb(instruction));
			instructionDefined = true;
			break;
		default:
			break;
		}
		if(instructionDefined) {
			return;
		}
		maxOpCode = maxOpCode.substring(0, 6);
		switch(maxOpCode) {
		case("000101"):
			//B
			instructions.add(new b(instruction));
			instructionDefined = true;
			break;
		case("100101"):
			//BL
			instructions.add(new b(instruction));
			instructionDefined = true;
			break;
		}
	}

}