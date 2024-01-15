import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class User {
    public void addUsersToBD(String name, int numCard, int amount, boolean isBlocked) throws IOException {

        FileWriter fw = new FileWriter("src/DataBases/users.txt", true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(name+"-"+numCard+"-"+amount+"-"+isBlocked);
        bw.newLine();
        bw.close();
    }
}
