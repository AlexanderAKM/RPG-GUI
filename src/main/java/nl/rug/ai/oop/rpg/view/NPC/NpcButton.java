package nl.rug.ai.oop.rpg.view.NPC;
import nl.rug.ai.oop.rpg.model.NPC.Npc;

import javax.swing.*;

/**
 * InteractiveCard class is a button containing all the information about the card on screen.
 */
public class NpcButton extends JButton {
    private String interactionName = "";

    private Npc interactingNpc;
    private String text = "";

    /**
     * The constructor for InteractiveCard button containing the picture.
     * @param icon The png file of the card picture.
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
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
