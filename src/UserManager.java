import java.util.ArrayList;
import java.util.Objects;

public class UserManager {
    private final ArrayList<User> users = new ArrayList<>();
    UserManager(){
        User admin = new User("admin", "admin", "admin",true);
        users.add(admin);
    }
    private void createAccount(String login, String password, String name, boolean isAdmin){

        // Check if the new login is unique
        for (User admin : users){
            if(Objects.equals(admin.getLogin(), login)){
                System.out.println("This username is already used, try another one.");
                return;
            }
        }
        // Check if password is at least 4 characters
        if(password.length() < 4){
            System.out.println("Password should be at least 4 characters.");
            return;
        }
        // Create new User based on its role
        User newUser = new User(login,password,name, isAdmin);
        users.add(newUser);

        if(isAdmin){
            System.out.println("New administrator has been added: " + name);
        }
        else{
            System.out.println("New customer has been added: " + name);
        }
        users.add(newUser);
    }
    private User loginAccount(String login, String password){
        // Search for the user in the database based on its login
        User user = null;
        for (User user1 : users){
            if(Objects.equals(user1.getLogin(), login)){
                user = user1;
                break;
            }
        }

        if(user == null){
            System.out.println("This username doesn't exist.");
            return null;
        }

        // Check the password and return the user otherwise
        if(!Objects.equals(user.getPassword(), password)){
            System.out.println("The given password is wrong");
            return null;
        }
        System.out.println("Logged in successfully.");
        return user;
    }
    private void addAdmin(User currentUser, String login, String password, String name){
        if(!currentUser.getIsAdmin()){
            System.out.println("You don't have the permission to add an administrator.");
            return;
        }
        createAccount(login,password,name,true);
    }


    private void displayAdmins() {
        System.out.println("Administrators:");
        for (User user : users){
            if(user.getIsAdmin()){
                System.out.println(user.getName());
            }
        }
    }
    private void displayCustomers() {
        System.out.println("Customers:");
        for (User user : users){
            if(!user.getIsAdmin()){
                System.out.println(user.getName());
            }
        }
    }
    private void deleteUser(User currentUser) {
        if(!currentUser.getIsAdmin()){
            System.out.println("You don't have the permission to delete a user.");
            return;
        }
        System.out.println("Enter username: ");
        String login = Main.scanner.nextLine();
        for (User user : users){
            if(Objects.equals(user.getLogin(), login)){
                users.remove(user);
                System.out.println("User deleted.");
                return;
            }
        }
        System.out.println("User not found.");
    }

    // ask if you want to create an account or login
    public void accountMenu(){
        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            User user;
            String login, password;
            InventoryManager inventoryManager = new InventoryManager(null);
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    login = Main.scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = Main.scanner.nextLine();
                    System.out.print("Enter name: ");
                    String name = Main.scanner.nextLine();
                    createAccount(login, password, name, false);
                    System.out.println("Please login to start shopping.");
                    break;
                case 2:
                    System.out.print("Enter username: ");
                    login = Main.scanner.nextLine();
                    System.out.print("Enter password: ");
                    password = Main.scanner.nextLine();
                    user = loginAccount(login, password);
                    if(user != null){
                        inventoryManager.setCurrentUser(user);
                        if(user.getIsAdmin()){
                            adminMenu(user, inventoryManager);
                        }
                        else{
                            customerMenu(inventoryManager);
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting account menu...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // user menu
    private void adminMenu(User currentUser, InventoryManager inventoryManager){
        while (true) {
            System.out.println("1. User management");
            System.out.println("2. Inventory management");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            switch (choice) {
                case 1:
                    userManagementMenu(currentUser);
                    break;
                case 2:
                    inventoryManager.inventoryMenu();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void userManagementMenu(User currentUser){
        if(currentUser.getIsAdmin()){
            while (true) {
                System.out.println("1. Add administrator");
                System.out.println("2. Display administrators");
                System.out.println("3. Display customers");
                System.out.println("4. Display all orders");
                System.out.println("5. Delete user");
                System.out.println("6. Back to main menu");
                System.out.print("Enter your choice: ");
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                switch (choice) {
                    case 1:
                        System.out.print("Enter username: ");
                        String login = Main.scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = Main.scanner.nextLine();
                        System.out.print("Enter name: ");
                        String name = Main.scanner.nextLine();
                        addAdmin(currentUser, login, password, name);
                        break;
                    case 2:
                        displayAdmins();
                        break;
                    case 3:
                        displayCustomers();
                        break;
                    case 4:
                        for(User user : users){
                            if(!user.getIsAdmin()){
                                user.displayOrders();
                            }
                        }
                        break;
                    case 5:
                        deleteUser(currentUser);
                        break;
                    case 6:
                        System.out.println("Back to main menu...");
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }
        }
        else{
            System.out.println("You don't have the permission to access this menu.");
        }
    }
    private void customerMenu(InventoryManager inventoryManager){
        while (true) {
            System.out.println("1. Shopping Menu");
            System.out.println("2. View Orders");
            System.out.println("3. Pay an order");
            System.out.println("4. Cancel an order");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = Main.scanner.nextInt();
            Main.scanner.nextLine();
            ShoppingCart shoppingCart = new ShoppingCart();
            int id;
            switch (choice) {
                case 1:
                    shoppingCart.shoppingMenu(inventoryManager);
                    break;
                case 2:
                    inventoryManager.getCurrentUser().displayOrders();
                    break;
                case 3:
                    System.out.println("Enter order id: ");
                    id = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    System.out.println("1. Cash");
                    System.out.println("2. Credit card");
                    System.out.print("Enter your choice: ");
                    int choice1 = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    switch (choice1) {
                        case 1:
                            inventoryManager.getCurrentUser().payOrder(id, new CashPaymentStrategy());
                            break;
                        case 2:
                            inventoryManager.getCurrentUser().payOrder(id, new CreditCardPaymentStrategy());
                            break;
                        default:
                            System.out.println("Invalid choice.");
                    }
                    break;
                case 4:
                    System.out.println("Enter order id: ");
                    id = Main.scanner.nextInt();
                    Main.scanner.nextLine();
                    inventoryManager.getCurrentUser().cancelOrder(id, inventoryManager);
                    break;
                case 5:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

}
