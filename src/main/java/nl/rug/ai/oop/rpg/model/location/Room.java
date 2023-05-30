package nl.rug.ai.oop.rpg.model.location;

/**
 * A class that  defines locations within the game
 */
public class Room {
    private String roomName;
    private String roomDescription;
    private int n, e, s, w;

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
    public String getName(){
        return this.roomName;
    }

    /**
     * getter for the location description.
     * @return The description of the location.
     */
    public String getDescription(){
        return this.roomDescription;
    }

    /**
     * A constructor for a room (location).
     * @param name The name of the room.
     * @param description The description of the room.
     * @param n North direction.
     * @param e East direction.
     * @param s South direction.
     * @param w West direction
     */
    public void createRoom(String name, String description, int n, int e, int s, int w){

    }

}
