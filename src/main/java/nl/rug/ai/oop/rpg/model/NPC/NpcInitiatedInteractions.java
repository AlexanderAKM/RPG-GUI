package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
public class NpcInitiatedInteractions extends Interactions{
    public NpcInitiatedInteractions(String interactionName, Npc npcSource, InteractionFunction function){
        super(interactionName,npcSource, function);
    }

    public void doInteraction(){
        // Change this to have a system where text is sent out, then a specific end effect occurs, which are these
        switch (this.getFunction()){
            case BATTLE:
                this.battleInteraction();
            case GIFT:
                this.giftInteraction();
            case SOCIALISE:
                this.socialInteraction();
            case INTRODUCTION:
                this.introductionInteraction();
        }
    }
}
