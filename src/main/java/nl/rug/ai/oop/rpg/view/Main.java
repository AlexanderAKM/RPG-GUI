package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryGUI;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    //Main class for the JFrame which should include everyone's panes

    /**
     * @author Alexander Müller
     * @param args
     */
    public static void main(String[] args) {
        Player player = Player.getInstance();
        Inventory inventory = new Inventory();
        Item item = new Alcohol(0,0);
        inventory.addItem(item);
        player.setInventory(inventory);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.add(new InventoryGUI(Player.getInstance().getInventory()), BorderLayout.EAST);
        //frame.add(new GamePanelGUI(), BorderLayout.CENTER); // adds the game panel
        frame.setVisible(true);
    }
}
