package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.inventory.items.StudentCard;
import nl.rug.ai.oop.rpg.model.players.Player;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The LocationManager class manages the locations (rooms) in the game.
 * It implements the LocationInterface and Serializable interfaces.
 *
 * @author Victoria Polaka
 */
public class LocationManager implements LocationInterface, Serializable {
    private ArrayList<Room> map;
    private ArrayList<Room> availableRooms;

    private languageManager languageManager;

    private ItemManager manager;
    private transient ArrayList<PropertyChangeListener> listeners;

    /**
     * Constructs a LocationManager object with the specified ItemManager and RoomLanguageManager.
     *
     * @param manager             The ItemManager for managing items.
     * @param languageManager The RoomLanguageManager for managing room language translations.
     */
    public LocationManager(ItemManager manager, languageManager languageManager){
        this.manager = manager;
        map = new ArrayList<Room>();
        listeners = new ArrayList();
        availableRooms = new ArrayList<Room>();
        this.languageManager = languageManager;
        locationSetUp();
    }

    /**
     * Retrieves the room at the specified index from the map.
     *
     * @param index The index of the room in the map.
     * @return The room at the specified index.
     */
    public Room getRoom(int index){
        return map.get(index);
    }

    /**
     * Sets up the locations in the game.
     */
    public void locationSetUp() {
        // Get items for each room
        ArrayList<Item> coverItems = manager.getItemsForRoom("Coffee", "Alcohol");
        ArrayList<Item> homeItems = manager.getItemsForRoom("Books", "Alcohol", "StudentCard","Money");
        ArrayList<Item> canteenItems = manager.getItemsForRoom("Coffee");
        ArrayList<Item> outsideItems = manager.getItemsForRoom("Money", "Money");
        ArrayList<Item> examHallItems = manager.getItemsForRoom("Books");

        // Create rooms using RoomBuilder (builder design pattern) and initialize their properties
        Room home = new RoomBuilder()
                .setName(languageManager.getTranslation("home"))
                .setDescription(languageManager.getTranslation("home_description"))
                .setNorth(1)
                .setEast(-1)
                .setSouth(-1)
                .setWest(-1)
                .setIsLocked(false)
                .addAvailableItems(homeItems)
                .build();

        Room outside = new RoomBuilder()
                .setName(languageManager.getTranslation("outside"))
                .setDescription(languageManager.getTranslation("outside_description"))
                .setNorth(3)
                .setEast(-1)
                .setSouth(0)
                .setWest(2)
                .setIsLocked(false)
                .addAvailableItems(outsideItems)
                .build();

        Room examHall = new RoomBuilder()
                .setName(languageManager.getTranslation("Aletta_Jacobs_Hall"))
                .setDescription(languageManager.getTranslation("Aletta_Jacobs_Hall_description"))
                .setNorth(-1)
                .setEast(1)
                .setSouth(-1)
                .setWest(-1)
                .setIsLocked(false)
                .addAvailableItems(examHallItems)
                .setRequiredItem(new StudentCard())
                .build();

        Room bb = new RoomBuilder()
                .setName(languageManager.getTranslation("bernoulliborg"))
                .setDescription(languageManager.getTranslation("bernoulliborg_description"))
                .setNorth(5)
                .setEast(-1)
                .setSouth(1)
                .setWest(4)
                .setIsLocked(false)
                //.addAvailableItems(bbItems)
                //.setRequiredItem()
                .build();

        Room canteen = new RoomBuilder()
                .setName(languageManager.getTranslation("TheBBCanteen"))
                .setDescription(languageManager.getTranslation("TheBBCanteen_description"))
                .setNorth(3)
                .setEast(-1)
                .setSouth(-1)
                .setWest(2)
                .setIsLocked(false)
                .addAvailableItems(canteenItems)
                //.setRequiredItem()
                .build();

        Room coverRoom = new RoomBuilder()
                .setName(languageManager.getTranslation("cover_room"))
                .setDescription(languageManager.getTranslation("cover_room_description"))
                .setNorth(-1)
                .setEast(-1)
                .setSouth(3)
                .setWest(-1)
                .setIsLocked(true)
                .addAvailableItems(coverItems)
                .build();

        // Set the current room for the player
        Player.getInstance().setCurrentRoom(home);

        // Add rooms to the map in a specific order
        map.add(home);
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);

    }
    /**
     * Adds an NPC to a room.
     *
     * @param npcName The name of the NPC.
     * @param npc The NPC object to add.
     * @param room The room where the NPC will be added.
     */
    @Override
    public void addNpcs(String npcName, Npc npc, Room room) {
        room.getAvailableNpcs().add(npc);
    }

    /**
     * Removes an NPC from a room.
     *
     * @param npcName The name of the NPC.
     * @param npc The NPC object to remove.
     * @param room The room where the NPC will be removed from.
     */
    @Override
    public void removeNpcs(String npcName, Npc npc, Room room) {
        room.getAvailableNpcs().remove(npc);
    }

    /**
     * Adds an item to a room.
     *
     * @param x The item to add.
     * @param room The room where the item will be added.
     */
    @Override
    public void addItemActions(Item x, Room room) {
        room.getAvailableItems().add(x);
    }

    /**
     * Removes an item from a room.
     *
     * @param x The item to remove.
     * @param room The room where the item will be removed from.
     */
    @Override
    public void removeItemActions(Item x, Room room) {
        room.getAvailableItems().remove(x);
    }
    /**
     * Returns a list of available rooms adjacent to the current room.
     *
     * @param currentRoom The current room.
     * @return An ArrayList of available rooms adjacent to the current room.
     */
    @Override
    public ArrayList<Room> roomsAvailable(Room currentRoom) {
        availableRooms.clear();
        if (Player.getInstance().getCurrentRoom().getNorth() >= 0) {
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getNorth()));
        }
        if (Player.getInstance().getCurrentRoom().getEast() >= 0) {
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getEast()));
        }
        if (Player.getInstance().getCurrentRoom().getSouth() >= 0) {
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getSouth()));
        }
        if (Player.getInstance().getCurrentRoom().getWest() >= 0) {
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getWest()));
        }
        return availableRooms;
    }

    /**
     * Notifies all registered listeners of a property change event.
     *
     * @param payload The PropertyChangeEvent payload.
     */
    private void notifyListeners(PropertyChangeEvent payload) {
        Iterator<PropertyChangeListener> allListeners = listeners.iterator();

        while (allListeners.hasNext()) {
            allListeners.next().propertyChange(payload);
        }
    }

    /**
     * Adds a property change listener.
     *
     * @param listener The PropertyChangeListener to add.
     */
    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    /**
     * Moves the player to the specified room.
     *
     * @param chosenRoom The index of the chosen room in the available rooms list.
     */
    @Override
    public void movePlayer(int chosenRoom) {
        Room destinationRoom = getRoom(chosenRoom);

        if (destinationRoom.getRequiredItem() != null) {
            // If the room requires an item
            if (Player.getInstance().getInventory().containsItem(destinationRoom.getRequiredItem())) {
                // If the player has the required item, allow them to move
                Player.getInstance().setCurrentRoom(destinationRoom);
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
                notifyListeners(payload);
            } else {
                // The player doesn't have the required item, they go there
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "popUp", null, null);
                notifyListeners(payload);
            }
        } else {
            // If the room does not require an item
            if (destinationRoom.getIsLocked()) {
                // If the room is locked
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "popUp", null, null);
                notifyListeners(payload);
            } else {
                // If the room is not locked, allow the player to move
                Player.getInstance().setCurrentRoom(destinationRoom);
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
                notifyListeners(payload);
            }
        }
    }

    /**
     * Returns a list of available items in the current room.
     *
     * @param currentRoom The current room.
     * @return An ArrayList of available items in the current room.
     */
    @Override
    public ArrayList<Item> getAvailableItemsList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableItems();
    }

    /**
     * Returns a list of NPCs in the current room.
     *
     * @param currentRoom The current room.
     * @return An ArrayList of NPCs in the current room.
     */
    @Override
    public ArrayList<Npc> getNpcList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableNpcs();
    }

    /**
     * Unlocks a specific room.
     *
     * @param specificRoom The room to unlock.
     */
    @Override
    public void unlockRoom(Room specificRoom) {
        specificRoom.setIsLocked(false);
    }
}
