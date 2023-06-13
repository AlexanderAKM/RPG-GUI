package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;

public class EventBuilder {
    private String interactionName;

    private int condition;
    private Npc npcSource;
    //private Npc.eventType function;
    private String speechText;
    private BattleQuestions battleQuestions;

    public EventBuilder setInteractionName(String interactionName) {
        this.interactionName = interactionName;
        return this;
    }

    public EventBuilder setNpcSource(Npc npcSource) {
        this.npcSource = npcSource;
        return this;
    }

    /*public EventBuilder setFunction(Npc.eventType function) {
        this.function = function;
        return this;
    }*/

    public EventBuilder setSpeechText(String speechText) {
        this.speechText = speechText;
        return this;
    }

    /*public EventBuilder setBattleQuestions(BattleQuestions battleQuestions) {
        this.battleQuestions = battleQuestions;
        return this;
    }*/

    public BattleEvent buildBattleEvent(BattleQuestions battleQuestions) {
        return new BattleEvent(interactionName, npcSource, speechText, battleQuestions);
    }



    public WorldEvent buildWorldEvent(WorldEvent.effectOnWorld effect, int condition, String successText, String failText, LocationManager locationManager) {
        WorldEvent event = new WorldEvent(interactionName, npcSource, effect, speechText,condition, Events.EventType.WORLD_EVENT, successText, failText, locationManager);
        switch (effect) {
            case UNLOCK:
                //event.unlockRoom();
                break;
            // Add other effects if necessary
        }
        return event;
    }
}
