package nl.rug.ai.oop.rpg.view.inventory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.inventories.generalInventory;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;

public class InventoryGUI extends JFrame {
    private Inventory inventory;
    private DefaultListModel<Item> itemListModel;
    private JList<Item> itemList;

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;

        // Create the main frame
        setTitle("Inventory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Create the item list and scroll pane
        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        // Create the button to add a new item
        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // This should be so that a new item is added to the inventory whenever that is supposed to happen, i'll fix later
                String itemName = "Alcohol";  //JOptionPane.showInputDialog("Enter the item name:");
                Item item;
                item = new Alcohol(0, 0);
                inventory.addItem(item);
                itemListModel.addElement(item);
            }
        });
        add(addItemButton, BorderLayout.SOUTH);
    }

    private void addItemToInventory(Item item) {
        inventory.addItem(item);
        itemListModel.addElement(item);
        itemList.revalidate();
        itemList.repaint();
    }

    private void removeItemFromInventory(Item item) {
        inventory.removeItem(item);
        itemListModel.removeElement(item);
        itemList.revalidate();
        itemList.repaint();
    }

    public static void main(String[] args) {
        Inventory inventory = new generalInventory();
        SwingUtilities.invokeLater(new Runnable() { // Ensure that the GUI follows proper threading guidelines
            @Override
            public void run() {
                InventoryGUI gui = new InventoryGUI(inventory);
                gui.setVisible(true);
            }
        });
    }
}
