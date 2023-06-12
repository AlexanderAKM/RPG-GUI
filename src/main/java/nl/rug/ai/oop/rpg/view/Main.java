package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.inventory.InventoryController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.inventory.items.Alcohol;
import nl.rug.ai.oop.rpg.model.inventory.items.Books;
import nl.rug.ai.oop.rpg.model.inventory.items.Coffee;
import nl.rug.ai.oop.rpg.model.inventory.items.Money;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.model.players.SaveFiles;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;
import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {
    //Main class for the JFrame which should include everyone's panes

    /**
     * @author Alexander Müller & Robert Hielkema & Kikis Hjikakou & Victoria Polaka
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

        ItemManager itemManager = new ItemManager();
        LocationManager manager = new LocationManager(itemManager);
        LocationController controller = new LocationController(manager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller);

        NpcManager model = new NpcManager();
        NpcController npcController = new NpcController(model);

        // Test
        Npc bob = model.getNpc("Bob");
        manager.addNpcs("Bob", bob, manager.getRoom(0));

        Npc harmen = model.getNpc("Harmen");
        manager.addNpcs("Harmen", harmen, manager.getRoom(0));

        // We get the players current room
        Room currentRoom = player.getCurrentRoom();
        ArrayList<Npc> npcs = currentRoom.getAvailableNpcs();

        NpcView npcPanel = new NpcView(model);



        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500, 500);

        JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        npcPanel.setup(model, npcs, npcController, gamePanel, frame);
        //frame.add(npcThing, BorderLayout.NORTH);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(locationView, c); // adds the game panel

        frame.revalidate();

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
