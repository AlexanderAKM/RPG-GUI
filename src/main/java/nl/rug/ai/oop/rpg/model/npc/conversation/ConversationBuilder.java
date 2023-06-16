package nl.rug.ai.oop.rpg.model.npc.conversation;

import java.util.ArrayList;
import java.util.List;

public class ConversationBuilder {
    private ConversationChain conversationChain;

    public ConversationBuilder() {
        this.conversationChain = new ConversationChain();
    }

    public ConversationBuilder addToConversationChain(String textKey, ArrayList<String> options) {
        this.conversationChain.addToConversationChain(textKey, options);
        return this;
    }

    public ConversationBuilder addToDialogueConnections(String textKey, String nextKey) {
        this.conversationChain.addToDialogueConnections(textKey, nextKey);
        return this;
    }

    public ConversationBuilder setFinalText(ArrayList<String> finalText) {
        this.conversationChain.setFinalText(finalText);
        return this;
    }

    public ConversationChain build() {
        return this.conversationChain;
    }
}
