package nl.rug.ai.oop.rpg.model.NPC;

public class BattleEvent extends Events {

    BattleQuestions battleQuestions;
    public BattleEvent(String interactionName, Npc npcSource, String speechText, BattleQuestions battleQuestions){
        super(interactionName,npcSource, EventType.BATTLE, speechText);
        this.battleQuestions = battleQuestions;
    }

    public BattleQuestions getBattleQuestions() {
        return battleQuestions;
    }

    /*public String doInteraction(){
        this.battleInteraction();

        return speechText;
    }*/
}
