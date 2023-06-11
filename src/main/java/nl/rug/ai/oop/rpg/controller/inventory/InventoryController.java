package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryController implements ItemListener {
    private final Inventory inventory;
    private final Player player;

    public InventoryController(Inventory inventory, InventoryView inventoryView, Player player) {
        this.inventory = inventory;
        this.player = player;

        // The controller is registered as an ItemListener for the inventory view
        inventoryView.setItemListener(this);
    }

    // When an item is clicked in the InventoryView, this method is called
    @Override
    public void onItemClicked(Item item) {
        // Use the item and remove it from the inventory
        item.use(player);
        inventory.removeItemByName(item.getName());
    }
}

