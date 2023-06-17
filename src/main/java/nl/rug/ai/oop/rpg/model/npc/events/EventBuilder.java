package nl.rug.ai.oop.rpg.model.npc.events;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.model.npc.Npc;
import nl.rug.ai.oop.rpg.model.npc.events.worldevents.WorldEvent;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleEvent;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleQuestions;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationChain;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationEvent;

/**
 * Builds a generic event and then specialises it with the appropriate type. Makes the process much easier.
 *
 * @author Kyriakos Hjikakou
 */
public class EventBuilder {
    private String interactionName;
    private Npc npcSource;
    private String speechText;

    /**
     * Sets the name of the interaction.
     *
     * @param interactionName the name of the interaction
     * @return this event builder instance
     */
    public EventBuilder setInteractionName(String interactionName) {
        this.interactionName = interactionName;
        return this;
    }

    /**
     * Sets the NPC source of the event. The NPC that this event is associated with.
     *
     * @param npcSource the NPC source
     * @return this event builder instance
     */
    public EventBuilder setNpcSource(Npc npcSource) {
        this.npcSource = npcSource;
        return this;
    }

    /**
     * Sets the speech text for the event. The basic text that would be shown
     * to a player upon initiating an event.
     *
     * @param speechText the speech text
     * @return this event builder instance
     */
    public EventBuilder setSpeechText(String speechText) {
        this.speechText = speechText;
        return this;
    }

    /**
     * Builds a BattleEvent.
     *
     * @param battleQuestions the battle questions for the event
     * @return a new BattleEvent instance
     */
    public BattleEvent buildBattleEvent(BattleQuestions battleQuestions) {
        return new BattleEvent(interactionName, npcSource, speechText, battleQuestions);
    }

    /**
     * Builds a WorldEvent.
     *
     * @param effect the type of effect that event will have on the world
     * @param cost the cost for the world event
     * @param successText the success text for the event
     * @param failText the fail text for the event
     * @param locationManager the location manager in order to have effects onto the world
     * @return a new WorldEvent instance
     */
    public WorldEvent buildWorldEvent(WorldEvent.effectOnWorld effect, int cost, String successText, String failText, LocationManager locationManager) {
        WorldEvent event = new WorldEvent(interactionName, npcSource, effect, speechText,cost, Events.EventType.WORLD_EVENT, successText, failText, locationManager);
        return event;
    }

    /**
     * Builds a ConversationEvent.
     *
     * @param conversationChain the conversation chain for the event
     * @return a new ConversationEvent instance
     */
    public ConversationEvent buildConversationEvent(ConversationChain conversationChain){
        ConversationEvent event = new ConversationEvent(interactionName, npcSource, speechText, conversationChain);
        return event;
    }
}
