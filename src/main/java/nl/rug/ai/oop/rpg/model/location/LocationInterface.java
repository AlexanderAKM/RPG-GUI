package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

/**
 * The LocationInterface defines the behavior and functionality of a location in the game.
 * This interface provides methods for managing NPCs, items, rooms, and player movement.
 *
 * @author Victoria Polaka
 */
public interface LocationInterface {

    /**
     * Sets up the location by creating rooms, adding items to the room, defining the starting room, and generating the map.
     */
    public void locationSetUp();

    /**
     * Adds an NPC to a specific room.
     *
     * @param npcName  The name of the NPC.
     * @param npc      The NPC object to add.
     * @param roomName The room where the NPC should be added.
     */
    public void addNpcs(String npcName, Npc npc, Room roomName);

    /**
     * Removes a specific NPC from a room.
     *
     * @param npcName  The name of the NPC to remove.
     * @param npc      The NPC object to remove.
     * @param roomName The room from which the NPC should be removed.
     */
    public void removeNpcs(String npcName, Npc npc, Room roomName);

    /**
     * Adds an item to a room.
     *
     * @param item     The item to add.
     * @param roomName The name of the room where the item should be added.
     */
    public void addItemActions(Item item, Room roomName);

    /**
     * Removes a particular item from a room.
     *
     * @param item     The item to remove.
     * @param roomName The name of the room from which the item should be removed.
     */
    public void removeItemActions(Item item, Room roomName);

    /**
     * Returns a list of available rooms that can be accessed from the current room.
     *
     * @param currentRoom The current room the player is in.
     * @return An ArrayList of all the rooms accessible from the current room.
     */
    public ArrayList<Room> roomsAvailable(Room currentRoom);

    /**
     * Moves the player to the specified room in the given direction.
     *
     * @param direction The compass direction to move towards.
     */
    public void movePlayer(int direction);

    /**
     * Returns a list of available items in the current room that can be interacted with.
     *
     * @param currentRoom The current room the player is in.
     * @return An ArrayList of all the items in the room that can be interacted with.
     */
    public ArrayList<Item> getAvailableItemsList(Room currentRoom);

    /**
     * Returns a list of available NPCs in the current room that can be interacted with.
     *
     * @param currentRoom The current room the player is in.
     * @return An ArrayList of all the available NPCs in the room.
     */
    public ArrayList<Npc> getNpcList(Room currentRoom);

    /**
     * Unlocks a specific room.
     *
     * @param specificRoom The room to unlock.
     */
    public void unlockRoom(Room specificRoom);
}