// Christian Asch y Carlos Urena
import java.util.ArrayList;

class ALU{
    private Registro[] dataRegisters;
    private Registro zeroFlag;
    public ALU(Registro[] workingDataRegisters, Registro workingZeroFlag){
        dataRegisters = workingDataRegisters; 
        zeroFlag = workingZeroFlag;
    }

    public void add(int primerSumando, int segundoSumando, int destino){
        int suma = dataRegisters[primerSumando].getRegisterValue() + dataRegisters[segundoSumando].getRegisterValue();
        dataRegisters[destino].setRegisterTo(suma);
        if(suma == 0){
            zeroFlag.setRegisterTo(1);
        } else {
            zeroFlag.setRegisterTo(0);
        }
    }
    
    public void sub(int minuendo, int sustraendo, int destino){
        int resta = dataRegisters[minuendo].getRegisterValue() - dataRegisters[sustraendo].getRegisterValue();
        dataRegisters[destino].setRegisterTo(resta);
        if(resta == 0){
            zeroFlag.setRegisterTo(1);
        } else {
            zeroFlag.setRegisterTo(0);
        }
    }
}
