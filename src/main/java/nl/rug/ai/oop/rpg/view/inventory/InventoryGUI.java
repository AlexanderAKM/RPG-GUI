package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InventoryGUI extends JPanel {
    public static final Map<String, BufferedImage> itemImages = new HashMap<>();
    public static final String ITEM_IMAGE_PATH = "/resources/";
    public static final int ITEM_WIDTH = 120;
    public static final int ITEM_HEIGHT = 150;

    private Inventory inventory;
    private DefaultListModel<String> itemListModel;
    private JList<String> itemList;

    static {
        loadItemImages();
    }

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;

        setLayout(new BorderLayout());

        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Item item = new Alcohol(0, 0);
                String itemName = item.getName();
                inventory.addItem(item);
                itemListModel.addElement(itemName);
            }
        });
        add(addItemButton, BorderLayout.SOUTH);
    }

    private static void loadItemImages() {
        try {
            itemImages.put("alcohol", ImageIO.read(InventoryGUI.class.getResource(ITEM_IMAGE_PATH + "alcohol.png")));
            // Add more items as needed
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < itemListModel.size(); i++) {
            String itemName = itemListModel.get(i);
            BufferedImage itemImage = itemImages.get(itemName.toLowerCase());
            if (itemImage != null) {
                int x = (getWidth() - ITEM_WIDTH) / 2;
                int y = (getHeight() - ITEM_HEIGHT) / 2;
                g.drawImage(itemImage, x, y, ITEM_WIDTH, ITEM_HEIGHT, null);
            }
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(300, 400);
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
        itemListModel.clear();
        for (Item item : inventory.getItems()) {
            String itemName = item.getName();
            itemListModel.addElement(itemName);
        }
        repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Inventory inventory = new Inventory();
                Item item = new Alcohol(0, 0);
                inventory.addItem(item);

                JFrame frame = new JFrame("Inventory");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                InventoryGUI inventoryGUI = new InventoryGUI(inventory);
                frame.getContentPane().add(inventoryGUI);
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

                // Example of updating the inventory and view
                inventory.addItem(new Alcohol(0, 0));
                inventory.addItem(new Alcohol(0, 0));
                inventoryGUI.setInventory(inventory);
            }
        });
    }
}
