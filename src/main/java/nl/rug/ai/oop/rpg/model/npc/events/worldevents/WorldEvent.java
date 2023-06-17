package nl.rug.ai.oop.rpg.model.npc.events.worldevents;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.events.Events;

import java.io.Serial;
import java.io.Serializable;

/**
 * This class represents a world event in the RPG game.
 * It extends the Events class and implements the Serializable interface for persistence.

 *
 * @author Kyriakos Hjikakou
 */
public class WorldEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public enum effectOnWorld {
        UNLOCK
    }

    private final int cost;

    private final String successText;

    private final String failText;

    private boolean hasFinishedEventChain;

    private final LocationManager locationManager;

    /**
     * Constructs a world event with the specified details.
     *
     * @param interactionName the name of the event
     * @param npcSource the NPC source that is associated with the event
     * @param effect the effect on the world
     * @param speechText the speech text for the event
     * @param cost the condition for the event
     * @param eventType the type of the event
     * @param successText the success text for the event
     * @param failText the fail text for the event
     * @param locationManager the location manager
     */
    public WorldEvent(String interactionName, Npc npcSource, effectOnWorld effect, String speechText, int cost, Events.EventType eventType, String successText, String failText, LocationManager locationManager){
        super(interactionName,npcSource, EventType.WORLD_EVENT, speechText);
        hasFinishedEventChain = false;
        this.cost = cost;
        this.successText = successText;
        this.failText = failText;
        this.locationManager = locationManager;
    }

    /**
     * Returns the success text of the event.
     *
     * @return the success text
     */
    public String getSuccessText(){
        return successText;
    }

    /**
     * Returns the fail text of the event.
     *
     * @return the fail text
     */
    public String getFailText(){
        return failText;
    }

    /**
     * Unlocks the room at the specified index.
     *
     * @param index the index of the room
     */
    public void unlockRoom(int index){
        locationManager.unlockRoom(locationManager.getRoom(index));
    }

    /**
     * Sets whether the event chain has finished.
     *
     * @param value the new value
     */
    public void setHasFinishedEventChain(boolean value){
        hasFinishedEventChain = value;
    }

    /**
     * Returns the condition of the event.
     *
     * @return the condition
     */
    public int getCost(){
        return cost;
    }

}
