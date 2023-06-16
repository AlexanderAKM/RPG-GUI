package nl.rug.ai.oop.rpg.model.NPC;

import jdk.jfr.Event;
import nl.rug.ai.oop.rpg.controller.NPC.NpcActionEvent;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

public class NpcPropertyEvent {

    private Npc.EventType eventType;
    private String eventName;
    private String text;
    private ArrayList<String> dialogueText;

    private Npc sourceNpc;

    private int cost;

    public NpcPropertyEvent(Npc.EventType eventType, String eventName, String text, ArrayList<String> dialogueText, int cost, Npc sourceNpc) {
        this.eventType = eventType;
        this.eventName = eventName;
        this.text = text;
        this.dialogueText = dialogueText;
        this.sourceNpc = sourceNpc;
        this.cost = cost;
    }

    public Npc.EventType getEventType(){
        return eventType;
    }

    public String getEventName() {
        return eventName;
    }

    public String getText() {
        return text;
    }

    public ArrayList<String> getDialogueText() {
        return dialogueText;
    }

    public Npc getSourceNpc() {
        return sourceNpc;
    }

    public int getCost(){
        return cost;
    }
}

