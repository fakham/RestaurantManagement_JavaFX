package util;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class FactureView {
    private final SimpleStringProperty item;
    private final SimpleStringProperty type;
    private final SimpleDoubleProperty price;

    public FactureView(SimpleStringProperty item, SimpleStringProperty type, SimpleDoubleProperty price) {
        this.item = item;
        this.type = type;
        this.price = price;
    }

    public String getItem() {
        return item.get();
    }

    public SimpleStringProperty itemProperty() {
        return item;
    }

    public void setItem(String item) {
        this.item.set(item);
    }

    public String getType() {
        return type.get();
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
