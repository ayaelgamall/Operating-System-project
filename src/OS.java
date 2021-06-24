import java.io.*;
import java.util.LinkedList;

public class OS {

    private static Word [] memory= new Word[1000];
    private static int PID =0; //increment with each process
    public static int index=99;
    public static int pcbIndex =-1;
    static LinkedList<Integer> readyQueue=new LinkedList<>() ;

    public static void print(String x, int lower) {
        System.out.println(readMemory(x,lower));
    }

    private static String readMemory(String x, int i) {
        //if exists in memory get it else return the same x
//         return variables.getOrDefault(x, x);

        for (;  memory[i]!=null && !memory[i].key.equals("Instruction"); i++) {
            if (memory[i].key.equals(x)){
                String  res= (String) memory[i].value;
                System.out.println("Word read from the memory is :" +memory[i].toString()+" , its index is :"+i);
                return res;
            }
        }
        return x;
    }

    public static void assign(String variable, String value, int lower) {
        writeMemory(variable, value,lower);
    }

    private static void writeMemory(String variable, String value, int i) {
//        variables.put(variable, value);
        for (; i<memory.length; i++) {
            if(memory[i]!=null) continue;
            memory[i]= new Word(variable,value);
            System.out.println("Word written to the memory is :" +memory[i].toString()+" , its index is :"+i);
            return;
        }
    }

    public static void add(String x, String y, int lower) {
        int i1 = Integer.parseInt(readMemory(x,lower));
        int i2 = Integer.parseInt(readMemory(y,lower));
        int res= i1 + i2;
        writeMemory(x,""+res, lower);
    }

    public static void writeFile(String fileNameVar, String dataVar, int lower) throws IOException {
        String fileName= readMemory(fileNameVar,lower);
        String  data = readMemory(dataVar,lower);
        File file = new File( fileName );
        if (file.createNewFile())
            System.out.println("File has been created.");
         else
            System.out.println("File already exists.");

        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.close();
    }

    public static String readFile(String fileNameVar, int lower) {
        String fileName= readMemory(fileNameVar,lower);
        StringBuilder data = new StringBuilder();

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready())
                data.append(br.readLine());
        } catch (Exception e) {
            print("File Does not Exist", lower);
        }
        return data.toString();
    }

    public static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

    public static int initializePCB(int lower,int pc) {

        String s ="and its PCB is : \n " ;
        int id =assignID();
        memory[++pcbIndex] =  new Word("ID:",id );s+=memory[pcbIndex]+", at index :" + pcbIndex +"\n";
        memory[++pcbIndex] =  new Word("Upper Boundary",index+1);s+=memory[pcbIndex]+", at index : " + pcbIndex +"\n";
        memory[++pcbIndex] = new Word("lower Boundary", lower);s+=memory[pcbIndex]+", at index : " + pcbIndex +"\n";
        memory[++pcbIndex] =  new Word("startPC",pc);s+=memory[pcbIndex]+", at index : " + pcbIndex +"\n";
        memory[++pcbIndex] =  new Word("State", state.NotRunning);s+=memory[pcbIndex]+", at index " + pcbIndex +"\n";
        System.out.println(s);
        return id;
    }
    public static int storeProgramInstructions(String filePath) throws IOException {
        System.out.println("Memory access in Process creation to add ");
        //7ad yerage3 waraya
        //BufferedReader lel program file
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        //put each instruction in a new index fel memory
        int lowerBoundary = index+1; //wla ++index idk?
        index += 3;
        int pc = index+1;//leaving space for variables
        while(br.ready()){
             memory[++index] =  new Word("Instruction",br.readLine());
            System.out.println(memory[index]);
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
                 if(oldId!=-1) System.out.println("The Process with id : "+ oldId+" finished execution with quanta "+ i);
                 System.out.println("The Process with id : "+id+" has been chosen to run from ready list");
                 i=1; oldId=id;
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
             i--;
            memory[stateIdx].value=state.NotRunning;
             if(pc==upper)
                 memory[stateIdx].value=state.Finished;
             else
                 readyQueue.add(id);
        }
    }



    public enum state {
        Running,
        NotRunning,
        Finished
    }

    public static class Word {
        String key;
        Object value;
        public Word(String k, Object v){
            value=v;
            key=k;
        }
        public String toString(){
            return "word { "+ "Key : "+key + " ,Value : " +value + " } ";
        }

    }
}
