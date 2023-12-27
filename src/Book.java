public class Book extends Product {
    private String author;
    private String genre;

    public Book(String name, double price, String description, String author, String genre) {
        super(name, price, description);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book: " + name + " by " + author + ". Genre: " + genre + ". Price: $" + price + ". Description: " + description);
    }

    @Override
    public void update() {
        super.update();
        System.out.print("Enter new author: ");
        String author = Main.scanner.nextLine();
        System.out.print("Enter new genre: ");
        String genre = Main.scanner.nextLine();
        if(author != null && !author.isEmpty()){
            this.author = author;
        }
        if(genre != null && !genre.isEmpty()){
            this.genre = genre;
        }
        displayInfo();
    }
}

