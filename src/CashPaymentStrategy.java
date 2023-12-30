public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Collecting cash payment of $" + amount);
        System.out.println("Cash payment collected successfully!");
    }

    @Override
    public String toString() {
        return "Cash";
    }
}
