package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A class that  defines locations within the game
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

    Room() {
        availableNpcs = new ArrayList<Npc>();
        availableItems = new ArrayList<Item>();
    }

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
     * sets the status of the room.
     * @param x Whether its locked or unlocked.
     */
    public void setIsLocked(boolean x){
        this.isLocked = x;
    }

    public boolean getIsLocked(){
        return isLocked;
    }

    public ArrayList<Item> getAvailableItems(){
        return availableItems;
    }

    public ArrayList<Npc> getAvailableNpcs(){
        return availableNpcs;
    }

    public Item getRequiredItem() {
        return requiredItem;
    }

    public void setRequiredItem(Item item){
        this.requiredItem = item;
    }

    public void setItems(ArrayList<Item> items) {
        availableItems.addAll(items);
    }
}
