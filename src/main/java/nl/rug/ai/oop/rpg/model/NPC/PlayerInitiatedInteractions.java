package nl.rug.ai.oop.rpg.model.NPC;

public class PlayerInitiatedInteractions extends Interactions{
    public PlayerInitiatedInteractions(String interactionName, Npc npcSource, InteractionFunction function){
        super(interactionName,npcSource, function);
    }

    public void expectInteraction(InteractionFunction function){
        switch (function){
            case BATTLE:
                this.battleInteraction();
            case GIFT:
                this.giftInteraction();
            case SOCIALISE:
                this.socialInteraction();
        }
    }
}
