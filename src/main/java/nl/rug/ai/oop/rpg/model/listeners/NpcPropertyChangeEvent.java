package nl.rug.ai.oop.rpg.model.listeners;

import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.util.ArrayList;

/**
 * Represents an NPC property change event in the RPG game.
 */
public class NpcPropertyChangeEvent {
    private Npc.EventType eventType;
    private ArrayList<String> dialogueText;
    private String eventName;
    private String text;
    private Npc sourceNpc;
    private int cost;
    private String toolTipText;

    /**
     * Constructs an NPC property change event with the following attributes about the event.
     *
     * @param eventType the event type
     * @param eventName the event name
     * @param text the text for the event
     * @param dialogueText the dialogue text for the event
     * @param cost the cost of the event
     * @param sourceNpc the source NPC of the event
     */
    public NpcPropertyChangeEvent(Npc.EventType eventType, String eventName, String text, ArrayList<String> dialogueText, int cost, Npc sourceNpc) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.text = text;
        this.dialogueText = dialogueText;
        this.sourceNpc = sourceNpc;
        this.cost = cost;
    }

    /**
     * Returns the event type.
     *
     * @return the event type
     */
    public Npc.EventType getEventType(){
        return eventType;
    }

    /**
     * Returns the event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Returns the text of the event.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the tooltip text of the event.
     *
     * @param toolTipText the new tooltip text
     */
    public void setToolTipText(String toolTipText){
        this.toolTipText = toolTipText;
    }

    /**
     * Returns the tooltip text of the event.
     *
     * @return the tooltip text
     */
    public String getToolTipText(){
        return toolTipText;
    }

    /**
     * Returns the dialogue text of the event.
     *
     * @return the dialogue text
     */
    public ArrayList<String> getDialogueText() {
        return dialogueText;
    }

    /**
     * Returns the source NPC of the event. The NPC that relates to this event.
     *
     * @return the source NPC
     */
    public Npc getSourceNpc() {
        return sourceNpc;
    }

    /**
     * Returns the cost of the event.
     *
     * @return the cost
     */
    public int getCost(){
        return cost;
    }
}

