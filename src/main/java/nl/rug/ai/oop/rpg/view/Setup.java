package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.inventory.InventoryController;
import nl.rug.ai.oop.rpg.controller.inventory.RoomItemsController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.location.RoomLanguageManager;
import nl.rug.ai.oop.rpg.model.location.RoomStateManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;
import nl.rug.ai.oop.rpg.view.players.PlayerStatsPane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Setup {
    //Main class for the JFrame which should include everyone's panes
    public String chosenLanguage;


    /**
     * @author Alexander Müller & Robert Hielkema
     * @param
     */

    public void start(RoomLanguageManager roomLanguageManager) {
        // Create a player
        Player player = Player.getInstance();

        // Create an inventory and add some items
        Inventory inventory = player.getInventory();

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
        LocationManager manager = new LocationManager(itemManager, roomLanguageManager);
        LocationController controller = new LocationController(manager, roomLanguageManager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller, roomLanguageManager);

        RoomItemsController roomItemsController = new RoomItemsController(inventory, gamePanel, player, manager);

        // Connect the RoomItemsController to the GamePanelGUI
        gamePanel.setItemListener(roomItemsController);

        NpcManager model = new NpcManager();
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

        NpcView npcPanel = new NpcView(model, manager);


        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(500, 500);

        JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        gamePanel.setNpcView(npcPanel);
        npcPanel.setup(model, npcs, npcController, gamePanel, frame);
        //frame.add(npcThing, BorderLayout.NORTH);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(locationView, c); // adds the game panel
        //frame.setVisible(true);

        //gamePanel.frameSetUp();
        frame.revalidate();

        frame.setSize(800, 600);
        frame.setVisible(true);
    }


    public void saveGame(LocationManager locManager) {
        RoomStateManager.saveRoomState(locManager, "room_state.ser");
        Player.getInstance().save("player");
    }

    public void loadSavedGame(LocationManager locManager){
        Player.getInstance().loadSaveFile("player");
        locManager = RoomStateManager.loadRoomState("room_state.ser");
    }
}
