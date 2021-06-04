import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Parser {
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
    public static void interpret(String s) throws IOException {

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
                        result = OS.readFile(fileName);
                        break;
                    default:
                        result = words[2];
                        break;
                }
                OS.assign(words[1], result);
                break;
            case ("writeFile"):
                OS.writeFile(words[1], words[2]);
                break;
            case ("print"):
                OS.print(words[1]);
                break;
            case ("readFile"):
                OS.readFile(words[1]);
                break;
            case ("add"):
                OS.add(words[1], words[2]);
                break;
        }


    }
}
