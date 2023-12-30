public class Review {
    private int rating;
    private String comment;
    private User user;

    public Review(int rating, String comment,  User user) {
        this.comment = comment;
        this.rating = rating;
        this.user = user;
    }
    public User getUser() {
        return user;
    }
    public void displayInfo() {
        System.out.println("Review info:");
        System.out.println("\tUser: " + user.getName());
        System.out.println("\tRating: " + rating);
        System.out.println("\tComment: " + comment);
    }
}
