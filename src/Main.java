import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        userManager.accountMenu();
    }
}