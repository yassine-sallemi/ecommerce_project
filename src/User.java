import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class User{
    private String login;
    private String password;
    private String name;
    private boolean isAdmin;
    private final ArrayList<Order> orders = new ArrayList<>();
    public User(String login, String password, String name, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
    }

    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public boolean getIsAdmin() {
        return isAdmin;
    }
    public String getName() {
        return name;
    }
    public void addOrder(Order order){
        orders.add(order);
    }

    public static ArrayList<User> populate() {
        String textFile = "admins.txt"; // Replace with your text file path
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(textFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] adminInfo = line.split(",");

                if (adminInfo.length >= 3) {
                    String login = adminInfo[0];
                    String password = adminInfo[1];
                    String name = adminInfo[2];
                    boolean isAdmin = Boolean.parseBoolean(adminInfo[3]);

                    User user = new User(login, password, name, isAdmin);
                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

}
