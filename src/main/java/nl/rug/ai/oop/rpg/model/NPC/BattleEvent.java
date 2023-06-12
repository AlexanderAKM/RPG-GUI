package nl.rug.ai.oop.rpg.model.NPC;

public class BattleEvent extends Events {
    String speechText;

    BattleQuestions battleQuestions;
    public BattleEvent(String interactionName, Npc npcSource, NpcInitiatedInteractions.InteractionFunction function, String speechText, BattleQuestions battleQuestions){
        super(interactionName,npcSource);
        this.battleQuestions = battleQuestions;
        //this.speechText = speechText;
        //this.npcName = npcName;
    }

    public String doInteraction(){
        // Change this to have a system where text is sent out, then a specific end effect occurs, which are these
        // Output text first:

        this.battleInteraction();

        return speechText;
    }
}
