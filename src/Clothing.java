public class Clothing extends Product {
    private String size;
    private String material;

    public Clothing(String name, double price, String description, String size, String material) {
        super(name, price, description);
        this.size = size;
        this.material = material;
    }

    @Override
    public void displayInfo() {
        System.out.println("\tClothing: " + name + ". Size: " + size + ". Material: " + material + ". Price: $" + price + ". Description: " + description);
    }

    public void update() {
        super.update();
        System.out.print("Enter new size: ");
        String size = Main.scanner.nextLine();
        System.out.print("Enter new material: ");
        String material = Main.scanner.nextLine();
        if(size != null && !size.isEmpty()){
            this.size = size;
        }
        if(material != null && !material.isEmpty()){
            this.material = material;
        }
        displayInfo();
    }
}
