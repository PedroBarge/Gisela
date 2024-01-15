import java.io.*;

public class User1 {
    public void addUsersToBD(String name, int numCard, int amount, boolean isBlocked) throws IOException {

        FileWriter fw = new FileWriter("src/Resource/Teste.doc", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name + "-" + numCard + "-" + amount + "€-" + isBlocked);
        bw.newLine();
        bw.close();
    }
    public void readUsers() {
        System.out.println("Read all users");
        System.out.println();

        try (FileInputStream fileInputStream = new FileInputStream("src/Resource/Teste.doc");
             BufferedReader br = new BufferedReader(new InputStreamReader(fileInputStream))) {

            StringBuilder resultStringBuilder = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }

            String readFile = resultStringBuilder.toString();
            System.out.println(readFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    public void readUsers() {
//        System.out.println("Read all useres");
//        System.out.println();
//
//        FileInputStream fileInputStream;
//
//        try {
//            fileInputStream = new FileInputStream("src/Resource/Teste.doc");
//
//            String readFile = readFromInputStream(fileInputStream);
//            System.out.println(readFile);
//
//            fileInputStream.close();
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    private String readFromInputStream(InputStream inputStream)
//            throws IOException {
//        StringBuilder resultStringBuilder = new StringBuilder();
//        try (BufferedReader br
//                     = new BufferedReader(new InputStreamReader(inputStream))) {
//            String line;
//            while ((line = br.readLine()) != null) {
//                resultStringBuilder.append(line).append("\n");
//            }
//        }
//        return resultStringBuilder.toString();
//    }
}
