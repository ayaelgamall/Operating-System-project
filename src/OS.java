import java.io.*;
import java.util.Hashtable;
public class OS {

    private static Hashtable<String, String> variables = new Hashtable<>();
    private static Word [] memory= new Word[1000];
    private static int PID =0; //increment with each process
    static int PC = 0; //idk hngebo mnen
    public static int index=-1;

    public static void print(String x) {
        System.out.println(readMemory(x));
    }

    private static String readMemory(String x) {
        //if exists in memory get it else return the same x
        return variables.getOrDefault(x, x);
    }

    public static void assign(String variable, String value) {
        writeMemory(variable, value);
    }

    private static void writeMemory(String variable, String value) {
         variables.put(variable, value);
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

    public static void initializePCB(String process) {
        memory[++index] = new Word("lower Boundary", index+1);
        memory[++index] =  new Word("startPC",PC);
        memory[++index] =  new Word("ID:", assignID());
        memory[++index] =  new Word("State", state.NotRunnig);
        memory[++index] =  new Word("Upper Boundary",index+1);
        //mesh el boundaries el mafroud bta3et el instructions nafsaha mesh el PCB?
    }
    public static void storeProgramInstructions(String filePath) {
        //7ad yerage3 waraya pls
        //BufferedReader lel program file
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //put each instruction in a new index fel memory
        while(br.hasNext()){
             memory[++index] =  new Word("Instruction",br.next()); //lw b2et mem[index++] 5ly el boundaryS index++
        }
    }
    public static int assignID() {
        return PID++;
    }
    public static void main(String[] args) {
        //read processes in order
        //call initializeProcess for each one
        //store variables (in memory brdo)
        //after doing that for all 3 processes
        //start scheduler
        //2 instruction for each process then preemt
        //keep going till all processes finished
    }

    public enum state {
        Running,
        NotRunnig,
        Finished
    }

    public static class Word {
        String key;
        Object value;
        public Word(key k, Object v){
            value=v;
            key=k;
        }

    }
}
