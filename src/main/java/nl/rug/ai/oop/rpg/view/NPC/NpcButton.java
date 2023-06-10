package nl.rug.ai.oop.rpg.view.NPC;
import javax.swing.*;

/**
 * InteractiveCard class is a button containing all the information about the card on screen.
 */
public class NpcButton extends JButton {
    private String interactionName;
    private String text;

    /**
     * The constructor for InteractiveCard button containing the picture.
     * @param icon The png file of the card picture.
     * @param suit The suit of the card.
     * @param rank The rank of the card.
     */
    public NpcButton(String text, String interactionName) {
        this.text = text;
        this.interactionName = interactionName;
        init(this.text, null);
    }
}
