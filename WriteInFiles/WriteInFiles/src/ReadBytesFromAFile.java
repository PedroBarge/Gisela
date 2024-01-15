import java.io.*;

public class ReadBytesFromAFile {
    public static void main(String[] args)  {
        System.out.println("Ler um documento");
        System.out.println();
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream("src/Resource/Teste.doc");

            String temp = readFromInputStream(fileInputStream);
            System.out.println(temp);

            fileInputStream.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
    private static String readFromInputStream(InputStream inputStream)
            throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}
