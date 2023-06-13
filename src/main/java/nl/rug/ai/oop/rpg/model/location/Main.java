package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.inventory.RoomItemsController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /*Player player = Player.getInstance();
        Inventory inventory = new Inventory();
        //RoomItemsController itemController = new RoomItemsController(inventory, gamePanelGUI, player);
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

        Npc michael = model.getNpc("Michael");
        manager.addNpcs(michael.getName(), michael, manager.getRoom(1));

        Npc evilMan = model.getNpc("Evil Man");
        manager.addNpcs(evilMan.getName(), evilMan, manager.getRoom(1));

        // We get the players current room
        Room currentRoom = player.getCurrentRoom();
        ArrayList<Npc> npcs = currentRoom.getAvailableNpcs();

        //NpcView npcPanel = new NpcView(model, manager, );
        JFrame frame = new JFrame();


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        // Location panel + the npc panel and then we add them to the plane
        //JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        gamePanel.setNpcView(npcPanel);
        npcPanel.setup(model, npcs, npcController, gamePanel, frame);
        //frame.add(npcThing, BorderLayout.NORTH);
        frame.add(locationView, BorderLayout.CENTER); // adds the game panel
        frame.setVisible(true);

        frame.revalidate();
*/
    }
}
