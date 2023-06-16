package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.GameController;
import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.inventory.InventoryController;
import nl.rug.ai.oop.rpg.controller.inventory.RoomItemsController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.location.languageManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class SetUp implements PropertyChangeListener{
    //Setting up the main frame
    private JFrame frame;

    /**
     * @author Alexander Müller & Robert Hielkema & Victoria Polaka & Kikis Hjikakou
     * @param
     */

    public void start(languageManager languageManager, Game game) {
        // Create a player
        Player player = Player.getInstance();

        // Create an inventory and add some items
        Inventory inventory = player.getInventory();

        // Create the inventory view and controller
        InventoryView inventoryView = new InventoryView(inventory, languageManager);
        new InventoryController(inventory, inventoryView, player);

        // Create the main frame and add the inventory view and PlayerStatsPane
        frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        player.addChangeListener(this);

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
        LocationManager manager = new LocationManager(itemManager, languageManager);
        LocationController controller = new LocationController(manager, languageManager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller, languageManager);

        RoomItemsController roomItemsController = new RoomItemsController(inventory, gamePanel, player, manager);

        // Connect the RoomItemsController to the GamePanelGUI
        gamePanel.setItemListener(roomItemsController);

        NpcManager model = new NpcManager(manager);
        NpcController npcController = new NpcController(model);

        // Test
        Npc bob = model.getNpc("Bob");
        manager.addNpcs("Bob", bob, manager.getRoom(0));

        Npc harmen = model.getNpc("Harmen");
        manager.addNpcs("Harmen", harmen, manager.getRoom(0));

        Npc michael = model.getNpc("Michael");
        manager.addNpcs(michael.getName(), michael, manager.getRoom(1));

        Npc evilMan = model.getNpc("Evil Man");
        manager.addNpcs(evilMan.getName(), evilMan, manager.getRoom(1));

        manager.addNpcs("Human man", model.getNpc("Human man"), manager.getRoom(1));

        // We get the players current room
        Room currentRoom = player.getCurrentRoom();
        ArrayList<Npc> npcs = currentRoom.getAvailableNpcs();

        NpcView npcPanel = new NpcView(model, manager, playerStatsPane);


        JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        gamePanel.setNpcView(npcPanel);
        npcPanel.setup(model, npcs, npcController, gamePanel, frame);

        //setup GridBagLayout for LocationView (game panel)
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(locationView, c); // adds the game panel

        GameController gameController = new GameController(game, manager);
        //SavePanel saveGamePanel = new SavePanel(gameController);
        SavePanel savePanel = new SavePanel(gameController);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.0;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(savePanel, c);

        //frame.add(saveGamePanel);

        frame.revalidate();

        frame.setSize(800, 600);
        frame.setVisible(true);
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("lowWellbeing")){
            frame.getContentPane().removeAll();
            frame.repaint();
            frame.setLayout(new BorderLayout());
            JLabel message = new JLabel("YOU DIED", SwingConstants.CENTER);
            message.setFont(new Font("Serif", Font.BOLD, 50));
            frame.add(message, BorderLayout.CENTER);
            frame.revalidate();
            frame.repaint();
        }
    }
}
