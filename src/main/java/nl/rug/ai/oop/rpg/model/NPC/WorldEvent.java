package nl.rug.ai.oop.rpg.model.NPC;

public class WorldEvent extends Events{
    public enum effectOnWorld {
        UNLOCK
    }
    String speechText;

    private int condition;

    private boolean hasFinishedEventChain;

    public WorldEvent(String interactionName, Npc npcSource, effectOnWorld effect, String speechText, BattleQuestions battleQuestions, Events.EventType eventType){
        super(interactionName,npcSource, EventType.WORLD_EVENT, speechText);

        switch (effect){
            case UNLOCK:
                unlockRoom();
                break;
        }
    }

    public void unlockRoom(){
        // unlockRoom
    }

    public void setHasFinishedEventChain(boolean value){
        hasFinishedEventChain = value;
    }

    public boolean gethasFinishedEventChain(){
        return hasFinishedEventChain;
    }

    public void setCondition(int value){
        condition = value;
    }

    public int getCondition(){
        return condition;
    }

}
