import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Pass1Assembler {
    
    ArrayList<String[]> opcode;
    ArrayList<ArrayList<String>> asm_code;
    ArrayList<ArrayList<String>> ic = new ArrayList<>();
    ArrayList<ArrayList<String>> literalTable = new ArrayList<>();
    ArrayList<ArrayList<String>> symbolTable = new ArrayList<>();
    ArrayList<ArrayList<String>> poolTable =new ArrayList<>();
    int startValue;

    public Pass1Assembler(String inputFilename, String opcodeFilename) {

        try {
            opcode = processOpcodeFile(opcodeFilename);        
        } catch (FileNotFoundException e) {
            System.out.println("input file with given name not found");
            System.exit(1);    
        } catch (IOException e){
            System.out.println("Cannot read from the given input file");
            System.exit(1);
        }

        try {
            asm_code = processInputFile(inputFilename);        
        } catch (FileNotFoundException e) {
            System.out.println("opcode file with given name not found");    
            System.exit(1);    
        } catch (IOException e){
            System.out.println("Cannot read from the given opcode file");
            System.exit(1);
        }
        
        // printOpcode();
        asm_code = assignLc();
        // print_asmcode();
        // print(asm_code);
    }

    //assigning location counter
    public ArrayList<ArrayList<String>> assignLc(){
        ArrayList<ArrayList<String>> asmcode_withLc = asm_code;
        startValue = 0;
        for( int i =0 ; i < asmcode_withLc.size(); i++){
            for( int j =0; j< asmcode_withLc.get(i).size(); j++){
                if(asmcode_withLc.get(i).get(j).equals("START"))
                    startValue = Integer.parseInt(asmcode_withLc.get(i).get(j+1));
                    break;
            }
            if(startValue != 0)
                asmcode_withLc.get(i).addFirst(Integer.toString(startValue++));
        }
        return asmcode_withLc;
    }

    //printing opcode
    public void printOpcode(){
        System.out.println("----------------------------------");
        System.out.println("opcode Chart :");
        for (String[] code : opcode) {
            System.out.println(Arrays.toString(code));
        }
    }

    //printing asm_code (this function is not used anymore)
    public void print_asmcode(){
        System.out.println("----------------------------------");
        System.out.println("asmcode :");
        for (ArrayList<String> line : asm_code) {
            for (String word :  line)
                System.out.print(word+" ");       
            System.out.println();
        }
    }

    //printing general list structure
    public void print(ArrayList<ArrayList<String>> list){
        System.out.println("======================================");
        for (ArrayList<String> line : list) {
            for (String word :  line)
                System.out.print(word+" ");       
            System.out.println();
        }
    }

    //reading opcode from opcode file
    public ArrayList<String[]> processOpcodeFile(String filename) throws FileNotFoundException, IOException{
        ArrayList<String[]> opcodeArrayList = new ArrayList<>();
        FileReader opcodeReader = new FileReader(filename);

        try (BufferedReader opcodeBufferedReader = new BufferedReader(opcodeReader)) {
            while(opcodeBufferedReader.ready()){
                String line = opcodeBufferedReader.readLine();
                if(line.isBlank())
                    continue;
                String[] lineArray = line.split(" "); 
                for(int i =0; i< lineArray.length; i++)
                    lineArray[i] = lineArray[i].trim();
                opcodeArrayList.add(lineArray);
            }
        }
        return opcodeArrayList;
    }

    //creating and writing an output file
    public void createOutputFile(){
        File file = new File("output.txt");
        try{
            file.createNewFile();

        }catch(IOException e){
            System.out.println("Something went wrong while creating new file");
        }
        FileWriter writer;
        try {
            writer = new FileWriter("output.txt");
            writer.write("IC");
            writer.write("\n");

            for(ArrayList<String> line : this.ic){
                for(String word : line){
                    writer.write(word);
                    writer.write(" ");
                }
                writer.write("\n");
            }    
            writer.write("LT");
            writer.write("\n");

            for(ArrayList<String> line : this.literalTable){
                for(String word: line){
                    writer.write(word);
                    writer.write(" ");
                }
                writer.write("\n");
            }
            writer.write("ST");
            writer.write("\n");

            for (ArrayList<String> line  : this.symbolTable) {
                for (String word  : line) {
                    writer.write(word);
                    writer.write(" ");
                }
                writer.write("\n");                
            }
            writer.write("PT");
            writer.write("\n");
            for (ArrayList<String> line  : this.poolTable) {
                for (String word  : line) {
                    writer.write(word);
                    writer.write(" ");
                }
                writer.write("\n");
            }
            
            writer.close();
        } catch (Exception e) {
            System.out.println("Cannot write to the output file");
        }
 
    }
    
    //reading input asm code from input file and also removing comments
    public ArrayList<ArrayList<String>> processInputFile(String filename) throws FileNotFoundException, IOException{
        ArrayList<ArrayList<String>> asmcode = new ArrayList<>();
        FileReader asmFileReader = new FileReader(filename);
        
        boolean isComment = false;
        try (BufferedReader asmFileBufferedReader = new BufferedReader(asmFileReader)) {
            while(asmFileBufferedReader.ready()){
                String line = asmFileBufferedReader.readLine();
                if( line.isEmpty() || line.startsWith("#"))
                    continue;
                String wordsArray[] = line.split("\\s+");
                ArrayList<String> wordsArrayList = new ArrayList<>();
                for (String word  : wordsArray) {
                    if(word.isEmpty() || isComment == true)
                        continue;
                    if(word.startsWith("#")){
                        isComment = true;
                        continue;
                    }
                    if(word.endsWith(",")){
                        word = word.substring(0, word.length() -1);
                    }
                    wordsArrayList.add(word);
                }
                isComment = false;
                asmcode.add(wordsArrayList);
            }
        }
        return asmcode;

    }
    
    public void compile() throws IOException {
        String icStr, symStr, litStr;
        for(int i =0 ; i< asm_code.size(); i++){
            ArrayList<String> icLine = new ArrayList<>();
            ArrayList<String> symLine = new ArrayList<>();
            ArrayList<String> poolLine = new ArrayList<>();
            ArrayList<String> literalLine = new ArrayList<>();
            for(int j = 1; j< asm_code.get(i).size(); j++){
                for(int k =0; k < opcode.size(); k++){
                    if(opcode.get(k)[0].equals(asm_code.get(i).get(j))){
                        icStr = "(" + opcode.get(k)[1] + "," + opcode.get(k)[2] + ")";
                        icLine.add(icStr);
                    }
                }

                if( asm_code.get(i).get(j).equals(opcode.get(16)[0]) || asm_code.get(i).get(j).equals(opcode.get(17)[0])){
                    symStr = "(" + asm_code.get(i).get(0) + "," + asm_code.get(i).get(j) + ")";
                    symLine.add(symStr);
                }
                if ( asm_code.get(i).get(j).matches("[A-Za-z]")){
                    icStr = "(S," + asm_code.get(i).get(j) + ")";
                    icLine.add(icStr);                       
                }
                if( asm_code.get(i).get(j).matches("[0-9]+")){
                    icStr = "(C," + asm_code.get(i).get(j) + ")";
                    litStr = "(" + asm_code.get(i).get(0) + "," + asm_code.get(i).get(j) + ")";
                    icLine.add(icStr);
                    literalLine.add(litStr);
                }
                if( asm_code.get(i).get(j).equals(opcode.get(15)[0]) || asm_code.get(i).get(j).equals(opcode.get(12)[0])){
                    String poolStr = "(" + asm_code.get(i).get(0) + "," + asm_code.get(i).get(j) + ")";
                    poolLine.add(poolStr);
                }
            }
            
            ic.add(icLine);
            symbolTable.add(symLine);
            literalTable.add(literalLine);
            poolTable.add(poolLine);   
            createOutputFile();
        }
    }

    public static void main(String[] args) throws IOException {
        Pass1Assembler assembler = new Pass1Assembler("input.txt", "opcode.txt");
        assembler.compile();
        
        System.out.println("asmcode");
        assembler.print(assembler.asm_code);
        System.out.println();

        System.out.println("opcode");
        assembler.printOpcode();
        System.out.println();

        System.out.println("intermediate code");
        assembler.print(assembler.ic);
        System.out.println();

        System.out.println("literal table");
        assembler.print(assembler.literalTable);
        System.out.println();

        System.out.println("symbol table");
        assembler.print(assembler.symbolTable);
        System.out.println();

        System.out.println("pool table");
        assembler.print(assembler.poolTable);
        System.out.println();
    }
}