package nl.rug.ai.oop.rpg.model.NPC;

public class WorldEvent extends Events{
    public enum effectOnWorld {
        UNLOCK
    }
    String speechText;

    private int condition;

    private String successText;
    private String failText;
    private boolean hasFinishedEventChain;

    public WorldEvent(String interactionName, Npc npcSource, effectOnWorld effect, String speechText, int condition, Events.EventType eventType, String successText, String failText){
        super(interactionName,npcSource, EventType.WORLD_EVENT, speechText);
        hasFinishedEventChain = false;
        this.condition = condition;
        this.successText = successText;
        this.failText = failText;

        switch (effect){
            case UNLOCK:
                unlockRoom();
                break;
        }
    }

    public String getSuccessText(){
        return successText;
    }

    public String getFailText(){
        return failText;
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
