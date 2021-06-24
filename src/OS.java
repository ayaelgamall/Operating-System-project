import java.io.*;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;

public class OS {

    private static Word [] memory= new Word[1000];
    private static int PID =0; //increment with each process
    public static int index=99;
    public static int pcbIndex =-1;
    static LinkedList<Integer> readyQueue=new LinkedList<>() ;

    public static void print(String x) {
        System.out.println(readMemory(x));
    }

    private static String readMemory(String x) {
        //if exists in memory get it else return the same x
//         return variables.getOrDefault(x, x);
        //todo get vars from memory

        return null;
    }

    public static void assign(String variable, String value, int lower) {
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

    public static void writeFile(String fileNameVar, String dataVar, int lower) throws IOException {
        String fileName= readMemory(fileNameVar);
        String  data = readMemory(dataVar);
        File file = new File( fileName );
        if (file.createNewFile())
            System.out.println("File has been created.");
         else
            System.out.println("File already exists.");

        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.close();
    }

    public static String readFile(String fileNameVar) {
        String fileName= readMemory(fileNameVar);
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

    public static int initializePCB(int lower,int pc) {
        int id =assignID();
        memory[++pcbIndex] =  new Word("ID:",id );
        memory[++pcbIndex] =  new Word("Upper Boundary",index+1);
        memory[++pcbIndex] = new Word("lower Boundary", lower);
        memory[++pcbIndex] =  new Word("startPC",pc);
        memory[++pcbIndex] =  new Word("State", state.NotRunnig);
        return id;
    }
    public static int storeProgramInstructions(String filePath) throws IOException {
        //7ad yerage3 waraya
        //BufferedReader lel program file
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //put each instruction in a new index fel memory
        int lowerBoundary = index+1; //wla ++index idk?
        index += 3;
        int pc = index+1;//leaving space for variables
        while(br.ready()){
             memory[++index] =  new Word("Instruction",br.readLine());
        }
       return initializePCB(lowerBoundary,pc);
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

       readyQueue.add( storeProgramInstructions("Program 1.txt"));
       readyQueue.add( storeProgramInstructions("Program 2.txt"));
       readyQueue.add( storeProgramInstructions("Program 3.txt"));

       scheduler() ;


    }

    private static void scheduler() throws IOException {
        int oldId =-1;
        int i=0;
        while (!readyQueue.isEmpty()){
             int id = readyQueue.poll();
             int upper = 0;
             int pc= 0;
             int lower=0;
             int pcIdx = 0;
             int stateIdx = 0;
             if(id!= oldId) {
                 i=0; oldId=id;
             }
            for (int k = 0; k <100 ; k+=5)
                if(memory[k]!=null && memory[k].value.equals(id)){
                   upper= (int) memory[k+1].value;
                   lower= (int) memory[k+2].value;
                   pc= (int) memory[k+3].value;
                   pcIdx=k+3;
                   stateIdx=k+4;
                   break;
                }
             for (int j =i+2 ; i<j && pc<upper ;i++){
                 memory[stateIdx].value=state.Running;
                 Parser.interpret((String) memory[pc].value,lower);
                (memory[pcIdx].value)= ++pc;
             }
            memory[stateIdx].value=state.NotRunnig;
             if(pc==upper)
                 delete(id);
             else
                 readyQueue.add(id);
        }
    }

    private static void delete(int id) {
    }

    public enum state {
        Running,
        NotRunnig
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
