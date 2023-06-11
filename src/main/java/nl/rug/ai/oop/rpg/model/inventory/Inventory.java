package nl.rug.ai.oop.rpg.model.inventory;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;
    private PropertyChangeSupport support;

    public Inventory() {
        items = new ArrayList<>();
        support = new PropertyChangeSupport(this);
    }

    public void addChangeListener(PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    public Item getItemByName(String itemName) {
        for (Item item : items) {
            if (item.getName().equals(itemName)) {
                return item;
            }
        }
        return null;
    }

    public void removeItemByName(String itemName) {
        items.removeIf(item -> item.getName().equals(itemName));
        support.firePropertyChange("items", null, null);
    }

    public boolean containsItem(Item item) {
        return items.contains(item);
    }

    public void addItem(Item item) {
        items.add(item);
        support.firePropertyChange("items", null, null);
    }

    public List<Item> getItems() {
        return items;
    }
}
