import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
    User admin = new User("admin", "admin", "admin",true);
    User customer = new User("customer", "customer", "customer",false);
    UserManager userManager = new UserManager();
    userManager.addUser(admin);
    userManager.addUser(customer);
    userManager.accountMenu();
    }
}