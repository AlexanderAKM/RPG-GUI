package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class InventoryView extends JPanel implements PropertyChangeListener {
    private final Inventory inventory;
    private final JPanel itemsPanel;

    public InventoryView(Inventory inventory) {
        this.inventory = inventory;
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new BoxLayout(itemsPanel, BoxLayout.Y_AXIS));

        // Listen for changes in the inventory
        inventory.addChangeListener(this);

        // Load initial inventory
        loadInventory();

        // Setup GUI
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(new JLabel("Inventory:"));
        add(itemsPanel);
    }

    // Load items into the inventory
    private void loadInventory() {
        itemsPanel.removeAll();
        List<Item> items = inventory.getItems();
        for (Item item : items) {
            ImageIcon imageIcon = new ImageIcon("resources/" + item.getName() + ".png");
            JButton button = new JButton(item.getName(), imageIcon);
            button.setActionCommand(item.getName());
            itemsPanel.add(button);
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    public JPanel getItemsPanel() {
        return itemsPanel;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // When the inventory changes, reload the inventory
        loadInventory();
    }
}
