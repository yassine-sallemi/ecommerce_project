public abstract class Product {
    private static int nextId = 0;
    protected int id;
    protected String name;
    protected double price;
    protected String description;

    public Product(String name, double price, String description) {
        this.id = nextId++;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public abstract void displayInfo();
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public void setName(String name) {
        if(!name.isEmpty()){
            this.name = name;
        }
    }
    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }
    public void setDescription(String description) {
        if (!description.isEmpty()) {
            this.description = description;
        }
    }
    public void update(){
        System.out.print("Enter new name: ");
        setName(Main.scanner.nextLine());
        while(true){
            System.out.print("Enter new price: ");
            try {
                setPrice(Double.parseDouble(Main.scanner.nextLine()));
                if(price > 0){
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid price.");
            }
        }

        System.out.print("Enter new description: ");
        setDescription(Main.scanner.nextLine());
    }
}

