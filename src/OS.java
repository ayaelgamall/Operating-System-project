import java.io.BufferedReader;
import java.io.FileReader;

public class OS {

    public static void main (String[] args){
        String programPath="";
        
        BufferedReader br = new BufferedReader(new FileReader(programPath));
        while(br.ready()){
            interpret(br.readLine());
        }
    }	

    public static void interpret(String s){

    }


}
