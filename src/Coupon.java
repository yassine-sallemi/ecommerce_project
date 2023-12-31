public class Coupon {
    private static int nextId = 0;
    private int id;
    private String codeText;
    private double discount;
    private Boolean active;
    
    // The admin field is a User object that represents the admin who created the coupon
    private User admin;
    public Coupon(double discount, String codeText, Boolean active, User admin) {
        this.id = nextId++;
        this.codeText = codeText;
        this.discount = discount;
        this.active = active;
        this.admin = admin;
    }
    public int getId() {
        return id;
    }
    public String getCodeText() {
        return codeText;
    }
    public User getAdmin() {
        return admin;
    }
    public double getDiscount() {
        return discount;
    }
    public Boolean getActive() {
        return active;
    }
    public void setActive(Boolean active) {
        this.active = active;
    }
}
