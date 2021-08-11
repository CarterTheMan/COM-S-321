# COM-S-321
This is an archive of my projects from the course COM S 321 (Introduction to Computer Architecture and Machine-Level Programming) at Iowa State University from the Spring of 2021.

This course covered topics such as the LEGv8 assembly programming language, how computers do basic math in binary and how computer memory works.

# Using the LEGv8 Emulator:
In order to execute legv8asm files on ordinary computers, the LEGv8 emulator must be used. The emulator can be compiled using the following command in the same directory as the legv8emul file:

chmod +x legv8emul

After compiling the LEGv8 emulator, you should be able to use the emulator like so:

./legv8emul <LEGv8 file>
  
Optional parameters
  
-a: Run the emulator as an assembler, outputing a .machine file
  
-b: Run the emulator in binary emulation mode

# Project 1 
 This is a LEGv8 program that implements selection sort. For more details on project 1, you can read the readme in that project.

# Project 2 
This is a LEGv8 disassembler written in Java. It takes a binary iput file and converts the binary into LEGv8 code. For mroe details on project 2, you can read the readme in that project. 
