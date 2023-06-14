package nl.rug.ai.oop.rpg.model.NPC;

import java.util.ArrayList;
import java.util.HashMap;

public class ConversationChain {
    // Harmen - > Yo I'm in your house
    // Yo I'm in your house -> Dang. Why?
    // Dang. - > Bye.
    // Why? - > Hehe
    // Bye -> Home
    // Hehe -> Home

    // The first key is always the NPC's name
    HashMap<String, ArrayList<String>> textChain;

    public ConversationChain(){
        textChain = new HashMap<String, ArrayList<String>>();
    }

    public void addToConversationChain(String textKey, ArrayList<String> options){
        textChain.put(textKey, options);
    }

    public ArrayList<String> getOptions(String textKey){
        return textChain.get(textKey);
    }

    public void getKey(){

    }
}
