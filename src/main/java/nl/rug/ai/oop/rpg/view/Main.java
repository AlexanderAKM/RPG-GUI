package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.model.players.SaveFiles;
import nl.rug.ai.oop.rpg.view.inventory.InventoryGUI;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        frame.add(new PlayerStatsPane(), BorderLayout.SOUTH);
        //frame.add(new GamePanelGUI(), BorderLayout.CENTER); // adds the game panel
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
