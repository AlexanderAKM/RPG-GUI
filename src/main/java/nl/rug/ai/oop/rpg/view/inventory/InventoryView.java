package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.controller.inventory.ItemListener;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.location.RoomLanguageManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.List;

/**
 * This class represents the visual display of the player's inventory in the game.
 * @author Alexander Müller
 */
public class InventoryView extends JPanel implements PropertyChangeListener {
    private final Inventory inventory;
    private final RoomLanguageManager roomLanguageManager;
    private final JPanel itemsPanel;
    private ItemListener itemListener;
    private JLabel inventoryLabel;
    public static int ITEM_WIDTH = 75;
    public static int ITEM_LENGTH = 100;
    public static int VIEWWIDTH = 200;
    public static int VIEWHEIGHT = 250;

    /**
     * Creates an InventoryView with the given Inventory.
     *
     * @param inventory the inventory to display
     */
    public InventoryView(Inventory inventory, RoomLanguageManager roomLanguageManager) {
        this.inventory = inventory;
        this.roomLanguageManager = roomLanguageManager;
        itemsPanel = new JPanel();
        itemsPanel.setLayout(new GridLayout(0, 2));
        setLayout(new BorderLayout());
        inventoryLabel = new JLabel(roomLanguageManager.getTranslation("Inventory"));
        add(inventoryLabel, BorderLayout.NORTH);
        add(itemsPanel, BorderLayout.CENTER);
        this.inventory.addChangeListener(this);
        loadInventory(roomLanguageManager);
        Dimension size = new Dimension(VIEWWIDTH, VIEWHEIGHT);
        this.setPreferredSize(size);
        this.setMaximumSize(size);
    }

    /**
     * Sets the ItemListener for the InventoryView.
     *
     * @param itemListener the item listener to set
     */
    public void setItemListener(ItemListener itemListener) {
        this.itemListener = itemListener;
    }

    /**
     * Loads items into the inventory.
     */
    private void loadInventory(RoomLanguageManager roomLanguageManager) {
        itemsPanel.removeAll();
        List<Item> items = this.inventory.getItems();
        for (Item item : items) {
            URL resourceUrl = getClass().getClassLoader().getResource(item.getName() + ".png");
            if (resourceUrl == null) {
                System.out.println(roomLanguageManager.getTranslation("Image_Fail") + roomLanguageManager.getTranslation(item.getName()));
                continue;
            }
            ImageIcon imageIcon = new ImageIcon(resourceUrl);
            ImageIcon resizedIcon = resizeImageIcon(imageIcon, ITEM_WIDTH, ITEM_LENGTH);
            JButton button = new JButton(roomLanguageManager.getTranslation(item.getName()), resizedIcon);
            button.setActionCommand(roomLanguageManager.getTranslation(item.getName()));
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String itemName = e.getActionCommand();
                    Item item = inventory.getItemByName(itemName);
                    if (item != null && itemListener != null) {
                        itemListener.onItemClicked(item);
                    }
                }
            });
            itemsPanel.add(button);
        }
        itemsPanel.revalidate();
        itemsPanel.repaint();
    }

    /**
     * Returns the panel of items.
     *
     * @return the items panel
     */
    public JPanel getItemsPanel() {
        return itemsPanel;
    }

    /**
     * Resizes an ImageIcon to the given dimensions.
     *
     * @param icon the icon to resize
     * @param width the new width
     * @param height the new height
     * @return the resized ImageIcon
     */
    public ImageIcon resizeImageIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /**
     * Responds to a property change event (like the inventory changing).
     *
     * @param evt the event to respond to
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        loadInventory(roomLanguageManager);
    }
}
