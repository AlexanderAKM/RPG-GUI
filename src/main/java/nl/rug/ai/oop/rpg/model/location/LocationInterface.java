package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

public interface LocationInterface {
    /**
     * Gets the room the player is currently in.
     * @return The current room the player is in.
     */
    public Room getPlayerRoom();

    /**
     * Method that sets up the location feature; rooms, starting room and map.
     */
    public void locationSetUp();

    /**
     * Adds NPCs to the list of NPCs in a specific room.
     * @param npcName The name of the NPC.
     * @param roomName The room in which the NPC should be in.
     */
    public void addNpcs(NPC npcName, Room roomName);

    /**
     * Removes a specific NPC from a room.
     * @param npcName The name of the NPC.
     * @param roomName The room in which the NPC remains.
     */
    public void removeNpcs(NPC npcName, Room roomName);

    /**
     * Adds an item to the room.
     * @param x The item specified.
     * @param roomName The name of the room the item should be in.
     */
    public void addItemActions(Item x, Room roomName);

    /**
     * removes a particular item from a room.
     * @param x The item to be removed.
     * @param roomName The name of the room the item is in.
     */
    public void removeItemActions(Item x, Room roomName);

    /**
     * Returns all the available rooms the player can access/ go to.
     * @param currentRoom The room the player resides.
     * @return An arrayList of all the rooms you can access from the current room
     */
    public ArrayList<Room> roomsAvailable(Room currentRoom);

    /**
     * Moves the player to the chosen room.
     * @param chosenRoom The players chosen room.
     */
    public void movePlayer(Room chosenRoom);

    /**
     * Returns all the items you can interact with that are present in the room.
     * @param currentRoom The room the player is currently in.
     * @return An ArrayList of all the items in the room.
     */
    public ArrayList<Item> getAvailableItems(Room currentRoom);

    /**
     * Returns all the NPCs you can interact with in the current room.
     * @param currentRoom The room the player is in.
     * @return An ArrayList of all the available NPCs.
     */
    public ArrayList<NPC> getAvailableNPCs(Room currentRoom);

}
