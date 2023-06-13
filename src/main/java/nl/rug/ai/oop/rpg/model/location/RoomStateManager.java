package nl.rug.ai.oop.rpg.model.location;

import java.io.*;
public class RoomStateManager {
    public static void saveRoomState(LocationManager locationManager, String fileName) {
        try {
            FileOutputStream fileOut = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(locationManager);
            out.close();
            fileOut.close();
            System.out.println("Room state saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving room state: " + e.getMessage());
        }
    }
    //RoomStateManager.saveRoomState(locationManager, "room_state.ser");
    //LocationManager locationManager = RoomStateManager.loadRoomState("room_state.ser"); to load
    public static LocationManager loadRoomState(String fileName) {
        try {
            FileInputStream fileIn = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            LocationManager locationManager = (LocationManager) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Room state loaded successfully.");
            return locationManager;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading room state: " + e.getMessage());
            return null;
        }
    }
}
