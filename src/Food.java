import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Food extends Product {
    private LocalDate expirationDate;

    public Food(String name, double price, String description, LocalDate expirationDate) {
        super(name, price, description);
        this.expirationDate = expirationDate;
    }

    @Override
    public void displayInfo() {
        System.out.println("\tFood: " + name + ". Expires: " + expirationDate + ". Price: $" + price + ". Description: " + description);
    }

    @Override
    public void update() {
        super.update();
        LocalDate date = null;
        System.out.print("Enter new expiration date: ");
        String expirationDate = Main.scanner.nextLine();
        try {
            date = LocalDate.parse(expirationDate);
        } catch (DateTimeParseException e) {
            System.out.println("Error: Please enter a date in the format YYYY-MM-DD.");
        }
        this.expirationDate = date;
        displayInfo();
    }
}
