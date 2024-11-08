package store.product;

public class Item implements  Product{

    private final String name;
    private final double price;
    private final int quantity;
    private final String promotion;

    public Item(String name,double price, int quantity, String promotion) {
        this.name=name;
        this.price=price;
        this.quantity=quantity;
        this.promotion=promotion;
    }
    // Getter Method
    @Override
    public String getName() { return name; }
    @Override
    public int getQuantity() { return quantity; }

    public double getPrice() { return price; }

    public String getPromotion() { return promotion; }
}
