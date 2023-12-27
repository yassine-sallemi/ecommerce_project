public class Electronic extends Product {
    private String brand;

    public Electronic(String name, double price, String description, String brand) {
        super(name, price, description);
        this.brand = brand;
    }

    @Override
    public void displayInfo() {
        System.out.println("Electronic: " + name + " by " + brand + ". Price: $" + price + ". Description: " + description);
    }

    @Override
    public void update() {
        super.update();
        System.out.print("Enter new brand: ");
        String brand = Main.scanner.nextLine();
        if(brand != null && !brand.isEmpty()){
            this.brand = brand;
        }
        displayInfo();
    }
}
