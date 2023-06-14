package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.NPC.Npc;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.awt.event.ActionEvent;

public class NpcActionEvent extends ActionEvent {
    private int wellBeingEffect;
    private int socialEffect;

    private NpcButton sourceNpcButton;
    public NpcActionEvent(Object source, int id, String command, int wellBeingEffect, int socialEffect, NpcButton sourceNpcButton){
        super(source, id, command);
        this.wellBeingEffect = wellBeingEffect;
        this.socialEffect = socialEffect;
        this.sourceNpcButton = sourceNpcButton;
    }

    public NpcButton getSourceNpcButton(){
        return sourceNpcButton;
    }


}
