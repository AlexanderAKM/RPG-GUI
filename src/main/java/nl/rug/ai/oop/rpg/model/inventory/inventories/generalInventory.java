package nl.rug.ai.oop.rpg.model.inventory.inventories;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;
import java.util.List;

public class generalInventory implements Inventory {

    private List<Item> items;

    public generalInventory() {
        items = new ArrayList<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

        // Implement other inventory-related methods
}
