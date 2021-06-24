import java.io.*;
import java.util.Hashtable;
public class OS {

    private static Word [] memory= new Word[1000];
    private static int PID =0; //increment with each process
    static int PC = 0; //idk hngebo mnen
    public static int index=99;
    public static int pcbindex=-1;

    public static void print(String x) {
        System.out.println(readMemory(x));
    }

    private static String readMemory(String x) {
        //if exists in memory get it else return the same x
//         return variables.getOrDefault(x, x);
        //todo get vars from memory

        return null;
    }

    public static void assign(String variable, String value) {
        writeMemory(variable, value);
    }

    private static void writeMemory(String variable, String value) {
//        variables.put(variable, value);
        //todo this store the variables in the memory itself
    }

    public static void add(String x, String y) {
        int i1 = Integer.parseInt(readMemory(x));
        int i2 = Integer.parseInt(readMemory(y));
        int res= i1 + i2;
        writeMemory(x,""+res);
    }

    public static void writeFile(String fileNameVar, String dataVar) throws IOException {
        String fileName= readMemory(fileNameVar);
        String  data = readMemory(dataVar);
        File file = new File( fileName );
        if (file.createNewFile())
            System.out.println("File has been created.");
         else
            System.out.println("File already exists.");

//        String filePath = "src/"+ fileName +".txt";
        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.close();
    }

    public static String readFile(String fileNameVar) {
        String fileName= readMemory(fileNameVar);
//        String filePath = "src/"+ fileName +".txt";
        StringBuilder data = new StringBuilder();

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready())
                data.append(br.readLine());
        } catch (Exception e) {
            print("File Does not Exist");
        }
        return data.toString();
    }

    public static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static void initializePCB(int lower) {
        index += 3; //leaving space for variables
        memory[++pcbindex] =  new Word("ID:", assignID());
        memory[++pcbindex] = new Word("lower Boundary", lower);
        memory[++pcbindex] =  new Word("Upper Boundary",index+1);
        memory[++pcbindex] =  new Word("startPC",PC);
        memory[++pcbindex] =  new Word("State", state.NotRunnig);
    }
    public static void storeProgramInstructions(String filePath) throws IOException {
        //7ad yerage3 waraya
        //BufferedReader lel program file
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //put each instruction in a new index fel memory
        int lowerBoundary = index+1; 
        while(br.ready()){
             memory[++index] =  new Word("Instruction",br.readLine());
        }
        initializePCB(lowerBoundary);
    }
    public static int assignID() {
        return PID++;
    }
    public static void main(String[] args) throws IOException {
        //read processes in order - done
        //call storeProgramInstructions
        //store variables (in memory brdo)
        //after doing that for all 3 processes
        //start scheduler
        //2 instruction for each process then preempt
        //keep going till all processes finished

        storeProgramInstructions("Program 1.txt");
        storeProgramInstructions("Program 2.txt");
        storeProgramInstructions("Program 3.txt");


    }

    public enum state {
        Running,
        NotRunnig,
        Finished
    }

    public static class Word {
        String key;
        Object value;
        public Word(String k, Object v){
            value=v;
            key=k;
        }

    }
}
