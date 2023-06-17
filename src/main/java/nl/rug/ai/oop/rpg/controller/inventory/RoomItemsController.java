package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

/**
 * The RoomItemsController class handles interactions with room items in the game.
 * It implements the ItemListener interface to respond to item click events.
 * It allows the player to remove an item from the room and add it to their inventory.
 * @author Alexander Müller
 */
public class RoomItemsController implements ItemListener {

    private final Inventory inventory;
    private final Player player;
    private final LocationManager locationManager;

    /**
     * Constructs a new RoomItemsController object.
     *
     * @param inventory         the Inventory object representing the player's inventory
     * @param gamePanelGUI      the GamePanelGUI object representing the game panel GUI
     * @param player            the Player object representing the game player
     * @param locationManager  the LocationManager object representing the game's location manager
     */
    public RoomItemsController(Inventory inventory, GamePanelGUI gamePanelGUI, Player player, LocationManager locationManager) {
        this.inventory = inventory;
        this.player = player;
        this.locationManager = locationManager;
        gamePanelGUI.setItemListener(this);
    }

    /**
     * Handles the item click event.
     *
     * @param item  the Item object that was clicked
     */
    @Override
    public void onItemClicked(Item item) {
        locationManager.removeItemActions(item, player.getCurrentRoom());
        inventory.addItem(item);
    }
}
