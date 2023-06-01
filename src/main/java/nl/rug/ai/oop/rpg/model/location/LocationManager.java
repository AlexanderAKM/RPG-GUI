package nl.rug.ai.oop.rpg.model.location;

import java.util.ArrayList;

public class LocationManager implements LocationInterface{
    public ArrayList<Room> map;
    public Room currentRoom;

    public LocationManager(){
        map = new ArrayList<Room>();
        locationSetUp();
    }

    // @Override
    public Room getPlayerRoom() {
        return currentRoom;
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
        Room examHall = new Room("Aletta Jacobs Hall", "Building where students take their course exams and have lectures. ",-1, 1, -1, -1, false);
        Room home = new Room("Home", "The room you live in.",1, -1, -1, -1, false);

        map.add(home);
        map.add(outside);
        map.add(examHall);
        map.add(bb);
        map.add(canteen);
        map.add(coverRoom);

        currentRoom = getRoom(0);
    }

}
