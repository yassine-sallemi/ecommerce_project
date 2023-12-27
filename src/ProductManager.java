import java.time.LocalDate;
import java.time.format.DateTimeParseException;
public class ProductManager {
    public Product createProduct() {
        Product product = null;
        System.out.println("Choose product type:");
        System.out.println("1. Book");
        System.out.println("2. Electronic");
        System.out.println("3. Clothing");
        System.out.println("4. Food");
        System.out.print("Your choice: ");
        int choice = Main.scanner.nextInt();
        Main.scanner.nextLine();
        String name, description;
        double price ;
        System.out.print("Enter name: ");
        name = Main.scanner.nextLine();
        while(name.isEmpty()){
            System.out.println("Error: Name cannot be empty.");
            System.out.print("Enter name: ");
            name = Main.scanner.nextLine();
        }
        System.out.print("Enter description: ");
        description = Main.scanner.nextLine();
        while(description.isEmpty()){
            System.out.println("Error: Description cannot be empty.");
            System.out.print("Enter description: ");
            description = Main.scanner.nextLine();
        }

        System.out.print("Enter price: ");
        price = Main.scanner.nextDouble();
        while(price <= 0){
            System.out.println("Error: Price must be greater than 0.");
            System.out.print("Enter price: ");
            price = Main.scanner.nextDouble();
        }

        switch (choice) {
            case 1:
                String author, genre;
                System.out.print("Enter author: ");
                author = Main.scanner.nextLine();
                while(author.isEmpty()){
                    author = Main.scanner.nextLine();
                    System.out.println("Error: Author cannot be empty.");
                    System.out.print("Enter author: ");
                }
                System.out.print("Enter genre: ");
                genre = Main.scanner.nextLine();
                while(genre.isEmpty()){
                    System.out.println("Error: Genre cannot be empty.");
                    System.out.print("Enter genre: ");
                    genre = Main.scanner.nextLine();
                }
                product = new Book(name, price, description, author, genre);
                break;
            case 2:
                String brand;
                System.out.print("Enter brand: ");
                brand = Main.scanner.nextLine();
                while(brand.isEmpty()){
                    System.out.println("Error: Brand cannot be empty.");
                    System.out.print("Enter brand: ");
                    brand = Main.scanner.nextLine();
                }
                product = new Electronic(name, price, description, brand);
                break;
            case 3:
                String size, material;
                System.out.print("Enter size: ");
                size = Main.scanner.nextLine();
                while(size.isEmpty()){
                    System.out.println("Error: Size cannot be empty.");
                    System.out.print("Enter size: ");
                    size = Main.scanner.nextLine();
                }
                System.out.print("Enter material: ");
                material = Main.scanner.nextLine();
                while(material.isEmpty()){
                    System.out.println("Error: Material cannot be empty.");
                    System.out.print("Enter material: ");
                    material = Main.scanner.nextLine();
                }
                product = new Clothing(name, price, description, size, material);
                break;
            case 4:
                String expirationDate;
                LocalDate date = null;
                System.out.print("Enter expiration date: ");
                expirationDate = Main.scanner.nextLine();
                while(expirationDate.isEmpty()){
                    System.out.println("Error: Expiration date cannot be empty.");
                    System.out.print("Enter expiration date: ");
                    expirationDate = Main.scanner.nextLine();
                }
                while(date == null){
                    try {
                        date = LocalDate.parse(expirationDate);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Please enter a date in the format YYYY-MM-DD.");
                    }
                }
                product = new Food(name, price, description, date);
                break;
            default:
                System.out.println("Invalid choice");
                break;
        }
        return product;
    }
    public void updateProduct(Product product) {
        if(product == null){
            System.out.println("Product not found");
            return;
        }
        product.displayInfo();
        System.out.println("Press enter to keep the old value.");
        product.update();
        product.displayInfo();
    }
}