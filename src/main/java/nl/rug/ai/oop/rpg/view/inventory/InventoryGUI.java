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

    /*static {
        loadItemImages();
    }*/

    public InventoryGUI(Inventory inventory) {
        this.inventory = inventory;

        setLayout(new BorderLayout());

        itemListModel = new DefaultListModel<>();
        itemList = new JList<>(itemListModel);
        JScrollPane scrollPane = new JScrollPane(itemList);
        add(scrollPane, BorderLayout.CENTER);

        setLayout(new GridLayout(2, 2));
        JButton addItemButton = new JButton("Add Item");

        addItemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImageIcon resizedIcon1 = resizeImageIcon(new ImageIcon(InventoryGUI.class.getResource("/Alcohol.png")));
                JLabel item1 = new JLabel(resizedIcon1);
                add(item1);

                ImageIcon resizedIcon2 = resizeImageIcon(new ImageIcon(InventoryGUI.class.getResource("/Books.png")));
                JLabel item2 = new JLabel(resizedIcon2);
                add(item2);

                ImageIcon resizedIcon3 = resizeImageIcon(new ImageIcon(InventoryGUI.class.getResource("/Coffee.png")));
                JLabel item3 = new JLabel(resizedIcon3);
                add(item3);

                ImageIcon resizedIcon4 = resizeImageIcon(new ImageIcon(InventoryGUI.class.getResource("/Money.png")));
                JLabel item4 = new JLabel(resizedIcon4);
                add(item4);
                //item = new JLabel(new ImageIcon(InventoryGUI.class.getResource("/Alcohol.png")));
                //String itemName = item.getName();
                //inventory.addItem(item);
                //itemListModel.addElement(itemName);
            }
        });
        add(addItemButton, BorderLayout.SOUTH);
    }

    private ImageIcon resizeImageIcon(ImageIcon icon) {
        Image image = icon.getImage();
        Image scaledImage = image.getScaledInstance(ITEM_WIDTH, ITEM_HEIGHT, Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /*private static void loadItemImages() {
        try {
            itemImages.put("Alcohol", ImageIO.read(InventoryGUI.class.getResource(ITEM_IMAGE_PATH + "Alcohol.png")));
            // Add more items as needed
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }*/

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

                inventoryGUI.setInventory(inventory);
            }
        });
    }
}
