package nl.rug.ai.oop.rpg.model.location;

import java.util.ArrayList;

public class LocationManager implements LocationInterface{
    public ArrayList<Room> map;

    public LocationManager(){
        map = new ArrayList<Room>();
    }

}
