//Christian Asch y Carlos Urena
class IR{
    private int[] instructionParts;
    private PC pc;
    private RAM ram; 

    public IR(PC workingPC, RAM workingRAM){
        pc = workingPC;
        ram = workingRAM;
    }

    public int[] loadInstruction(){
        int actualInstruction = pc.getInstructionNumber();
        int instruction = ram.getMemoryLocation(actualInstruction);
        switch(instruction){
            case 0:
            case 1:
            case 2:
            case 3:
                instructionParts = new int[3];
                for(int i = 0; i < instructionParts.length; i++){
                    instructionParts[i] = ram.getMemoryLocation(actualInstruction + i);
                }
                pc.advanceInstructionNumber(instructionParts.length);
                break;
            case 4:
            case 7:
                instructionParts = new int[2];
                for(int i = 0; i < instructionParts.length; i++){
                    instructionParts[i] = ram.getMemoryLocation(actualInstruction + i);
                }
                pc.advanceInstructionNumber(instructionParts.length);
                break;
            case 5:
            case 6:
                instructionParts = new int[4];
                for(int i = 0; i < instructionParts.length; i++){
                    instructionParts[i] = ram.getMemoryLocation(actualInstruction + i);
                }
                pc.advanceInstructionNumber(instructionParts.length);
                break;
        }
        return instructionParts;
    }
}
