import java.util.ArrayList;

public class InventoryManager {
    private final ArrayList<ProductCard> inventory = new ArrayList<>();
    private final ProductManager productManager = new ProductManager();
    private final ShoppingCart shoppingCart = new ShoppingCart();
    private User currentUser;
    public InventoryManager(User currentUser) {
        this.currentUser = currentUser;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(User anotherUser) {
        this.currentUser = anotherUser;
    }

    public ProductCard getProductCardById(int id) {
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                return productCard;
            }
        }
        return null;
    }
    public void addProduct() {
        Product product = productManager.createProduct();
        ProductCard productCard = new ProductCard(product, 0);
        inventory.add(productCard);
    }
    int readProductId(){
        System.out.println("Enter product id: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();
        return id;
    }
    public void displayProduct() {
        int id = readProductId();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void updateProduct() {
        int id = readProductId();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                productManager.updateProduct(productCard.getProduct());
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void deleteProduct() {
        int id = readProductId();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                inventory.remove(productCard);
                System.out.println("Product deleted.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
        } else {
            System.out.println("Products in inventory:");
            for (ProductCard productCard : inventory) {
                productCard.getProduct().displayInfo();
                System.out.println("Quantity: " + productCard.getQuantity());
            }
        }
    }
    public void modifyQuantityProduct() {
        System.out.println("Enter product id: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();

        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                System.out.println("Enter quantity (Old quantity: " + productCard.getQuantity() + "): ");
                int quantity = Main.scanner.nextInt();
                Main.scanner.nextLine();
                productCard.setQuantity(quantity);
                System.out.println("Quantity updated.");
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void updateProductQuantityAfterPurchase(Product product, int quantity) {
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == product.getId()) {
                productCard.setQuantity(productCard.getQuantity() - quantity);
                return;
            }
        }
    }
    public void searchProductMenu(){
        System.out.println("1. Search by id");
        System.out.println("2. Search by name");
        System.out.println("3. Search by price range");
        System.out.println("4. Search by category");
        System.out.print("Your choice: ");
        int choice = Main.scanner.nextInt();
        Main.scanner.nextLine();
        switch (choice){
            case 1:
                searchProductById();
                break;
            case 2:
                searchProductByName();
                break;
            case 3:
                searchProductByPriceRange();
                break;
            case 4:
                searchProductByCategory();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    public void searchProductById() {
        System.out.println("Enter product id: ");
        int id = Main.scanner.nextInt();
        Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getId() == id) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByName() {
        System.out.println("Enter product name: ");
        String name = Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getName().equalsIgnoreCase(name)) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByPriceRange() {
        System.out.println("Enter min price: ");
        double minPrice = Main.scanner.nextDouble();
        Main.scanner.nextLine();
        System.out.println("Enter max price: ");
        double maxPrice = Main.scanner.nextDouble();
        Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getPrice() >= minPrice && productCard.getProduct().getPrice() <= maxPrice) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    public void searchProductByCategory() {
        System.out.println("Enter product category: ");
        String category = Main.scanner.nextLine();
        for (ProductCard productCard : inventory) {
            if (productCard.getProduct().getClass().getSimpleName().equalsIgnoreCase(category)) {
                productCard.getProduct().displayInfo();
                return;
            }
        }
        System.out.println("Product not found.");
    }
    //inventory menu
    public void inventoryMenu() {
        if(currentUser.getIsAdmin()){
            while (true) {
                System.out.println("1. Add product");
                System.out.println("2. Display product");
                System.out.println("3. Update product");
                System.out.println("4. Delete product");
                System.out.println("5. Display inventory");
                System.out.println("6. Modify quantity product");
                System.out.println("7. Search product");
                System.out.println("8. Back to main menu");
                System.out.print("Enter your choice: ");
                int choice = Main.scanner.nextInt();
                Main.scanner.nextLine();
                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        displayProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        displayInventory();
                        break;
                    case 6:
                        modifyQuantityProduct();
                        break;
                    case 7:
                        searchProductMenu();
                        break;
                    case 8:
                        System.out.println("Back to main menu...");
                        return;
                    default:
                        System.out.println("Invalid choice");
                        break;
                }
            }
        } else{
            shoppingCart.shoppingMenu(this);
        }
    }
}