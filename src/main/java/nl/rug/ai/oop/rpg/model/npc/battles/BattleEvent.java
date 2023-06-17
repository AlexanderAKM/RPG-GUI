package nl.rug.ai.oop.rpg.model.npc.battles;

import nl.rug.ai.oop.rpg.model.npc.Events;
import nl.rug.ai.oop.rpg.model.npc.Npc;

import java.io.Serial;
import java.io.Serializable;

public class BattleEvent extends Events implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    BattleQuestions battleQuestions;
    public BattleEvent(String interactionName, Npc npcSource, String speechText, BattleQuestions battleQuestions){
        super(interactionName,npcSource, EventType.BATTLE, speechText);
        this.battleQuestions = battleQuestions;
    }

    public BattleQuestions getBattleQuestions() {
        return battleQuestions;
    }

}
