package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.items.*;

import java.io.Serializable;
import java.util.Random;

import java.util.*;

/**
 * @author Alexander Müller
 */
public class ItemManager implements Serializable {
    private Map<String, Item> items;

    public ItemManager() {
        items = new HashMap<>();
        initItems();
    }

    private void initItems() {
        items.put("Alcohol", new Alcohol(10, 5));
        items.put("Books", new Books(15, 10));
        items.put("Coffee", new Coffee(5, 15));
        items.put("Money", new Money(100));
        items.put("StudentCard", new StudentCard());
    }

    public ArrayList<Item> getItemsForRoom(String... itemNames) {
        ArrayList<Item> itemsForRoom = new ArrayList<>();
        for (String itemName : itemNames) {
            if (items.containsKey(itemName)) {
                itemsForRoom.add(items.get(itemName));
            }
        }
        return itemsForRoom;
    }
    public Collection<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }


}

