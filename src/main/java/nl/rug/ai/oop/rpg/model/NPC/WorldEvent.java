package nl.rug.ai.oop.rpg.model.NPC;

public class WorldEvent extends Events{
    public enum effectOnWorld {
        UNLOCK
    }
    String speechText;

    public WorldEvent(String interactionName, Npc npcSource, effectOnWorld effect, String speechText, BattleQuestions battleQuestions, Events.EventType eventType){
        super(interactionName,npcSource, EventType.WORLD_EVENT, speechText);

        switch (effect){
            case UNLOCK:
                unlockRoom();
                break;
        }
    }

    public void unlockRoom(){

    }

}
