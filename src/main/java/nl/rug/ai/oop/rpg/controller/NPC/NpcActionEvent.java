package nl.rug.ai.oop.rpg.controller.NPC;

import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.awt.event.ActionEvent;

/**
 * Custom ActionEvent for NPC actions. Allows passing of vital information without casting.
 *
 * @author Kyriakos Hjikakou
 */
public class NpcActionEvent extends ActionEvent {
    private int wellBeingEffect;
    private int socialEffect;

    private final Npc sourceNpc;

    private final String eventName;

    private final String selectedText;

    /**
     * Constructs a new NpcActionEvent.
     *
     * @param source          The object that originated the event
     * @param id              An integer identifying the type of event
     * @param command         The command string associated with the event
     * @param selectedText    The selected text associated with the event
     * @param eventName       The name of the event
     * @param wellBeingEffect The well-being modifier of the event
     * @param socialEffect    The social modifier of the event
     * @param sourceNpc       The NPC that is associated with the event
     */
    public NpcActionEvent(Object source, int id, String command, String selectedText, String eventName, int wellBeingEffect, int socialEffect, Npc sourceNpc){
        super(source, id, command);
        this.wellBeingEffect = wellBeingEffect;
        this.socialEffect = socialEffect;
        this.sourceNpc = sourceNpc;
        this.eventName = eventName;
        this.selectedText = selectedText;
    }

    /**
     * Retrieves the NPC that triggered the event.
     *
     * @return The source NPC
     */
    public Npc getSourceNpc(){
        return sourceNpc;
    }

    /**
     * Retrieves the name of the event.
     *
     * @return The event name
     */
    public String getEventName(){
        return eventName;
    }

    /**
     * Retrieves the selected text associated with the event.
     *
     * @return The selected text
     */
    public String getSelectedText() {
        return selectedText;
    }
}
