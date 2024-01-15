import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WriteBytesToAFile {
    public static void main(String[] args) throws IOException {
        System.out.println("WRITE TO A FILE");

        // WRITE BYTES TO A FILE
        FileInputStream fileInputStream = new FileInputStream("src/Resource/Teste.doc");

        FileOutputStream fileOutputStream = new FileOutputStream("src/Resource/Teste.doc");

        fileOutputStream.write(fileInputStream.read());
        String message = "This is a very profound message\n";
        fileOutputStream.write(message.getBytes());
        message = "This is a very profound message\n";
        fileOutputStream.write(message.getBytes());
        message = "This is a very profound message\n";
        fileOutputStream.write(message.getBytes());
        message = "This is a very profound message\n";
        fileOutputStream.write(message.getBytes());
        // ALWAYS CLOSE THE STREAMS
        fileOutputStream.close();
    }
}