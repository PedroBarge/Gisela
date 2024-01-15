import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteInFileWithFileWriter {
    public static void main(String[] args) throws IOException {
        FileWriter fw = new FileWriter("src/Resource/Teste.doc", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("Spain");
        bw.newLine();
        bw.close();

    }
}
