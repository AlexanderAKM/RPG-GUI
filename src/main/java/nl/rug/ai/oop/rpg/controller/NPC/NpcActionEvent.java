package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.awt.event.ActionEvent;

public class NpcActionEvent extends ActionEvent {
    private int wellBeingEffect;
    private int socialEffect;

    private Npc sourceNpc;

    private String eventName;

    private String selectedText;

    public NpcActionEvent(Object source, int id, String command, String selectedText, String eventName, int wellBeingEffect, int socialEffect, Npc sourceNpc){
        super(source, id, command);
        this.wellBeingEffect = wellBeingEffect;
        this.socialEffect = socialEffect;
        this.sourceNpc = sourceNpc;
        this.eventName = eventName;
        this.selectedText = selectedText;
    }

    public Npc getSourceNpc(){
        return sourceNpc;
    }

    public String getEventName(){
        return eventName;
    }

    public String getSelectedText() {
        return selectedText;
    }
}
