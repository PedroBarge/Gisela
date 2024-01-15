import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static User1 user = new User1();

    public static void main(String[] args) throws IOException {
        String menuOption;
        do {
            System.out.println("Menu");
            System.out.println("1- Add new user to Data Base");
            System.out.println("2- Check all useres");
            System.out.println("0- Exit");
            menuOption = scanner.next();

            switch (menuOption) {
                case "1":
                    creatUser();
                    break;
                case "2":
                    checkUsers();
                    break;
                case "0":
                    break;
                default:
            }
        }while (!menuOption.equals("0"));
    }

        static void creatUser () throws IOException {
            boolean isLocked = false;
            System.out.println("New user");
            System.out.print("Name: ");
            String nameUser = scanner.next();
            System.out.print("Card Number: ");
            int cardNum = scanner.nextInt();
            System.out.print("Amount: ");
            int amonutInCard = scanner.nextInt();
            System.out.println("Is Blocked? ");
            System.out.print("Yes / No ");
            String isBlockedString = scanner.next().toLowerCase();
            if (isBlockedString.equals("yes")) {
                isLocked = true;
            }

            user.addUsersToBD(nameUser, cardNum, amonutInCard, isLocked);
        }

        static void checkUsers () {
            user.readUsers();
        }
    }