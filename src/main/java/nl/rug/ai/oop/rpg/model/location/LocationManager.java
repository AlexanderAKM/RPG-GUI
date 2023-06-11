package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

public class LocationManager implements LocationInterface{
    private ArrayList<Room> map;
    private ArrayList<Room> availableRooms;
    ArrayList<PropertyChangeListener> listeners;
    //public Player player;

    public LocationManager(){
        map = new ArrayList<Room>();
        listeners = new ArrayList();
        availableRooms = new ArrayList<Room>();
        locationSetUp();
        //player = currentPlayer;
    }

    public Room getRoom(int index){
        return map.get(index);
    }

    @Override
    public void locationSetUp() {
        Room coverRoom = new Room("Cover room", "Study association room for AI students and CS students",-1, -1, 3, -1, false);
        Room canteen = new Room("The BB canteen", "A place you can have food. Sometimes good, sometimes bad and always expensive.",3, -1, 0, 2, false);
        Room bb = new Room("Bernoulliborg", "Building where students have lectures. Within the building, a cafeteria and an association room is located.",5, -1, 1, 4, false);
        Room outside = new Room("Outside", "The outdoors. Nothing more I can say..",3, -1, 0, 2, false);
        Room examHall = new Room("Aletta Jacobs Hall", "Building where students take their course exams and have lectures. ",-1, 1, -1, -1, true);
        Room home = new Room("Home", "The room you live in.",1, -1, -1, -1, false);

        map.add(home); // the order of rooms
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);

        Player.getInstance().setCurrentRoom(home);
    }

    @Override
    public void addNpcs(String npcName, Npc npc, Room roomName) {
        roomName.getAvailableNpcs().add(npc);
    }

    @Override
    public void removeNpcs(String npcName, Npc npc, Room roomName) {
        roomName.getAvailableNpcs().remove(npc);
    }

    @Override
    public void addItemActions(Item x, Room roomName) {
        roomName.getAvailableItems().add(x);
    }

    @Override
    public void removeItemActions(Item x, Room roomName) {
        roomName.getAvailableItems().remove(x);
    }

    @Override
    public ArrayList<Room> roomsAvailable(Room currentRoom) {
        availableRooms.clear();
        if (Player.getInstance().getCurrentRoom().getNorth() >= 0){
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getNorth()));
        }
        if (Player.getInstance().getCurrentRoom().getEast() >= 0){
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getEast()));
        }
        if (Player.getInstance().getCurrentRoom().getSouth() >= 0){
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getSouth()));
        }
        if (Player.getInstance().getCurrentRoom().getWest() >= 0){
            availableRooms.add(getRoom(Player.getInstance().getCurrentRoom().getWest()));
        }
        return availableRooms;
    }

    private void notifyListeners(  PropertyChangeEvent payload) {
        Iterator<PropertyChangeListener> allListeners = listeners.iterator();

        while (allListeners.hasNext()) {
            allListeners.next().propertyChange(payload);
        }
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    @Override
    public void movePlayer(int chosenRoom) {
        //int nextRoom = Player.getInstance().getCurrentRoom().getDirection(direction);
        if (getRoom(chosenRoom).getIsLocked()){
            System.out.println("Cannot go there");
        } else {
            Player.getInstance().setCurrentRoom(getRoom(chosenRoom));
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
            notifyListeners(payload);
        }
        //Player.getInstance().setCurrentRoom(chosenRoom);
        System.out.println("You are now in " + Player.getInstance().getCurrentRoom().getRoomName());
        System.out.println("description : " + Player.getInstance().getCurrentRoom().getRoomDescription());
    }
    /*
    public void movePlayer(String direction) {
        int nextRoom = Player.getInstance().getCurrentRoom().getDirection(direction);
        if (nextRoom == -1 || getRoom(nextRoom).getIsLocked()){
            System.out.println("Cannot go there");
        } else {
            Player.getInstance().setCurrentRoom(getRoom(nextRoom));
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
            notifyListeners(payload);
        }
        //Player.getInstance().setCurrentRoom(chosenRoom);
        System.out.println("You are now in " + Player.getInstance().getCurrentRoom().getRoomName());
        System.out.println("description : " + Player.getInstance().getCurrentRoom().getRoomDescription());
    } */

    @Override
    public ArrayList<Item> getAvailableItemsList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableItems();
    }

    @Override
    public ArrayList<Npc> getNpcList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableNpcs();
    }

}
