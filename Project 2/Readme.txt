Group Members:
Carter Moseley, Max Sutcliffe, and Kobe Hass


How to Execute:
1. Call "bash Build.sh": this will build our program by calling the java compiler for all *.java files.

2. Call "bash Run.sh filename.legv8asm.machine" with the given file name: this will just run our program with the input of the "filename.legv8asm.machine" file inputted.
2a. If you'd like it to be printed into a file rather than printed to the console, call "bash Run.sh filename.legv8asm.machine >> output.txt" instead!

3. Once you are finished, call "bash Clean.sh" for a quick cleanup of all the .class files. This doesn't clean everything, it only deletes all the .class files so you may compiler it again if need be.

How it Works:
- Overall: Our program here takes in 4 bytes (using a FileInputStream, very handy) at a time from the .machine file, and immediately calls analyzeInstruction them them to put them into an array list of all of our instructions. Once we finish analyzing all the instructions we look for branch targets that were used by all B and CB type calls, and we remember where they are going to go to. Once we iterate through all of that, we add them into our array list right before our final call, and we only add in the ones that were actually targeted by B and CB calls. Once we finish, we iterate through our array list and call a toString() for each instruction.
- analyzeInstruction: What our analyzeInstruction does it just compare the OpCode for the current instruction to the instructions we have to support. In general it's a bunch of switch statements for each big kind of instruction type. When it finds the one we're looking for, we add a new operator to our array list of that specific type and continue onto the next instruction in the main.
- operator.java and all subclasses: This is probably the most important thing for us, as we collected a bunch of these classes subclasses to call a .toString onto. This class just defines a method we'd like to use for all the subclasses, and it also additionally acts as a type for our array list.
- toString: the toString method for each of the operator.java classes has been formatted to output the correct String we'd like to print to the console for each type of instruction we have to support.
