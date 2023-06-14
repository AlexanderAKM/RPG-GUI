package nl.rug.ai.oop.rpg.model.NPC;

public class IntroductionEvent extends Events{

    String speechText;


    private String returnText;

    private String failText;

    public IntroductionEvent(String interactionName, Npc npcSource, String speechText, String returnText){
        super(interactionName, npcSource, EventType.INTRODUCTION, speechText);
        this.returnText = returnText;
    }

    public String getReturnText(){
        return returnText;
    }

}
