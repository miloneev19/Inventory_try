import java.time.LocalDateTime;

public class Records {

    private String name,type;
    private double sellingPrice, purchasePrice;
    private int quantity;
    private LocalDateTime dateTime;

    Records(String name, String type, double sellingPrice, double purchasePrice, int quantity)
    {
        this.name=name.toUpperCase();
        this.purchasePrice=purchasePrice;
        this.sellingPrice=sellingPrice;
        this.type=type;
        this.quantity=quantity;
        this.dateTime=LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
