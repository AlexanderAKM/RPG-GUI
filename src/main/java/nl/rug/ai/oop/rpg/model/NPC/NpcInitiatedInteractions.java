package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
public class NpcInitiatedInteractions extends Interactions{
    public NpcInitiatedInteractions(String interactionName, Npc npcSource, InteractionFunction function){
        super(interactionName,npcSource, function);
    }

    public void doInteraction(InteractionFunction function){
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
