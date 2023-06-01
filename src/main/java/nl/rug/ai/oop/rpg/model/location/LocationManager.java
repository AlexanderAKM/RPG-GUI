package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;


import java.util.ArrayList;

public class LocationManager implements LocationInterface{
    public ArrayList<Room> map;
    public ArrayList<Room> availableRooms;

    public LocationManager(){
        map = new ArrayList<Room>();
        availableRooms = new ArrayList<Room>();
        locationSetUp();
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

        map.add(home);
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);
    }

    @Override
    public void addNpcs(NPC npcName, Room roomName) {

    }

    @Override
    public void removeNpcs(NPC npcName, Room roomName) {

    }

    @Override
    public void addItemActions(Item x, Room roomName) {

    }

    @Override
    public void removeItemActions(Item x, Room roomName) {

    }

    @Override
    public ArrayList<Room> roomsAvailable(Room currentRoom) {
        availableRooms.clear();
        if (Player.getCurrentRoom().n >= 0){
            availableRooms.add(getRoom(Player.getCurrentRoom().n);
        }
        if (Player.getCurrentRoom().e >= 0){
            availableRooms.add(getRoom(Player.getCurrentRoom().e));
        }
        if (Player.getCurrentRoom().s >= 0){
            availableRooms.add(getRoom(Player.getCurrentRoom().s));
        }
        if (Player.getCurrentRoom().w >= 0){
            availableRooms.add(getRoom(Player.getCurrentRoom().w));
        }
        return availableRooms;
    }

    @Override
    public void movePlayer(Room chosenRoom) {
        Player.setCurrentRoom = chosenRoom;
        System.out.println("You are now in " + Player.getCurrentRoom().roomName);
        System.out.println("You are now in " + Player.getCurrentRoom().roomDescription);
    }

    @Override
    public ArrayList<Item> getAvailableItems(Room currentRoom) {
        return getCurrentRoom().availableItems;
    }

    @Override
    public ArrayList<NPC> getAvailableNPCs(Room currentRoom) {
        return getCurrentRoom().availableNPCs;
    }

}
