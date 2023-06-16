package nl.rug.ai.oop.rpg.model.npc;

import java.util.ArrayList;

public class NpcPropertyEvent {

    private Npc.EventType eventType;
    private String eventName;
    private String text;
    private ArrayList<String> dialogueText;
    private Npc sourceNpc;
    private int cost;
    private String toolTipText;

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

    public void setToolTipText(String toolTipText){
        this.toolTipText = toolTipText;
    }

    public String getToolTipText(){
        return toolTipText;
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

