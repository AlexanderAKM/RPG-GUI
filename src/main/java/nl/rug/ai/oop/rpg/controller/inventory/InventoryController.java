package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryController {
    private final Inventory inventory;
    private final InventoryView inventoryView;
    private final Player player;

    public InventoryController(Inventory inventory, InventoryView inventoryView, Player player) {
        this.inventory = inventory;
        this.inventoryView = inventoryView;
        this.player = player;

        // Add action listeners to each button in the inventory view
        for (int i = 0; i < inventoryView.getItemsPanel().getComponentCount(); i++) {
            JButton button = (JButton) inventoryView.getItemsPanel().getComponent(i);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String itemName = e.getActionCommand();
                    Item item = inventory.getItemByName(itemName);
                    if (item != null) {
                        item.use(player);
                        inventory.removeItemByName(itemName);
                    }
                }
            });
        }
    }
}
