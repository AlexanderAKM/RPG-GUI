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
        ArrayList<Item> homeItems = manager.getItemsForRoom("Books", "Alcohol", "Student Card","Money");
        //ArrayList<Item> bbItems = manager.getItemsForRoom("Books");
        ArrayList<Item> canteenItems = manager.getItemsForRoom("Coffee");
        ArrayList<Item> outsideItems = manager.getItemsForRoom("Money", "Money");
        ArrayList<Item> examHallItems = manager.getItemsForRoom("Books");


        Room home = new RoomBuilder()
                .setName("Home")
                .setDescription("Welcome to your Dutch student home, worn and lived-in. The room is a chaotic mix of scattered textbooks, clothes strewn about, and a desk cluttered with study materials. Despite the disarray, it radiates a sense of youthful energy and camaraderie, a sanctuary amidst the bustling student life.")
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
                .setDescription("Step outside into the charming streets of Groningen, Netherlands. Despite the gloomy weather, the city exudes a unique beauty. Canals wind through historic buildings, their facades adorned with colorful bicycles. People huddle under umbrellas, but the spirit of this vibrant city remains undeterred.")
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
                .setDescription("Welcome to the Aletta Jacobs Lecture Hall, where students face their academic challenges head-on. The grand hall echoes with the collective buzz of eager learners. The vibrant orange interior evokes a mix of excitement and anxiety, as students prepare for exams and attend captivating lectures that shape their educational journey. To access this area, a student card is necessary so don't lose it.")
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
                .setDescription("Welcome to Bernoulliborg, a hub of knowledge and innovation for STEM students. Lecture halls and labs hum with scientific curiosity. The canteen disappoints with its lackluster food options, but it's the gathering place for students. Adjacent to it is the Cover association room, where friendships are forged and tech enthusiasts unite.")
                .setNorth(5)
                .setEast(-1)
                .setSouth(1)
                .setWest(4)
                .setIsLocked(false)
                //.addAvailableItems(bbItems)
                //.setRequiredItem()
                .build();

        Room canteen = new RoomBuilder()
                .setName("The BB canteen")
                .setDescription("Welcome to the Bernoulliborg Canteen, a bustling yet frustrating culinary oasis. Despite its limited and overpriced menu, hungry students flock here. The lunchtime rush at 1 PM transform the canteen into a lively gathering spot, filled with laughter and the aroma of mediocre food.")
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
                .setDescription("Welcome to the Cover Room, a haven for AI and CS students. Here, you'll find an awesome space filled with laughter and gaming sessions on the Switch. Indulge in tasty snacks and freshly brewed coffee from the machine. Keep an eye out for the Cover board members, always ready for a chat or a game challenge.")
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
