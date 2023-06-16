package nl.rug.ai.oop.rpg.model.NPC;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class IntroductionEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    String speechText;
    private String currentKey;
    private String returnText;
    private ConversationChain conversationChain;

    public IntroductionEvent(String interactionName, Npc npcSource, String speechText, String returnText, ConversationChain conversationChain){
        super(interactionName, npcSource, EventType.INTRODUCTION, speechText);
        this.returnText = returnText;
        this.conversationChain = conversationChain;

    }

    public ConversationChain getConversationChain() {
        return conversationChain;
    }

    public void initialSetup() {
        this.currentKey = super.getNpcSource().getName();
        ArrayList<String> nextKey = conversationChain.getOptions(getCurrentKey());
        setCurrentKey(nextKey.get(0));
    }

    public void setCurrentKey(String currentKey) {
        this.currentKey = currentKey;
    }

    public String getCurrentKey() {
        return currentKey;
    }
}
