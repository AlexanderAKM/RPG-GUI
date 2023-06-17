package nl.rug.ai.oop.rpg.model.npc.events.worldevents;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.events.Events;

import java.io.Serial;
import java.io.Serializable;

public class WorldEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    public enum effectOnWorld {
        UNLOCK
    }

    private int condition;

    private String successText;

    private String failText;

    private boolean hasFinishedEventChain;

    private LocationManager locationManager;

    public WorldEvent(String interactionName, Npc npcSource, effectOnWorld effect, String speechText, int condition, Events.EventType eventType, String successText, String failText, LocationManager locationManager){
        super(interactionName,npcSource, EventType.WORLD_EVENT, speechText);
        hasFinishedEventChain = false;
        this.condition = condition;
        this.successText = successText;
        this.failText = failText;
        this.locationManager = locationManager;
    }

    public String getSuccessText(){
        return successText;
    }

    public String getFailText(){
        return failText;
    }

    public void unlockRoom(int index){
        locationManager.unlockRoom(locationManager.getRoom(index));
    }

    public void setHasFinishedEventChain(boolean value){
        hasFinishedEventChain = value;
    }

    public boolean gethasFinishedEventChain(){
        return hasFinishedEventChain;
    }

    public void setCondition(int value){
        condition = value;
    }

    public int getCondition(){
        return condition;
    }

}
