package nl.rug.ai.oop.rpg.model.inventory;

import nl.rug.ai.oop.rpg.model.inventory.items.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * The ItemManager class is responsible for managing all items in the RPG game.
 * It stores all items in a map and provides methods to retrieve items by name or get all items.
 *
 * @author Alexander Müller
 */
public class ItemManager implements Serializable {
    private Map<String, Item> items;

    /**
     * Constructs a new ItemManager instance and initializes the items.
     */
    public ItemManager() {
        items = new HashMap<>();
        initItems();
    }

    /**
     * Initializes the items.
     */
    private void initItems() {
        items.put("Alcohol", new Alcohol(10, 5));
        items.put("Books", new Books(15, 10));
        items.put("Coffee", new Coffee(5, 15));
        items.put("Money", new Money(20));
        items.put("StudentCard", new StudentCard());
    }

    /**
     * Returns a list of items for a room given their names.
     *
     * @param itemNames the names of the items
     * @return a list of Item objects
     */
    public ArrayList<Item> getItemsForRoom(String... itemNames) {
        ArrayList<Item> itemsForRoom = new ArrayList<>();
        for (String itemName : itemNames) {
            if (items.containsKey(itemName)) {
                itemsForRoom.add(items.get(itemName));
            }
        }
        return itemsForRoom;
    }

    /**
     * Returns all items.
     *
     * @return a collection of all Item objects
     */
    public Collection<Item> getAllItems() {
        return new ArrayList<>(items.values());
    }

    /**
     * Returns an item given its name.
     *
     * @param itemName the name of the item
     * @return the Item object with the given name
     */
    public Item getItemByName(String itemName) {
        return items.get(itemName);
    }
}
