package nl.rug.ai.oop.rpg.model.location;

import java.util.ArrayList;

public class Main {

    //ArrayList<Room> roomMap = new ArrayList<Room>();


    public void main(){
        LocationManager manager = new LocationManager();
        Room coverRoom = new Room("Cover room", "Study association room for AI students and CS students",-1, -1, 4, -1)
        Room BB = new Room("Bernoulliborg", "Building where students have lectures. Within the building, a cafeteria and an association room is located.",-1, -1, 4, -1);
        manager.map.add(coverRoom);
    }
}
