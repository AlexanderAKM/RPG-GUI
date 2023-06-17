package nl.rug.ai.oop.rpg.model.npc.conversations;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class ConversationChain implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    // Harmen - > Yo I'm in your house
    // Yo I'm in your house -> Dang. Why?
    // Dang. - > Bye.
    // Why? - > Hehe
    // Bye -> Home
    // Hehe -> Home

    // The first key is always the NPC's name
    HashMap<String, ArrayList<String>> dialogueText;
    HashMap<String, String> dialogueConnections;

    private ArrayList<String> finalText;

    public ConversationChain(){
        dialogueText = new HashMap<String, ArrayList<String>>();
        dialogueConnections = new HashMap<String, String>();
    }

    public void addToConversationChain(String textKey, ArrayList<String> options){
        dialogueText.put(textKey, options);
    }

    public void addToDialogueConnections (String textKey, String nextKey){
        dialogueConnections.put(textKey, nextKey);
    }

    public void setFinalText(ArrayList<String> finalText){
        this.finalText = finalText;
    }

    public ArrayList<String> getFinalTexts(){
        return  finalText;
    }

    public String getNextKey(String key){
        return dialogueConnections.get(key);
    }

    public ArrayList<String> getOptions(String textKey){
        return dialogueText.get(textKey);
    }

    public void getKey(){

    }
}
