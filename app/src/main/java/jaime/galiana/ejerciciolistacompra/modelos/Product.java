package jaime.galiana.ejerciciolistacompra.modelos;

public class Product {
    private String name;
    private int quantity;
    private float price;
    private float total;

    public Product(String name, int quantity, float price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        calcularTotal();
    }

    private void calcularTotal() {
        this.total = price * quantity;
    }

    public float getTotal() {
        return total;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calcularTotal();
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
        calcularTotal();
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", total=" + total +
                '}';
    }
}
