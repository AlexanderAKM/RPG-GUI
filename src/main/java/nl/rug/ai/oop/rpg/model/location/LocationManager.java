package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.inventory.ItemManager;
import nl.rug.ai.oop.rpg.model.inventory.items.StudentCard;
import nl.rug.ai.oop.rpg.model.players.Player;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class LocationManager implements LocationInterface, Serializable {
    private ArrayList<Room> map;
    private ArrayList<Room> availableRooms;

    private ItemManager manager;
    ArrayList<PropertyChangeListener> listeners;

    public LocationManager(ItemManager manager){
        this.manager = manager;
        map = new ArrayList<Room>();
        listeners = new ArrayList();
        availableRooms = new ArrayList<Room>();
        locationSetUp();
    }

    public Room getRoom(int index){
        return map.get(index);
    }

    public void locationSetUp() {
        //Item StudentCard = new StudentCard();
        ArrayList<Item> coverItems = manager.getItemsForRoom("Coffee", "Alcohol");
        ArrayList<Item> homeItems = manager.getItemsForRoom("Books", "Alcohol", "StudentCard","Money");
        ArrayList<Item> bbItems = manager.getItemsForRoom("Books","Pen");
        ArrayList<Item> canteenItems = manager.getItemsForRoom("Coffee");
        ArrayList<Item> outsideItems = manager.getItemsForRoom("Money", "Money");
        ArrayList<Item> examHallItems = manager.getItemsForRoom("Books");


        Room home = new RoomBuilder()
                .setName("Home")
                .setDescription("The room you live in.")
                .setNorth(1)
                .setEast(-1)
                .setSouth(-1)
                .setWest(-1)
                .setIsLocked(false)
                .addAvailableItems(homeItems)
                //.setRequiredItem()
                .build();

        Room outside = new RoomBuilder()
                .setName("Outside")
                .setDescription("The outdoors. Nothing more I can say..")
                .setNorth(3)
                .setEast(-1)
                .setSouth(0)
                .setWest(2)
                .setIsLocked(false)
                .addAvailableItems(outsideItems)
                //.setRequiredItem()
                .build();

        Room examHall = new RoomBuilder()
                .setName("Aletta Jacobs Hall")
                .setDescription("The outdoors. Nothing more I can say..")
                .setNorth(-1)
                .setEast(1)
                .setSouth(-1)
                .setWest(-1)
                .setIsLocked(false)
                .addAvailableItems(examHallItems)
                .setRequiredItem(new StudentCard())
                .build();

        Room bb = new RoomBuilder()
                .setName("Bernoulliborg")
                .setDescription("The room you live in.")
                .setNorth(5)
                .setEast(-1)
                .setSouth(1)
                .setWest(4)
                .setIsLocked(false)
                .addAvailableItems(bbItems)
                //.setRequiredItem()
                .build();

        Room canteen = new RoomBuilder()
                .setName("The BB canteen")
                .setDescription("The room you live in.")
                .setNorth(3)
                .setEast(-1)
                .setSouth(-1)
                .setWest(2)
                .setIsLocked(false)
                .addAvailableItems(canteenItems)
                //.setRequiredItem()
                .build();

        Room coverRoom = new RoomBuilder()
                .setName("Cover room")
                .setDescription("Study association room for AI students and CS students")
                .setNorth(-1)
                .setEast(-1)
                .setSouth(3)
                .setWest(-1)
                .setIsLocked(true)
                .addAvailableItems(coverItems)
                //.setRequiredItem()
                .build();



        Player.getInstance().setCurrentRoom(home);

        map.add(home); // the order of rooms
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);

    }
    /*
    @Override
    public void locationSetUp() {
        ArrayList<Item> coverItems = manager.getItemsForRoom("Coffee", "Alcohol");
        ArrayList<Item> homeItems = manager.getItemsForRoom("Books", "Alcohol", "Student-Card","Money");
        ArrayList<Item> bbItems = manager.getItemsForRoom("Books","Pen");
        ArrayList<Item> canteenItems = manager.getItemsForRoom("Coffee");
        ArrayList<Item> outsideItems = manager.getItemsForRoom("Money", "Money");
        ArrayList<Item> examHallItems = manager.getItemsForRoom("Books");


        Room coverRoom = new Room("Cover room", "Study association room for AI students and CS students",-1, -1, 3, -1, true, coverItems);
        Room canteen = new Room("The BB canteen", "A place you can have food. Sometimes good, sometimes bad and always expensive.",3, -1, 0, 2, false, canteenItems);
        Room bb = new Room("Bernoulliborg", "Building where students have lectures. Within the building, a cafeteria and an association room is located.",5, -1, 1, 4, false, bbItems);
        Room outside = new Room("Outside", "The outdoors. Nothing more I can say..",3, -1, 0, 2, false, outsideItems);
        Room examHall = new Room("Aletta Jacobs Hall", "Building where students take their course exams and have lectures. ",-1, 1, -1, -1, true, examHallItems);
        Room home = new Room("Home", "The room you live in.",1, -1, -1, -1, false, homeItems);

        map.add(home); // the order of rooms
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);

        Player.getInstance().setCurrentRoom(home);
    }

     */

    @Override
    public void addNpcs(String npcName, Npc npc, Room room) {
        room.getAvailableNpcs().add(npc);
    }

    @Override
    public void removeNpcs(String npcName, Npc npc, Room room) {
        room.getAvailableNpcs().remove(npc);
    }

    @Override
    public void addItemActions(Item x, Room room) {
        room.getAvailableItems().add(x);
    }

    @Override
    public void removeItemActions(Item x, Room room) {
        room.getAvailableItems().remove(x);
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
        //Room currentRoom = Player.getInstance().getCurrentRoom();
        Room destinationRoom = getRoom(chosenRoom);

        if (destinationRoom.getRequiredItem() != null) { // if room requires item
            if (Player.getInstance().getInventory().containsItem(destinationRoom.getRequiredItem())) { // if player has required item
                // The player has the required item, allow them to move
                Player.getInstance().setCurrentRoom(destinationRoom);
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
                notifyListeners(payload);
                System.out.println("You are now in " + Player.getInstance().getCurrentRoom().getRoomName());
                System.out.println("Description: " + Player.getInstance().getCurrentRoom().getRoomDescription());
            } else {
                // The player doesn't have the required item, they cannot move
                System.out.println("You need a " + destinationRoom.getRequiredItem().getName() + " to enter this room.");
            }
        } else { // if room not require item
            if(destinationRoom.getIsLocked()){ // if room locked
                System.out.println("You need to interact with someone to enter this room.");
            } else {
                Player.getInstance().setCurrentRoom(destinationRoom);
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "direction", null, Player.getInstance().getCurrentRoom().getRoomDescription());
                notifyListeners(payload);
                System.out.println("You are now in " + Player.getInstance().getCurrentRoom().getRoomName());
                System.out.println("Description: " + Player.getInstance().getCurrentRoom().getRoomDescription());
            }
        }
    }
/*
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
        System.out.println("You are now in " + Player.getInstance().getCurrentRoom().getRoomName());
        System.out.println("description : " + Player.getInstance().getCurrentRoom().getRoomDescription());
    }

 */

    @Override
    public ArrayList<Item> getAvailableItemsList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableItems();
    }

    @Override
    public ArrayList<Npc> getNpcList(Room currentRoom) {
        return Player.getInstance().getCurrentRoom().getAvailableNpcs();
    }

    @Override
    public void unlockRoom(Room specificRoom) {
        specificRoom.setIsLocked(false);
    }

}
