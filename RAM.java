//Christian Asch y Carlos Urena
import java.util.ArrayList;
class RAM{
    private ArrayList<Integer> ram;

    public RAM(){
        ram = new ArrayList<Integer>(100); 
    }

    public void setMemoryLocationTo(int memoryLocation, int element){
        ram.add(memoryLocation, element);
    }

    public void addElement(int element){
        ram.add(element);
    }

    public int size(){
        return ram.size();
    }

    public int getMemoryLocation(int memoryLocation){
        return ram.get(memoryLocation);
    }
}
