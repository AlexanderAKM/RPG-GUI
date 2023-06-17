package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Item;

/**
 * This class is responsible for controlling the actions associated with the inventory.
 * @author Alexander Müller
 */
public interface ItemListener {
    public void onItemClicked(Item item);
}