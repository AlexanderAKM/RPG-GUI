package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.GameController;
import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.inventory.InventoryController;
import nl.rug.ai.oop.rpg.controller.inventory.RoomItemsController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.GameManager;
import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.NpcManager;
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

/**
 *  This class sets up the game's user interface and initializes the game's components.
 *
 * @author Alexander Müller & Robert Hielkema & Victoria Polaka & Kyriakos Hjikakou
 */
public class SetUp implements PropertyChangeListener{
    private JFrame frame;

    /**
     * Starts the game by setting up the user interface and initializing the game's components.
     *
     * @param chosenLanguage the language chosen by the player
     */
    public void start(String chosenLanguage) {
        Player player = Player.getInstance();

        Inventory inventory = player.getInventory();

        LanguageManager roomLanguageManager = new LanguageManager();
        LanguageManager npcLanguageManager = new LanguageManager();
        LanguageManager inventoryLanguageManager = new LanguageManager();

        roomLanguageManager.loadLanguage(chosenLanguage, "roomTranslations.roomTranslations");
        npcLanguageManager.loadLanguage(chosenLanguage, "npcLocalisations.npcLocalisations");
        inventoryLanguageManager.loadLanguage(chosenLanguage, "inventoryTranslations.inventoryTranslations");

        InventoryView inventoryView = new InventoryView(inventory, inventoryLanguageManager);
        new InventoryController(inventory, inventoryView, player);

        frame = new JFrame("RPG Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        player.addChangeListener(this);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridheight = 2;
        c.gridx = 2;
        c.gridy = 1;
        frame.add(inventoryView, c);

        PlayerStatsPane playerStatsPane = new PlayerStatsPane();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridx = 0;
        c.gridy = 2;
        frame.add(playerStatsPane, c);

        ItemManager itemManager = new ItemManager();
        LocationManager manager = new LocationManager(itemManager, roomLanguageManager);
        GameManager gameManager = new GameManager();
        LocationController controller = new LocationController(manager, roomLanguageManager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller, roomLanguageManager);

        RoomItemsController roomItemsController = new RoomItemsController(inventory, gamePanel, player, manager);

        gamePanel.setItemListener(roomItemsController);
        NpcManager model = new NpcManager(manager, itemManager, npcLanguageManager);
        NpcController npcController = new NpcController(model);


        Room currentRoom = player.getCurrentRoom();
        ArrayList<Npc> npcs = currentRoom.getAvailableNpcs();

        NpcView npcPanel = new NpcView(model, manager, playerStatsPane);


        JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        gamePanel.setNpcView(npcPanel);
        npcPanel.setup(model, npcController, gamePanel, frame);


        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridwidth = 2;
        c.gridheight = 2;
        c.gridx = 0;
        c.gridy = 0;
        frame.add(locationView, c);


        GameController gameController = new GameController(gameManager, manager, gamePanel);
        SavePanel savePanel = new SavePanel(gameController, roomLanguageManager);
        c.fill = GridBagConstraints.VERTICAL;
        c.weightx = 0.0;
        c.gridheight = 1;
        c.gridx = 2;
        c.gridy = 0;
        frame.add(savePanel, c);

        frame.revalidate();
        frame.setSize(1000, 600);
        frame.setVisible(true);
    }

    /**
     * @author Robert Hielkema
     * Responds to the wellbeing of the player going below zero.
     *
     * @param evt The PropertyChangeEvent object.
     */
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
