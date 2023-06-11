package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
public class NpcInitiatedInteractions extends Interactions{
    String speechText;
    public NpcInitiatedInteractions(String interactionName, Npc npcSource, InteractionFunction function, String speechText){
        super(interactionName,npcSource, function);
        this.speechText = speechText;
        //this.npcName = npcName;
    }

    public String doInteraction(){
        // Change this to have a system where text is sent out, then a specific end effect occurs, which are these
        // Output text first:
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
        return speechText;
    }
}
