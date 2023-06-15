package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.awt.event.ActionEvent;

public class NpcActionEvent extends ActionEvent {
    private int wellBeingEffect;
    private int socialEffect;

    private NpcButton sourceNpcButton;

    String eventName;

    public NpcActionEvent(Object source, int id, String command, String eventName, int wellBeingEffect, int socialEffect, NpcButton sourceNpcButton){
        super(source, id, command);
        this.wellBeingEffect = wellBeingEffect;
        this.socialEffect = socialEffect;
        this.sourceNpcButton = sourceNpcButton;
        this.eventName = eventName;
    }

    public NpcButton getSourceNpcButton(){
        return sourceNpcButton;
    }

    public String getEventName(){
        return eventName;
    }

}
