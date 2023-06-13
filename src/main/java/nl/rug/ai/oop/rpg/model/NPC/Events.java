package nl.rug.ai.oop.rpg.model.NPC;

public abstract class Events {
    public enum EventType {
        BATTLE,
        SHOP,
        SOCIALISE,
        INTRODUCTION,
        WORLD_EVENT
    }
    private String interactionName;
    private Npc npcSource;

    private EventType eventType;

    private String speechText;


    public Events(String eventName, Npc npcSource, EventType eventType, String speechText){
        this.interactionName = eventName;
        this.npcSource = npcSource;
        this.eventType = eventType;
        this.speechText = speechText;
        //this.chosenFunction = function;
        //this.playerSource = playerSource;
    }

    public Npc getNpcSource(){
        return npcSource;
    }

    public String getSpeechText(){
        return  speechText;
    }

    public String getName(){
        return interactionName;
    }

    public EventType getEventType(){
        return eventType;
    }

}
