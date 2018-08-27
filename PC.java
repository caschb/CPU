//Christian Asch y Carlos Urena
import java.util.ArrayList;
class PC{
    private int instructionNumber;
    private ArrayList<Integer> locations;

    private void addLocation(int locationToAdd){
        boolean failure = false;
        for(int i = 0; !failure && i < locations.size(); i++){
            if(locations.get(i) == locationToAdd){
                failure = true; 
            }
        }
        if(!failure){
            locations.add(locationToAdd);
        }
    }

    public PC(){
        instructionNumber = 0; 
        locations = new ArrayList<Integer>();
        locations.add(instructionNumber);
    }

    public void setInstructionNumber(int newInstructionNumber){
        instructionNumber = newInstructionNumber; 
        addLocation(instructionNumber);
    }
    
    public void resetInstructionNumber(){
        instructionNumber = 0; 
    }

    public void advanceInstructionNumber(int offset){
        instructionNumber += offset; 
        addLocation(instructionNumber);
    }

    public int getInstructionNumber(){
        return instructionNumber; 
    }

    public int getLocationOfInstruction(int location){
        return locations.get(location);
    }
}
