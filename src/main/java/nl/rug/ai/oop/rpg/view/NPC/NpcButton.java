package nl.rug.ai.oop.rpg.view.NPC;
import nl.rug.ai.oop.rpg.model.npc.Npc;

import javax.swing.*;

/**
 * InteractiveCard class is a button containing all the information about the card on screen.
 */
public class NpcButton extends JButton {
    private String interactionName = "";

    private Npc interactingNpc;
    private String text = "";

    public NpcButton(String text, String interactionName, Npc interactingNpc) {
        this.text = text;
        this.interactionName = interactionName;
        this.interactingNpc = interactingNpc;

        init(this.text, null);
    }



    public String getText(){
        return text;
    }
    public Npc getNpc(){
        return interactingNpc;
    }

    public String getInteractionName(){
        return interactionName;
    }
}
