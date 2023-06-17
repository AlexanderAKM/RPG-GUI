package nl.rug.ai.oop.rpg.model.npc.conversations;

import java.util.ArrayList;

/**
 * A builder class that helps in creating a ConversationChain object.
 * This should make it a bit less of a hassle to create a ConversationChain.
 *
 * @author Kyriakos Hjikakou
 */
public class ConversationBuilder {
    private ConversationChain conversationChain;

    public ConversationBuilder() {
        this.conversationChain = new ConversationChain();
    }

    /**
     * Adds a new "Node" to the ConversationChain that represent a single instance of output Text and
     * the player's possible replies (dialogue).
     *
     * @param textKey the key for the dialogue text. The key is also what is appended to the text output.
     * @param options the dialogue options.
     * @return the current ConversationBuilder instance.
     */
    public ConversationBuilder addToConversationChain(String textKey, ArrayList<String> options) {
        this.conversationChain.addToConversationChain(textKey, options);
        return this;
    }

    /**
     * Adds a connection between two "Nodes" in the conversation chain. Connecting one key to another.
     *
     * @param textKey the key for the current dialogue text.
     * @param nextKey the key for the next dialogue text.
     * @return the current ConversationBuilder instance.
     */
    public ConversationBuilder addToDialogueConnections(String textKey, String nextKey) {
        this.conversationChain.addToDialogueConnections(textKey, nextKey);
        return this;
    }

    /**
     * Sets the final text for the conversation chain. Which will indicate that there is no more conversation
     * after this set of strings are interacted with.
     *
     * @param finalText the final text to be set.
     * @return the current ConversationBuilder instance.
     */
    public ConversationBuilder setFinalText(ArrayList<String> finalText) {
        this.conversationChain.setFinalText(finalText);
        return this;
    }

    /**
     * Return the final ConversationChain instance.
     *
     * @return the finished ConversationChain Object.
     */
    public ConversationChain build() {
        return this.conversationChain;
    }
}
