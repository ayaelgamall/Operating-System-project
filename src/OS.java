import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;

public class OS {

    static Hashtable<String, Object> variables = new Hashtable<>();

    public static void main(String[] args) throws Exception {
        execute("Program 1.txt");
        execute("Program 2.txt");
        execute("Program 3.txt");
    }

    private static void execute(String programPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(programPath));
        while (br.ready()) {
            interpret(br.readLine());
        }
    }

    private static void print(String x) {
        if(variables.contains(x)) System.out.println(variables.get(x));
        else System.out.println(x);
    }


    private static void assign(String variable, Object value) {
        variables.put(variable, value);
    }

    private static void add(String x, String y) {
        //todo extract from variables parse then add in res
        int res=0;
        assign(x,res);
    }

    private static void writeFile(String fileName, String data) {
        //todo if does not exist filename create
    }

    private static String readFile(String fileName) {
        return null; //todo reading from file
    }

    public static void interpret(String s) throws IOException {

        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign"): {
                Object result ;
                switch (words[2]) {
                    case ("input"): {
                        result = input();
                        break;
                    }
                    case ("readFile"): {
                        String fileName = words[3];
                        result = readFile(fileName);
                        break;
                    }
                    default:result=words[2];
                }
                assign(words[1], result);
                break;
            }
            case ("writeFile"): {
                writeFile(words[1], words[2]);
                break;
            }
            case ("print"): {
                print(words[1]);
                break;
            }
        }


    }

    private static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}
