package nl.rug.ai.oop.rpg.model.inventory;

public interface Inventory {
    void addItem(Item item);
    void removeItem(Item item);
    boolean containsItem(Item item);

}
