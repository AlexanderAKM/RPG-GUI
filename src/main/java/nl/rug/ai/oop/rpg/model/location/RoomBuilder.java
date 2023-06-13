package nl.rug.ai.oop.rpg.model.location;


import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

// Builder class
public class RoomBuilder {
    private Room room;

    public RoomBuilder() {
        room = new Room();
    }

    public RoomBuilder setName(String name) {
        room.roomName = name;
        return this;
    }

    public RoomBuilder setDescription(String description) {
        room.roomDescription = description;
        return this;
    }

    public RoomBuilder setNorth(int north) {
        room.n = north;
        return this;
    }

    public RoomBuilder setEast(int east) {
        room.e = east;
        return this;
    }

    public RoomBuilder setSouth(int south) {
        room.s = south;
        return this;
    }

    public RoomBuilder setWest(int west) {
        room.w = west;
        return this;
    }

    public RoomBuilder setIsLocked(boolean isLocked) {
        room.isLocked = isLocked;
        return this;
    }

    public RoomBuilder addAvailableItems(ArrayList<Item> items) {
        room.setItems(items);
        return this;
    }
    /*
    public RoomBuilder addAvailableItem(Item item) {
        room.getAvailableItems().add(item);
        return this;
    }

     */

    public RoomBuilder addAvailableNpc(Npc npc) {
        room.getAvailableNpcs().add(npc);
        return this;
    }

    public RoomBuilder setRequiredItem(Item item) {
        room.requiredItem = item;
        return this;
    }

    public Room build() {
        return room;
    }
}
