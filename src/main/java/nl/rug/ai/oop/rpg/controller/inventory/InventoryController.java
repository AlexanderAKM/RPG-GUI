package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class is responsible for controlling the actions associated with the inventory.
 * @author Alexander Müller
 */
public class InventoryController implements ItemListener {
    private final Inventory inventory;
    private final Player player;

    /**
     * Constructs a new InventoryController with the given inventory, inventory view, and player.
     *
     * @param inventory the inventory to control
     * @param inventoryView the view of the inventory
     * @param player the player who owns the inventory
     */
    public InventoryController(Inventory inventory, InventoryView inventoryView, Player player) {
        this.inventory = inventory;
        this.player = player;
        inventoryView.setItemListener(this);
    }

    /**
     * Responds when an item is clicked in the InventoryView.
     *
     * @param item the item that was clicked
     */
    @Override
    public void onItemClicked(Item item) {
        item.use(player);
        inventory.removeItemByName(item.getName());
    }
}
