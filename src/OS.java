
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Hashtable;
public class OS {
   static Hashtable<String,Object> variables= new Hashtable<>();

    public static void main (String[] args)throws Exception{
        String programPath="";

        BufferedReader br = new BufferedReader(new FileReader(programPath));
        while(br.ready()){
            interpret(br.readLine());
        }
    }

    private static void print(String x) {

    }


    private static void assign(String variable,Object value) {
        variables.put(variable,value);
    }

    private ststic void add(String x, String y ) {
    }

    private static void writeFile(String fileName,String data) {
    }

    private static void readFile(String fileName) {

    }

    public static void interpret(String s){

    }


}
