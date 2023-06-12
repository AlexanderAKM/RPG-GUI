package nl.rug.ai.oop.rpg.model.NPC;

import java.util.ArrayList;

public class NpcInitiatedInteractions {

    public enum InteractionFunction {
        BATTLE,
        SHOP,
        SOCIALISE,
        INTRODUCTION,
        UNLOCK
    }

    String interactionName;
    String speechText;
    InteractionFunction selected;

    Npc npcSource;

    Events chosenEvent;

    ArrayList<String> payload;
    Object payloadObject;
    public NpcInitiatedInteractions(String interactionName, Npc npcSource, InteractionFunction function, String speechText, Object battleQuestions){
       // super(interactionName,npcSource, function);
        this.interactionName = interactionName;
        this.selected = function;
        this.speechText = speechText;
        this.npcSource = npcSource;
        payload = new ArrayList<String>();
        payloadObject = battleQuestions;
        setupEvent();
        //this.chosenEvent = chosenEvent;
        //this.npcName = npcName;
        //setupEvent(battleQuestions);
    }

    public String getName(){
        return interactionName;
    }

    public String setupEvent(){
        // Change this to have a system where text is sent out, then a specific end effect occurs, which are these
        // Output text first:
        switch (selected){
            case BATTLE:
                chosenEvent = new BattleEvent("Outside Battle", npcSource, selected, speechText, (BattleQuestions)payloadObject);
                payload = ((BattleQuestions) payloadObject).getAnswers();
                // Make a battle event
            case INTRODUCTION:


            /*case GIFT:
                this.giftInteraction();
            case SOCIALISE:
                this.socialInteraction();
            */
        }
        return speechText;
    }


    // Clone this in the NPC class instead
    public NpcInitiatedInteractions getInteractionBadly(){
        return this;
    }

    // Clone this too
    public BattleQuestions returnBattleQuestions(){
        return (BattleQuestions)payloadObject;
    }

    public InteractionFunction getInteractionFunction(){
        return selected;
    }

    public ArrayList<String> returnPayload(){
        return payload;
    }

    public String returnSpeechText(){
        return speechText;
    }

    public Npc getNpcSource(){
        return npcSource;
    }
}
