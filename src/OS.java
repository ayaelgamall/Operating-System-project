import java.util.Hashtable;

public class OS {

    Hashtable<String,Object> variables= new Hashtable<>();

    private  void print(String x) {
    }

    private void assign(String variable,Object value) {
        variables.put(variable,value);
    }

    private void add(String x, String y ) {
    }

    private void writeFile(String fileName,String data) {
    }

    private Object readFile(String fileName) {
        return null; //todo reading
    }

    public static void main(String[] args) {

    }
}
