package wiki.dynamodb.onlineshop.domain;

import java.util.Objects;

public class ShopItem {
    private final String id;
    private final String name;
    private final String description;
    private final Integer amount;

    public ShopItem(String id, String name, String description, Integer amount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "ShopItem{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShopItem shopItem = (ShopItem) o;
        return Objects.equals(id, shopItem.id) &&
                Objects.equals(name, shopItem.name) &&
                Objects.equals(description, shopItem.description) &&
                Objects.equals(amount, shopItem.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, amount);
    }
}
