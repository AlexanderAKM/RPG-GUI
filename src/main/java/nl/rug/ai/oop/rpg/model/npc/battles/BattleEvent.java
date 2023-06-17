package nl.rug.ai.oop.rpg.model.npc.battles;

import nl.rug.ai.oop.rpg.model.npc.Events;
import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a battle event that occurs during the game.
 * Each battle event includes a specific battle question object.
 * The battle question object contains the text content that will populate, different option buttons to the player.
 * Is based on a generic Event type, that gives the event functionality.
 *
 * @author Kyriakos Hjikakou
 */
public class BattleEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private final BattleQuestions battleQuestions;

    /**
     * Constructs a new BattleEvent object.
     *
     * @param interactionName the name of the interaction.
     * @param npcSource the NPC source of the event.
     * @param speechText the textBox text that will be outputted.
     * @param battleQuestions the battle questions associated with this event.
     */
    public BattleEvent(String interactionName, Npc npcSource, String speechText, BattleQuestions battleQuestions){
        super(interactionName,npcSource, EventType.BATTLE, speechText);
        this.battleQuestions = battleQuestions;
    }


    /**
     * Returns the BattleQuestions instance associated with this event.
     *
     * @return returns BattleQuestions.
     */
    public BattleQuestions getBattleQuestions() {
        return battleQuestions;
    }
}
