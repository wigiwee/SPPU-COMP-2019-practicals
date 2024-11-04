import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pass1Assembler {
    
    // ArrayList<String[]> asm_code;
    String asm_code[][] = {
        {"","","","200",""},
        {"","","MOVER","AREG","20"},
        {"","","START","300",""},
        {"","","","",""},
        {"","","","",""},
        {"","","","",""},
        {"","","","",""},
        {"","","","",""},
    };

    ArrayList<String[]> opcode;
    int startValue;

    public Pass1Assembler(String inputFilename, String opcodeFilename) throws FileNotFoundException, IOException{
        
        //reading opcode from opcode file
        opcode = new ArrayList<>();
        FileReader opcodeReader = new FileReader(opcodeFilename);

        try (BufferedReader opcodeBufferedReader = new BufferedReader(opcodeReader)) {
            while(opcodeBufferedReader.ready()){
                opcode.add(opcodeBufferedReader.readLine().split(" "));
            }
        }        

        // //printing opcode
        // for (String[] test : opcode) {
        //     System.out.println(Arrays.toString(test));
        // }

        //reading input asm code from input file
        // asm_code = new ArrayList<>();
        // FileReader asmFileReader = new FileReader(inputFilename);
        
        // try (BufferedReader asmFileBufferedReader = new BufferedReader(asmFileReader)) {
        //     while(asmFileBufferedReader.ready()){
        //         String line ="\t"+ asmFileBufferedReader.readLine();
        //         asm_code.add(line.split("\t"));
        //     }
        // }
        
        // //printing asm_code
        // for (String[] test : asm_code) {
        //     System.out.println(Arrays.toString(test));
        // }
        for(int i = 0; i <asm_code.length; i++){
            for(int j = 0 ; j <asm_code[i].length; j++){
                if(asm_code[i][j] == "START"){
                    startValue = Integer.parseInt(asm_code[i][j+1]);
                }
            }
            if(i == 0 || startValue == 0)
                continue;
            
            asm_code[i][0] = Integer.toString(startValue++);
            
        }
    }

    public void compile() throws IOException {
        System.out.println("Ready to compile");
        for (String[] line  : asm_code) {
            System.out.println(Arrays.toString(line));
            
        }
    }
    public static void main(String[] args) throws IOException {
        Pass1Assembler assembler = new Pass1Assembler("input.txt", "opcode.txt");
        assembler.compile();
    }
}

