
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
      private static void print() {


        }

        private static void readFile() {

        }


    public static void interpret(String s){

    }


}
