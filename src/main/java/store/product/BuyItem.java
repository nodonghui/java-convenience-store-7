package store.product;

public class BuyItem implements Product{

    private String name;
    private int quantity;

    public BuyItem(String name, int quantity) {
        this.name=name;
        this.quantity=quantity;
    }

    // Getter methods
    @Override
    public String getName() { return name; }
    @Override
    public int getQuantity() { return quantity; }
}
