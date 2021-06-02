
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

        private  void print(String x) {
        }

        
            private void assign(String variable,Object value) {
                variables.put(variable,value);
            }

        private void add(String x, String y ) {
        }

        private void writeFile(String fileName,String data) {
        }
        private void readFile(String fileName) {
        }

        private static void readFile() {

        }


    public static void interpret(String s){

    }


}
