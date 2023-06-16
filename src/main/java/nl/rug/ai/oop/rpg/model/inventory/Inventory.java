package nl.rug.ai.oop.rpg.model.inventory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * This class represents the player's inventory in the game, containing a list of items.
 * @author Alexander Müller
 */
public class Inventory implements Serializable {
    private List<Item> items;
    private PropertyChangeSupport support;

    /**
     * Constructs a new Inventory with an empty list of items.
     */
    public Inventory() {
        items = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    /**
     * Adds a change listener to this inventory.
     *
     * @param pcl the change listener to add
     */
    public void addChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Retrieves an item from the inventory by its name.
     *
     * @param itemName the name of the item to retrieve
     * @return the item if found, or null if not found
     */
    public Item getItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Removes an item from the inventory by its name.
     *
     * @param itemName the name of the item to remove
     */
    public void removeItemByName(String itemName) {
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            if (item.getName().equals(itemName)) {
                iterator.remove();
                break; // Stop after removing the first occurrence
            }
        }
        support.firePropertyChange("items", null, null);
    }

    /**
     * Checks whether the inventory contains a specific item.
     *
     * @param item the item to check for
     * @return true if the item is in the inventory, false otherwise
     */
    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    /**
     * Adds an item to the inventory.
     *
     * @param item the item to add
     */
    public void addItem(Item item) {
        items.add(item);
        support.firePropertyChange("items", null, null);
    }

    /**
     * Retrieves all items in the inventory.
     *
     * @return a list of all items in the inventory
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }
}

