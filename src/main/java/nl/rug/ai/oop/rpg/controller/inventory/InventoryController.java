package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InventoryController {
    private final Inventory inventory;
    private final Player player;

    public InventoryController(Inventory inventory, InventoryView inventoryView, Player player) {
        this.inventory = inventory;
        this.player = player;

        // Add action listeners to each button in the inventory view
        for (Component component : inventoryView.getItemsPanel().getComponents()) {
            if (component instanceof JButton) {
                JButton button = (JButton) component;
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
}
