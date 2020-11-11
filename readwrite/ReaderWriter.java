package readwrite;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReaderWriter implements Runnable{

    public Scanner fileinp=null;
    public boolean writer;
    public ProtectedTree protree=null;

    public ReaderWriter(String FILENAME, ProtectedTree ptree, boolean iswriter){
        try{    
            fileinp = new Scanner(new File(FILENAME));
            writer = iswriter;
            protree = ptree;
        } catch (FileNotFoundException e){
            throw new RuntimeException(e);
        }

    }

    public void run(){
        while(fileinp.hasNextInt()){
            int value = fileinp.nextInt();
            if(writer==true){
                protree.write(value);
            }
            else{
                protree.read(value);
            }
        }
    }
}