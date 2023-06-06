package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

/**
 * A class that  defines locations within the game
 */
public class Room {
    private String roomName;
    private String roomDescription;
    private int n, e, s, w;
    private ArrayList<Item> availableItems;
    private ArrayList<Npc> availableNPC;
    private boolean isLocked;

    /**
     * setter for the location name.
     * @param name Locations name.
     */
    public void setName(String name){
        this.roomName = name;
    }

    /**
     * setter for the location description.
     * @param description The description of the location.
     */
    public void setDescription(String description){
        this.roomDescription = description;
    }

    /**
     * getter for the locations name.
     * @return The name of the location.
     */
    public String getRoomName(){
        return this.roomName;
    }

    /**
     * getter for the location description.
     * @return The description of the location.
     */
    public String getRoomDescription(){
        return this.roomDescription;
    }


    public void setNorth(int north){
        this.n = north;
    }

    public void setEast(int east){
        this.e = east;
    }

    public void setSouth(int south){
        this.s = south;
    }

    public void setWest(int west){
        this.w = west;
    }

    public int getNorth(){
        return this.n;
    }

    public int getEast(){
        return this.e;
    }

    public int getSouth(){
        return this.s;
    }

    public int getWest(){
        return this.w;
    }

    /**
     * sets the status of the room.
     * @param x Whether its locked or unlocked.
     */
    public void setIsLocked(boolean x){
        this.isLocked = x;
    }

    /**
     * A constructor for a room (location).
     * @param name The name of the room.
     * @param description The description of the room.
     * @param n North direction.
     * @param e East direction.
     * @param s South direction.
     * @param w West direction.
     */
    public Room(String name, String description, int n, int e, int s, int w, boolean status){
        this.setName(name);
        this.setDescription(description);
        this.setNorth(n);
        this.setEast(e);
        this.setSouth(s);
        this.setWest(w);
        this.setIsLocked(status);
    }

}
