package nl.rug.ai.oop.rpg.model.npc.events;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.WorldEvent;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleEvent;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleQuestions;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationChain;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationEvent;

public class EventBuilder {
    private String interactionName;
    private int condition;
    private Npc npcSource;
    //private Npc.eventType function;
    private String speechText;

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
        return event;
    }

    public ConversationEvent buildIntroductionEvent(ConversationChain conversationChain){
        ConversationEvent event = new ConversationEvent(interactionName, npcSource, speechText, conversationChain);
        return event;
    }
}