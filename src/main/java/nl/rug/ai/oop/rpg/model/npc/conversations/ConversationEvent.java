package nl.rug.ai.oop.rpg.model.npc.conversations;

import nl.rug.ai.oop.rpg.model.npc.Events;
import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a conversation event in the game.
 * This class models interactive dialogues as events and is used to manage the possible conversation options
 * and hence flow with the NPC.
 *
 * @author Kyriakos Hjikakou
 */
public class ConversationEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private String currentKey;
    private final ConversationChain conversationChain;

    /**
     * Constructs a new ConversationEvent Object.
     *
     * @param interactionName the name of the interaction.
     * @param npcSource the NPC initiating the conversation.
     * @param speechText the text of the conversation.
     * @param conversationChain the conversation chain for the event.
     */
    public ConversationEvent(String interactionName, Npc npcSource, String speechText, ConversationChain conversationChain){
        super(interactionName, npcSource, EventType.INTRODUCTION, speechText);
        this.conversationChain = conversationChain;

    }

    /**
     * Sets up the initial state for the conversation. Should be done after conversationChain object is complete.
     * Ensures that we have a connection between a button with the NPC's name and the appropriate first dialogue node.
     */
    public void initialSetup() {
        this.currentKey = super.getNpcSource().getName();
        ArrayList<String> nextKey = conversationChain.getOptions(getCurrentKey());
        setCurrentKey(nextKey.get(0));
    }

    /**
     * Returns a ConversationChain clone associated with the event.
     *
     * @return the ConversationChain instance.
     */
    public ConversationChain getConversationChain() {
        return new ConversationChain(conversationChain);
    }

    /**
     * Sets the current key for the conversation chain.
     *
     * @param currentKey the current key.
     */
    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    /**
     * Returns the current key in the conversation chain.
     *
     * @return the current key.
     */
    public String getCurrentKey() {
        return currentKey;
    }
}
