package nl.rug.ai.oop.rpg.model.inventory;

import java.util.List;

public interface Inventory {
    void addItem(Item item);
    void removeItem(Item item);
    boolean containsItem(Item item);

    public List<Item> getItems();
}
