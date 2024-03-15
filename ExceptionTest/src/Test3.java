import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//예외전가
public class Test3 {
    public static void main(String[] args) {
        try{
            m2();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void m1() throws IOException{
        m2();
    }

    public static void m2 () throws IOException {
        Path file = Paths.get("C:\\javaStudy\\test.text");
        BufferedWriter writer = null;

        writer = Files.newBufferedWriter(file);
        writer.write('a');
        writer.write('b');
    }
}
