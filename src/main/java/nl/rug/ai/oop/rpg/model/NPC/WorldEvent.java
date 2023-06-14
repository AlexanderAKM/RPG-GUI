package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;

public class WorldEvent extends Events{
    public enum effectOnWorld {
        UNLOCK
    }
    String speechText;

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

    public void unlockRoom(){
        locationManager.unlockRoom(locationManager.getRoom(5));
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
