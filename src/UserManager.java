import java.util.ArrayList;
import java.util.Objects;

public class UserManager {
    private final ArrayList<User> users = new ArrayList<>();
    private final InventoryManager inventoryManager = new InventoryManager();
    private User currentUser;

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

        // Create new User based on its role and add it to the database
        User newUser = new User(login,password,name, isAdmin);
        if(isAdmin){
            System.out.println("New administrator has been added: " + name);
        }
        else{
            System.out.println("New customer has been added: " + name);
        }
        users.add(newUser);
    }
    private User loginAccount(String login, String password){
        // Check if the username exists in the database
        User user = null;
        for (User user1 : users){
            if(Objects.equals(user1.getLogin(), login)){
                user = user1;
                break;
            }
        }

        // If the username doesn't exist, return null
        if(user == null){
            System.out.println("This username doesn't exist.");
            return null;
        }

        // Check the password for the given user
        if(!Objects.equals(user.getPassword(), password)){
            System.out.println("The given password is wrong");
            return null;
        }

        // If the password is correct, return the user
        System.out.println("Logged in successfully.");
        return user;
    }
    private void addAdmin(User currentUser, String login, String password, String name){
        // Check if the current user is an admin
        if(!currentUser.getIsAdmin()){
            System.out.println("You don't have the permission to add an administrator.");
            return;
        }

        // Create new admin account and add it to the database
        createAccount(login,password,name,true);
    }


    private void displayAdmins() {
        // Display all admins from the database
        System.out.println("Administrators:");
        for (User user : users){
            if(user.getIsAdmin()){
                System.out.println(user.getName());
            }
        }
    }
    private void displayCustomers() {
        // Display all customers from the database
        System.out.println("Customers:");
        for (User user : users){
            if(!user.getIsAdmin()){
                System.out.println(user.getName());
            }
        }
    }
    private void deleteUser() {
        // Check if the current user is an admin
        if(!currentUser.getIsAdmin()){
            System.out.println("You don't have the permission to delete a user.");
            return;
        }

        // Delete a user from the database
        // Check if the username exists in the database
        System.out.println("Enter username: ");
        String login = Main.scanner.nextLine();
        for (User user : users){
            // If the username exists in the database
            if(Objects.equals(user.getLogin(), login)){
                // Check if the user is trying to delete himself
                if(user.getLogin().equals(currentUser.getLogin())){
                    System.out.println("You can't delete yourself.");
                    return;
                }
                // If the user is not trying to delete himself, delete the user
                users.remove(user);
                System.out.println("User deleted.");
                return;
            }
        }
        // If the username doesn't exist, do nothing
        System.out.println("User not found.");
    }

    // ask if you want to create an account or login
    public void accountMenu(){
        while (true) {
            System.out.println("1. Create account");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String login, password;
            int choice = -1;
            if(Main.scanner.hasNextInt()){
                choice = Main.scanner.nextInt();
            }
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
                    currentUser = loginAccount(login, password);
                    // If the user is not null, it means that the login was successful
                    if(currentUser != null){
                        // Set the current user and go to the user menu
                        inventoryManager.setCurrentUser(currentUser);
                        if(currentUser.getIsAdmin()){
                            adminMenu();
                        }
                        else{
                            customerMenu();
                        }
                    }
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // user menu
    private void adminMenu(){
        while (true) {
            System.out.println("1. User management");
            System.out.println("2. Inventory management");
            System.out.println("3. Logout");
            System.out.print("Enter your choice: ");
            int choice = -1;
            if(Main.scanner.hasNextInt()){
                choice = Main.scanner.nextInt();
            }
            Main.scanner.nextLine();
            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    inventoryManager.inventoryMenu();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    currentUser = null;
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void userManagementMenu(){
        if(currentUser.getIsAdmin()){
            while (true) {
                System.out.println("1. Add administrator");
                System.out.println("2. Display administrators");
                System.out.println("3. Display customers");
                System.out.println("4. Display all orders");
                System.out.println("5. Delete a user");
                System.out.println("6. Back to main menu");
                System.out.print("Enter your choice: ");
                int choice = -1;
                if(Main.scanner.hasNextInt()){
                    choice = Main.scanner.nextInt();
                }
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
                        // Display all orders from all customers
                        for(User user : users){
                            if(!user.getIsAdmin()){
                                user.displayOrders();
                            }
                        }
                        break;
                    case 5:
                        deleteUser();
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
    private void customerMenu(){
        while (true) {
            System.out.println("1. Shopping Menu");
            System.out.println("2. View Orders");
            System.out.println("3. Pay an order");
            System.out.println("4. Cancel an order");
            System.out.println("5. Logout");
            System.out.print("Enter your choice: ");
            int choice = -1;
            if(Main.scanner.hasNextInt()){
                choice = Main.scanner.nextInt();
            }
            Main.scanner.nextLine();
            ShoppingCart shoppingCart = new ShoppingCart();
            int id;
            switch (choice) {
                case 1:
                    shoppingCart.shoppingMenu(inventoryManager);
                    break;
                case 2:
                    currentUser.displayOrders();
                    break;
                case 3:
                    // Get order by id
                    System.out.println("Enter order id: ");
                    id = -1;
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    // Pay order based on payment method
                    System.out.println("1. Cash");
                    System.out.println("2. Credit card");
                    System.out.print("Enter your choice: ");
                    int choice1 = -1;
                    if(Main.scanner.hasNextInt()){
                        choice1 = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    switch (choice1) {
                        case 1:
                            currentUser.payOrder(id, new CashPaymentStrategy());
                            break;
                        case 2:
                            currentUser.payOrder(id, new CreditCardPaymentStrategy());
                            break;
                        default:
                            // If the user doesn't choose a valid payment method, cancel the payment
                            System.out.println("Payment cancelled.");
                            return;
                    }
                    break;
                case 4:
                    System.out.println("Enter order id: ");
                    id = -1;
                    if(Main.scanner.hasNextInt()){
                        id = Main.scanner.nextInt();
                    }
                    Main.scanner.nextLine();
                    currentUser.cancelOrder(id, inventoryManager);
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
