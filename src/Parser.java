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
//el mafroud dlwa2ty ye2ra el instructions mn el memory mesh mn el file directly
        int i = 0;
        // 3shan 2 instructions per quantum
        //nkhali eih condition el loop? Boundary?
        while (br.ready() && i < 2) {
            interpret(br.readLine(), 0); //ngeeb el pc mn el pcb
            i++;
        }
    }
    public static void interpret(String s, int PC) throws IOException {

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
        //increment pc hena

    }
}
