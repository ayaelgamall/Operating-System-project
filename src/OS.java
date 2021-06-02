import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

    private static void print(Object x) {
        System.out.println(x.toString());
    }


    private static void assign(String variable, Object value) {
        variables.put(variable, value);
//        variables.replace(words[1], result);

    }

    private static void add(String x, String y) {
    }

    private static void writeFile(String fileName, String data) {
    }


    private static String readFile(String fileName) {
        return null; //todo reading
    }

    public static void interpret(String s) {

        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign"): {
                Object result = words[2];
                if (words[2].equals("readFile")) {
                    String fileName = words[3];
                    result = readFile(fileName);
                }
                assign(words[1], result);
                break;
            }
            case ("writeFile"): {
                writeFile(words[1], words[2]);
                break;
            }
            case ("print"): {
                print(variables.get(words[1]));
                break;
            }
        }


    }

}
