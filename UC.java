// Christian Asch y Carlos Urena

import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

class UC{
    private RAM ram;
    private Registro[] dataRegisters;
    private Registro zeroFlag;
    private ALU alu;
    private IR ir;
    private PC pc;
    private int[] currentInstruction;

    private int decoder(String instruction){
        int decodedInstruction = -1;
        switch(instruction){
            case "LOAD":
                decodedInstruction = 0;
                break;
            case "STORE":
                decodedInstruction = 1;
                break;
            case "LOADI":
                decodedInstruction = 2;
                break;
            case "STOREI":
                decodedInstruction = 3;
                break;
            case "JMP":
                decodedInstruction = 4;
                break;
            case "ADD":
                decodedInstruction = 5;
                break;
            case "SUB":
                decodedInstruction = 6;
                break;
            case "BZ":
                decodedInstruction = 7;
                break;
        }
        return decodedInstruction;
    }

    public void execute(){
        while(pc.getInstructionNumber() < ram.size()){
            currentInstruction = ir.loadInstruction();
        } //Se recorre una vez el programa sin ejecutarlo para ver en donde empiezan las instrucciones
        pc.resetInstructionNumber();
        while(pc.getInstructionNumber() < ram.size()){
            currentInstruction = ir.loadInstruction();
            switch(currentInstruction[0]){
                case 0:
                    load(currentInstruction[1], currentInstruction[2]);
                    break;
                case 1:
                    store(currentInstruction[1], currentInstruction[2]);
                    break;
                case 2:
                    loadi(currentInstruction[1], currentInstruction[2]);
                    break;
                case 3:
                    storei(currentInstruction[1], currentInstruction[2]);
                    break;
                case 4:
                    jump(currentInstruction[1]);
                    break;
                case 5:
                    alu.add(currentInstruction[1], currentInstruction[2], currentInstruction[3]);
                    break;
                case 6:
                    alu.sub(currentInstruction[1], currentInstruction[2], currentInstruction[3]);
                    break;
                case 7:
                    branchZero(currentInstruction[1]);
                    break;
            }
//            System.out.print(dataRegisters[0].getRegisterValue());
//            System.out.print("\t");
//            System.out.println(dataRegisters[1].getRegisterValue());
        }
    }

    public void loadInstructionsToRam(String filename){
         String line = null;
         String args = null;
         String [] argsProcessed = new String[3];
         try{
            FileReader fileReader = new FileReader(filename);
            BufferedReader br = new BufferedReader(fileReader);
            while((line = br.readLine()) != null){
                this.ram.addElement(this.decoder(line.split(" ")[0]));
                args = line.split(" ")[1].trim();
                if(args.split(",").length > 1){
                    for(int i = 0; i < args.split(",").length; i++){
                        argsProcessed[i] = args.split(",")[i].trim();
                        if(argsProcessed[i].charAt(0) == 'R'){
                            this.ram.addElement(Integer.parseInt(argsProcessed[i].substring(1,2))); 
                        } else {
                            this.ram.addElement(Integer.parseInt(argsProcessed[i].substring(0,1))); 
                        }
                    }
                } else {
                    this.ram.addElement(Integer.parseInt(args.substring(1,2))); 
                }
            }
            br.close();
         } 
         catch(FileNotFoundException e){
            System.out.println("El archivo " + filename + " no existe");
         }
         catch(IOException e){
            e.printStackTrace();
         }
    }

    public void loadi(int register, int number){
        dataRegisters[register].setRegisterTo(number);
    }

    public void storei(int memLocation, int number){
        ram.setMemoryLocationTo(memLocation, number);
    }

    public void load(int register, int memLocation){
        dataRegisters[register].setRegisterTo(ram.getMemoryLocation(memLocation));
    }
    
    public void store(int register, int memLocation){
        ram.setMemoryLocationTo(memLocation, dataRegisters[register].getRegisterValue());
    }

    public void jump(int instruction){
        pc.setInstructionNumber(pc.getLocationOfInstruction(instruction));
    }
    
    public void branchZero(int instruction){
        if(zeroFlag.getRegisterValue() == 1){
            jump(instruction);
        }
    }

    public UC(){
        ram = new RAM();
        dataRegisters = new Registro[3];
        for(int i = 0; i < dataRegisters.length; i++){
            dataRegisters[i] = new Registro();
        }
        zeroFlag = new Registro();
        alu = new ALU(dataRegisters, zeroFlag);
        pc = new PC();
        ir = new IR(pc, ram);
    }

    public static void main(String[] argv){
        UC uc = new UC();
        if(argv.length == 1){
            System.out.println("Loading...");
            uc.loadInstructionsToRam(argv[0]);
            System.out.println("Executing...");
            uc.execute();
        } else {
            System.out.println("Uso del programa: java UC <archivo con las instrucciones>");
        }
    }
}
