package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

/**
 * Builder class for constructing a Room object.
 */
public class RoomBuilder {
    private Room room;

    /**
     * Constructs a new RoomBuilder object.
     */
    public RoomBuilder() {
        room = new Room();
    }

    /**
     * Sets the name of the room.
     *
     * @param name The name of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setName(String name) {
        room.roomName = name;
        return this;
    }

    /**
     * Sets the description of the room.
     *
     * @param description The description of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setDescription(String description) {
        room.roomDescription = description;
        return this;
    }

    /**
     * Sets the north direction of the room.
     *
     * @param north The room index representing the north direction of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setNorth(int north) {
        room.n = north;
        return this;
    }

    /**
     * Sets the east direction of the room.
     *
     * @param east The room index representing the east direction of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setEast(int east) {
        room.e = east;
        return this;
    }

    /**
     * Sets the south direction of the room.
     *
     * @param south The room index representing the south direction of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setSouth(int south) {
        room.s = south;
        return this;
    }

    /**
     * Sets the west direction of the room.
     *
     * @param west The room index representing the west direction of the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setWest(int west) {
        room.w = west;
        return this;
    }

    /**
     * Sets the locked status of the room.
     *
     * @param isLocked The locked status of the room (true if locked, false if unlocked).
     * @return The RoomBuilder object.
     */
    public RoomBuilder setIsLocked(boolean isLocked) {
        room.isLocked = isLocked;
        return this;
    }

    /**
     * Adds the specified items to the list of available items in the room.
     *
     * @param items The list of items to be added.
     * @return The RoomBuilder object.
     */
    public RoomBuilder addAvailableItems(ArrayList<Item> items) {
        room.setItems(items);
        return this;
    }

    /**
     * Sets the required item to access the room.
     *
     * @param item The required item to access the room.
     * @return The RoomBuilder object.
     */
    public RoomBuilder setRequiredItem(Item item) {
        room.setRequiredItem(item);
        return this;
    }

    /**
     * Builds and returns the constructed Room object.
     *
     * @return The constructed Room object.
     */
    public Room build() {
        return room;
    }
}

