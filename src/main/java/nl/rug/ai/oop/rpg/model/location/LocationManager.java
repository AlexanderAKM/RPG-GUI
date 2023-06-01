package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.inventory.Item;

import java.util.ArrayList;

public class LocationManager implements LocationInterface{
    public ArrayList<Room> map;
    public ArrayList<Room> availableRooms;
    public Room currentRoom; // gonna be in player class -> player.currentRoom

    public LocationManager(){
        map = new ArrayList<Room>();
        locationSetUp();
    }

    // @Override
    public Room getPlayerRoom() {
        return currentRoom; // player.currentRoom
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

        currentRoom = getRoom(0);
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
        if (player.currentRoom.n >= 0){
            availableRooms.add(getRoom(player.currentRoom.n));
        }
        if (player.currentRoom.e >= 0){
            availableRooms.add(getRoom(player.currentRoom.e));
        }
        if (player.currentRoom.s >= 0){
            availableRooms.add(getRoom(player.currentRoom.s));
        }
        if (player.currentRoom.w >= 0){
            availableRooms.add(getRoom(player.currentRoom.w));
        }
        return availableRooms;
    }

    @Override
    public void movePlayer(Room chosenRoom) {
        player.currentRoom = chosenRoom;
        System.out.println("You are now in " + player.currentRoom.roomName);
        System.out.println("You are now in " + player.currentRoom.roomDescription);
    }

    @Override
    public ArrayList<Item> getAvailableItems(Room currentRoom) {
        return currentRoom.availableItems;
    }

    @Override
    public ArrayList<NPC> getAvailableNPCs(Room currentRoom) {
        return currentRoom.availableNPCs;
    }

}
