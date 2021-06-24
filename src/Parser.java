import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
    public static void main(String[] args) throws Exception {

//        execute("Program 1.txt");
//        execute("Program 2.txt");
//        execute("Program 3.txt");
    }

    private static void execute(String programPath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(programPath));
        while (br.ready()) {
            interpret(br.readLine(),0);
        }
    }
    public static void interpret(String s, int lower) throws IOException {

        // el pc m7tag ykon bta3 kol process lw7daha w yb2a stored s7;
        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign"):
                String result;
                switch (words[2]) {
                    case ("input"):
                        result = OS.input();
                        break;
                    case ("readFile"):
                        String fileName = words[3];
                        result = OS.readFile(fileName, lower);
                        break;
                    default:
                        result = words[2];
                        break;
                }
                OS.assign(words[1], result,lower);
                break;
            case ("writeFile"):
                OS.writeFile(words[1], words[2],lower);
                break;
            case ("print"):
                OS.print(words[1],lower);
                break;
            case ("readFile"):
                OS.readFile(words[1],lower);
                break;
            case ("add"):
                OS.add(words[1], words[2],lower);
                break;
        }

    }
}
