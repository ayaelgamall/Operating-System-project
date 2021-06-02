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

    private static void add(String x, String y ) {
    }

    private static void writeFile(String fileName,String data) {
    }


    private static String readFile(String fileName) {
        return null; //todo reading
    }

    public static void interpret(String s) {

        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign"): {
                Object result = null;
                switch (words[2]) {
                    case ("input"): {
                        result = input();
                        break;
                    }
                    case ("readFile"): {
                        String fileName = words[3];
                        result = readFile(fileName);
                    }
                }
                variables.replace(words[1], result);
                break;
            }
            case ("writeFile"): {
                writeFile(words[1],words[2]);
                break;
            }
            case ("print"): {
                print(variables.get(words[1]));
                break;
            }
        }


    }
    public static String input(){
        //todo
        return null;
    }
    
}
