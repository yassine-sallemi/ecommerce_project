public class CreditCardPaymentStrategy implements PaymentStrategy {
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
        System.out.println("Credit card payment processed successfully!");
    }

    @Override
    public String toString() {
        return "Credit card";
    }
}