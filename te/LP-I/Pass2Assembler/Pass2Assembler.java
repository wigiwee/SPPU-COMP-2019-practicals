import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Pass2Assembler {
    
    private Pass1Assembler pass1Assembler;
    public ArrayList<ArrayList<String>> machineCode;

    public Pass2Assembler() {
        pass1Assembler = new Pass1Assembler("input.txt", "opcode.txt");
        pass1Assembler.compile();

    }

    public ArrayList<ArrayList<String>> generateMachineCode(){
        ArrayList<ArrayList<String>> mc = new ArrayList<>();


        return mc;
    }
    
    public static void main(String[] args) {
        Pass2Assembler pass2Assembler = new Pass2Assembler();
    }
}