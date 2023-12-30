import java.util.ArrayList;

public class CouponManager {
    private final ArrayList<Coupon> coupons = new ArrayList<>();

    // admin create coupon
    public void createCoupon(User admin) {
        double discount;
        String codeText;
        System.out.println("Enter discount: ");
        discount = Main.scanner.nextDouble();
        Main.scanner.nextLine();
        if (discount < 5 || discount > 70) {
            System.out.println("Discount must be between 5 and 70.");
            return;
        }
        System.out.println("Enter code text: ");
        codeText = Main.scanner.nextLine().toUpperCase();
        Coupon coupon = new Coupon(discount / 100, codeText, true, admin);
        System.out.println("Coupon created successfully.");
        coupons.add(coupon);
    }

    public void deactivateCoupon(User admin) {
        int id;
        System.out.println("Enter coupon id: ");
        id = Main.scanner.nextInt();
        Main.scanner.nextLine();
        for (Coupon coupon : coupons) {
            if (coupon.getId() == id) {
                if (coupon.getAdmin().getLogin().equals(admin.getLogin())) {
                    coupon.setActive(false);
                } else {
                    System.out.println("Error: You are not the creator of this coupon.");
                }
                return;
            }
        }
        System.out.println("Error: Coupon not found.");
    }
    public void displayCoupons() {
        if (coupons.isEmpty()) {
            System.out.println("No coupons found.");
        } else {
            System.out.println("Coupons:");
            for (Coupon coupon : coupons) {
                System.out.println("Id: " + coupon.getId());
                System.out.println("\tDiscount: " + coupon.getDiscount()*100 + "%");
                System.out.println("\tActive: " + coupon.getActive());
                System.out.println("\tAdmin: " + coupon.getAdmin().getName());
            }
        }
    }
    public Coupon getCouponByCode(String codeText) {
        for (Coupon coupon : coupons) {
            if (coupon.getCodeText().equals(codeText)) {
                if(coupon.getActive())
                    return coupon;
                else{
                    System.out.println("This coupon has expired.");
                    return null;
                }
            }
        }
        return null;
    }
}
