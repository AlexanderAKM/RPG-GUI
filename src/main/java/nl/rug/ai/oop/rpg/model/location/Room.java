package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that represents a room (location) within the game.
 *
 * @author Victoria Polaka
 */
public class Room implements Serializable {
    String roomName;
    String roomDescription;
    int n;
    int e;
    int s;
    int w;
    private ArrayList<Item> availableItems;
    private ArrayList<Npc> availableNpcs;
    boolean isLocked;
    Item requiredItem;

    /**
     * Constructs a new Room object.
     * Initializes the available NPCs and items lists.
     */
    Room() {
        availableNpcs = new ArrayList<Npc>();
        availableItems = new ArrayList<Item>();
    }

    /**
     * setter for the location/room name.
     * @param name Locations name
     */
    public void setName(String name){
        this.roomName = name;
    }

    /**
     * setter for the location/room description.
     * @param description The description of the location
     */
    public void setDescription(String description){
        this.roomDescription = description;
    }

    /**
     * getter for the locations/room name.
     * @return The name of the location.
     */
    public String getRoomName(){
        return this.roomName;
    }

    /**
     * getter for the location/room description.
     * @return The description of the location.
     */
    public String getRoomDescription(){
        return this.roomDescription;
    }


    /**
     * Sets the room index representing the north direction of the room.
     *
     * @param north The room index representing the north direction.
     */
    public void setNorth(int north) {
        this.n = north;
    }

    /**
     * Sets the room index representing the east direction of the room.
     *
     * @param east The room index representing the east direction.
     */
    public void setEast(int east) {
        this.e = east;
    }

    /**
     * Sets the room index representing the south direction of the room.
     *
     * @param south The room index representing the south direction.
     */
    public void setSouth(int south) {
        this.s = south;
    }

    /**
     * Sets the room index representing the west direction of the room.
     *
     * @param west The room index representing the west direction.
     */
    public void setWest(int west) {
        this.w = west;
    }

    /**
     * Returns the room index representing the north direction of the room.
     *
     * @return The room index representing the north direction.
     */
    public int getNorth() {
        return this.n;
    }

    /**
     * Returns the room index representing the east direction of the room.
     *
     * @return The room index representing the east direction.
     */
    public int getEast() {
        return this.e;
    }

    /**
     * Returns the room index representing the south direction of the room.
     *
     * @return The room index representing the south direction.
     */
    public int getSouth() {
        return this.s;
    }

    /**
     * Returns the room index representing the west direction of the room.
     *
     * @return The room index representing the west direction.
     */
    public int getWest() {
        return this.w;
    }
    public int getDirection(String direction){
        int answer = 0;
        switch(direction){
            case "n" :
                answer = getNorth();
                break;
            case "e" :
                answer = getEast();
                break;
            case "s" :
                answer = getSouth();
                break;
            case "w" :
                answer = getWest();
                break;
        }
        return answer;
    }

    /**
     * Sets the locked status of the room.
     *
     * @param isLocked The locked status of the room (true if locked, false if unlocked).
     */
    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Returns the locked status of the room.
     *
     * @return The locked status of the room (true if locked, false if unlocked).
     */
    public boolean getIsLocked() {
        return isLocked;
    }

    /**
     * Returns the list of available items in the room.
     *
     * @return The list of available items in the room.
     */
    public ArrayList<Item> getAvailableItems() {
        return availableItems;
    }

    /**
     * Returns the list of available NPCs in the room.
     *
     * @return The list of available NPCs in the room.
     */
    public ArrayList<Npc> getAvailableNpcs() {
        return availableNpcs;
    }

    /**
     * Returns the required item to access the room.
     *
     * @return The required item to access the room.
     */
    public Item getRequiredItem() {
        return requiredItem;
    }

    /**
     * Sets the required item to access the room.
     *
     * @param item The required item to access the room.
     */
    public void setRequiredItem(Item item) {
        this.requiredItem = item;
    }

    /**
     * Sets the list of items available in the room.
     *
     * @param items The list of items available in the room.
     */
    public void setItems(ArrayList<Item> items) {
        availableItems.addAll(items);
    }
}
