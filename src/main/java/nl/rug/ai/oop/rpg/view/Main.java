package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.inventory.InventoryController;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;
import nl.rug.ai.oop.rpg.model.inventory.items.Books;
import nl.rug.ai.oop.rpg.model.inventory.items.Coffee;
import nl.rug.ai.oop.rpg.model.inventory.items.Money;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.model.players.SaveFiles;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    //Main class for the JFrame which should include everyone's panes

    /**
     * @author Alexander Müller & Robert Hielkema
     * @param args
     */

    public static void main(String[] args) {
        // Create a player
        Player player = Player.getInstance();

        // Create an inventory and add some items
        Inventory inventory = new Inventory();
        inventory.addItem(new Alcohol(5, 10));
        inventory.addItem(new Books(10, 5));
        inventory.addItem(new Coffee(5, 5));
        inventory.addItem(new Money(100));

        for (Item item : inventory.getItems()) {
            System.out.println(item.getName());
        }

        // Create the inventory view and controller
        InventoryView inventoryView = new InventoryView(inventory);
        new InventoryController(inventory, inventoryView, player);

        // Create the main frame and add the inventory view and PlayerStatsPane
        JFrame frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        //setup GridBagLayout for inventory view
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 3;
        c.gridx = 2;
        c.gridy = 0;
        frame.add(inventoryView, c);

        //setup GridBagLayout for PlayerStatsPane
        PlayerStatsPane playerStatsPane = new PlayerStatsPane();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(playerStatsPane, c);

        frame.setSize(800, 600);
        frame.setVisible(true);
    }


    /**
     * @author RobertHielkema
     */
    public void loadOrNewGame(JFrame frame){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        JButton newGame = new JButton("New Game");
        newGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to panel to create game with choice of language and player course
            }
        });
        JButton loadGame = new JButton("Load from savefile");
        loadGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //go to panel to load game
            }
        });
        panel.add(newGame);
        panel.add(loadGame);
        frame.add(panel);
        frame.revalidate();
    }

}
