import java.io.*;
import java.util.Hashtable;

public class OS {

    static Hashtable<String, String> variables = new Hashtable<>();

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
        System.out.println(variables.getOrDefault(x, x));
    }


    private static void assign(String variable, String value) {
        variables.put(variable, value);
    }


    private static void add(String x, String y) {
        int i1 = Integer.parseInt(variables.getOrDefault(x, x));
        int i2 = Integer.parseInt(variables.getOrDefault(y, y));
        int res= i1 + i2;
        assign(x,""+res);
    }


    private static void writeFile(String fileNameVar, String dataVar) throws IOException {
        String fileName=variables.getOrDefault(fileNameVar, fileNameVar);
        String  data = variables.getOrDefault(dataVar, dataVar);
        File file = new File( fileName );
        if (file.createNewFile())
            System.out.println("File has been created.");
         else
            System.out.println("File already exists.");

//        String filePath = "src/"+ fileName +".txt";
        FileWriter fw = new FileWriter(fileName);
        fw.write(data);
        fw.close();
    }

    private static String readFile(String fileNameVar) {
        String fileName=variables.getOrDefault(fileNameVar, fileNameVar);
//        String filePath = "src/"+ fileName +".txt";
        StringBuilder data = new StringBuilder();

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            while (br.ready())
                data.append(br.readLine());
        } catch (Exception e) {
            print("File Does not Exist");
        }

        return data.toString();
    }

    public static void interpret(String s) throws IOException {

        String[] words = s.split(" ");
        switch (words[0]) {
            case ("assign") -> {
                String result;
                switch (words[2]) {
                    case ("input") -> result = input();
                    case ("readFile") -> {
                        String fileName = words[3];
                        result = readFile(fileName);
                    }
                    default -> result = words[2];
                }
                assign(words[1], result);
            }
            case ("writeFile") -> writeFile(words[1], words[2]);
            case ("print") -> print(words[1]);
            case ("readFile") -> readFile(words[1]);
            case ("add") -> add(words[1], words[2]);
        }


    }

    private static String input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        return br.readLine();
    }

}
