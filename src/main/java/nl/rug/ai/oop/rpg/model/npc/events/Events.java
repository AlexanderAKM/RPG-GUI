package nl.rug.ai.oop.rpg.model.npc.events;

import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.io.Serializable;

/**
 * Generic Event class to create Event objects.
 *
 * @author Kyriakos Hjikakou
 */
public abstract class Events implements Serializable {
    /**
     * Enum of the possible event types.
     */
    public enum EventType {
        BATTLE,
        INTRODUCTION,
        WORLD_EVENT
    }
    private final String interactionName;
    private final Npc npcSource;
    private final EventType eventType;
    private final String speechText;

    /**
     * Creates a generic Event object
     *
     * @param eventName the name of the event
     * @param npcSource the NPC source of the event
     * @param eventType the type of the event
     * @param speechText the speech text for the event, which is the most basic text shown to a player
     */
    public Events(String eventName, Npc npcSource, EventType eventType, String speechText){
        this.interactionName = eventName;
        this.npcSource = npcSource;
        this.eventType = eventType;
        this.speechText = speechText;
    }

    /**
     * Returns the associated NPC of this event
     *
     * @return the NPC source
     */
    public Npc getNpcSource(){
        return npcSource;
    }

    /**
     * Returns the speech text of the event.
     *
     * @return the speech text
     */
    public String getSpeechText(){
        return  speechText;
    }

    /**
     * Returns the name of the event.
     *
     * @return the event name
     */
    public String getName(){
        return interactionName;
    }

    /**
     * Returns the type of the event.
     *
     * @return the event type
     */
    public EventType getEventType(){
        return eventType;
    }

}
