import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        // Create a new instance of UserManager and call the accountMenu method
        UserManager userManager = new UserManager();
        userManager.accountMenu();
    }
}