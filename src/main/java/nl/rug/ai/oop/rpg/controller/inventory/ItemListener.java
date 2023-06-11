package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Item;

/**
 * This class is responsible for controlling the actions associated with the inventory.
 * @author Alexander Müller
 */
public interface ItemListener {
    // Made this interface to preserve MVC/
    // Without this, InventoryView would have to be used for creating action listeners for each button.
    // This would make it so that the view would interact with the model and this option wouldn't offer a high degree of decoupling.
    void onItemClicked(Item item);
}