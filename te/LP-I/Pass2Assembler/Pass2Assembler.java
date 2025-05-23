import java.util.ArrayList;

public class Pass2Assembler {
    
    private Pass1Assembler pass1Assembler;
    public ArrayList<ArrayList<String>> machineCode = new ArrayList<>();
    public ArrayList<ArrayList<String>> ic;

    public Pass2Assembler() {
        pass1Assembler = new Pass1Assembler("input.txt", "opcode.txt");
        pass1Assembler.compile();
        this.ic = pass1Assembler.ic;
    }

    public void compile(){
        ArrayList<ArrayList<String>> mc = new ArrayList<>();
        
        for(int i =1 ; i < ic.size(); i++){
            ArrayList<String> line = new ArrayList<>();
            if(!ic.get(i).contains("AD")){
                for(int j =0 ; j< ic.get(i).size(); j++){
                    if(ic.get(i).get(j).isEmpty()){
                        continue;
                    }
                    if(ic.get(i).get(j).contains("IS")){
                        String[] str = ic.get(i).get(j).split(",");
                        line.add(str[1].substring(0,str[1].length()-1));
                    }
                    if(ic.get(i).get(j).contains("RG")){
                        String[] str = ic.get(i).get(j).split(",");
                        line.add(str[1].substring(0,str[1].length()-1));
                    }
                    if(ic.get(i).get(j).contains("DL")){
                        String[] str = ic.get(i).get(j).split(",");
                        line.add(str[1].substring(0,str[1].length()-1));
                    }
                    if(ic.get(i).get(j).charAt(1) == 'C'){
                        String[] str = ic.get(i).get(j).split(",");
                        line.add(str[1].substring(0,str[1].length()-1));
                        String[] addr = pass1Assembler.literalTable.get(i).get(0).split(",");
                        line.add(addr[1].substring(0,str[1].length()-1));
                    }
                }
            }
            mc.add(line);
        }
        this.machineCode = mc;
    }
    
    public static void main(String[] args) {
        Pass2Assembler pass2Assembler = new Pass2Assembler();
        pass2Assembler.compile();
        System.out.println("Intermediate code (generated by Pass1Assembler)");
        pass2Assembler.pass1Assembler.print(pass2Assembler.ic);
        System.out.println("Pass2 Assembler ");
        pass2Assembler.pass1Assembler.print(pass2Assembler.machineCode);
    }
}