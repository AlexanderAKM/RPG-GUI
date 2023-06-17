package nl.rug.ai.oop.rpg.model.npc.events.conversations;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a chain of text and reply options within in a conversation (dialogue).
 * This class represents interactive dialogues between the player and NPCs.
 * Where dialogueText are the nodes in the chain and dialogueConnections are what links them.
 *
 * @author Kyriakos Hjikakou
 */
public class ConversationChain implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

   private HashMap<String, ArrayList<String>> dialogueText;
   private HashMap<String, String> dialogueConnections;
   private ArrayList<String> finalText;

    /**
     * Constructs a new ConversationChain object, and initialises the HashMaps.
     */
    public ConversationChain(){
        dialogueText = new HashMap<String, ArrayList<String>>();
        dialogueConnections = new HashMap<String, String>();
    }

    /**
     * Constructs a shallow copy of a particular ConversationChain instance.
     */
    public ConversationChain(ConversationChain original) {
        this.dialogueText = new HashMap<>(original.dialogueText);
        this.dialogueConnections = new HashMap<>(original.dialogueConnections);
        this.finalText = new ArrayList<>(original.finalText);
    }

    /**
     * Adds new dialogues with its options to the conversation chain.
     *
     * @param textKey the key for the dialogue text.
     * @param options the dialogue options.
     */
    public void addToConversationChain(String textKey, ArrayList<String> options){
        dialogueText.put(textKey, options);
    }

    /**
     * Adds a connection between two dialogues in the conversation chain.
     *
     * @param textKey the key for the current dialogue text.
     * @param nextKey the key for the next dialogue text.
     */
    public void addToDialogueConnections (String textKey, String nextKey){
        dialogueConnections.put(textKey, nextKey);
    }

    /**
     * Sets the final text for the conversation chain.
     *
     * @param finalText the final text.
     */
    public void setFinalText(ArrayList<String> finalText){
        this.finalText = finalText;
    }

    /**
     * Returns the final texts in the conversation chain.
     *
     * @return the final texts.
     */
    public ArrayList<String> getFinalTexts(){
        return  finalText;
    }

    /**
     * Returns the next key in the dialogue connection for a given key.
     *
     * @param key the key for the current dialogue text.
     * @return the next key.
     */
    public String getNextKey(String key){
        return dialogueConnections.get(key);
    }

    /**
     * Returns the dialogue options for a given key.
     *
     * @param textKey the key for the dialogue text.
     * @return the dialogue options.
     */
    public ArrayList<String> getOptions(String textKey){
        return dialogueText.get(textKey);
    }
}
