package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;

public class InventoryView extends JPanel implements PropertyChangeListener {
    private final Inventory inventory;
    private final JPanel itemsPanel;

    public InventoryView(Inventory inventory) {
        this.inventory = inventory;
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 1));

        // Setup GUI
        setLayout(new BorderLayout());
        add(new JLabel("Inventory:"), BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);

        // Listen for changes in the inventory
        this.inventory.addChangeListener(this);

        // Load initial inventory
        loadInventory();
    }

    // Load items into the inventory
    private void loadInventory() {
        itemsPanel.removeAll();
        List<Item> items = this.inventory.getItems();
        for (Item item : items) {
            URL resourceUrl = getClass().getClassLoader().getResource(item.getName() + ".png");
            if (resourceUrl == null) {
                System.out.println("Failed to load image for item: " + item.getName());
                continue;
            }
            ImageIcon imageIcon = new ImageIcon(resourceUrl);
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


