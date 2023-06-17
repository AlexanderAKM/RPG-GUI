package nl.rug.ai.oop.rpg.model.npc;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleEvent;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationEvent;
import nl.rug.ai.oop.rpg.model.npc.events.Events;
import nl.rug.ai.oop.rpg.model.npc.events.worldevents.WorldEvent;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents an NPC. Mostly just holds all events associated with an NPC. For time-being only one event is ever,
 * associated with an NPC.
 *
 *  * @author Kyriakos Hjikakou
 */
public class Npc implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public enum EventType {
        BATTLE,
        INTRODUCTION,
        WORLD_EVENT,
        RESET,
        RESPONSE
    }

    private final String name;
    private int money;
    private final ArrayList<Events> playerEvents;
    private HashMap<String, BattleEvent> npcBattleEvents;

    private HashMap<String, WorldEvent> npcWorldEvents;

    private HashMap<String, ConversationEvent> npcIntroductionEvents;

    /**
     * Npc constructor that initializes the NPC with the given name and money.
     * It also initializes the lists and maps used to store all types of events.
     *
     * @param name the name of the NPC
     * @param money the initial amount of money the NPC has
     */
    public Npc(String name, int money){
        playerEvents = new ArrayList<Events>();
        npcBattleEvents = new HashMap<String, BattleEvent>();
        npcWorldEvents = new HashMap<String, WorldEvent>();
        npcIntroductionEvents = new HashMap<String, ConversationEvent>();

        this.name = name;
        this.money = money;
    }

    /**
     * Copy constructor for the Npc class.
     *
     * @param original the Npc instance to copy
     */
    public Npc(Npc original){
        this.playerEvents = original.playerEvents;
        this.npcBattleEvents = original.npcBattleEvents;
        this.npcWorldEvents = original.npcWorldEvents;
        this.npcIntroductionEvents = original.npcIntroductionEvents;

        this.name = original.name;
        this.money = original.money;
    }

    /**
     * Get the name of the NPC.
     *
     * @return the name of the NPC
     */
    public String getName(){
        return name;
    }

    /**
     * Get the amount of money the NPC has.
     *
     * @return the amount of money the NPC has
     */
    public int getMoney(){
        return money;
    }

    /**
     * Change the amount of money the NPC has by the given amount.
     *
     * @param amount the amount to change the NPC's money by
     */
    public void changeMoney(int amount){
        money += amount;
    }

    /**
     * Add a new event to the list of player events.
     *
     * @param newInteraction the new event to add
     */
    public void setEvent(Events newInteraction){
        playerEvents.add(newInteraction);
    }

    /**
     * Add a new BattleEvent to the NPC's map of battle events.
     *
     * @@param battleEvent the BattleEvent to add
     */
    public void setNpcBattleEvents(BattleEvent battleEvent){
        npcBattleEvents.put(battleEvent.getName(), battleEvent);
    }

    /**
     * Add a new WorldEvent to the NPC's map of world events.
     *
     * @param worldEvent the WorldEvent to add
     */
    public void setNpcWorldEvents(WorldEvent worldEvent){
        npcWorldEvents.put(worldEvent.getName(), worldEvent);
    }

    /**
     * Add a new ConversationEvent to the NPC's map of introduction events.
     *
     * @param conversationEvent the ConversationEvent to add
     */
    public void setNpcIntroductionEvents(ConversationEvent conversationEvent) { npcIntroductionEvents.put(conversationEvent.getName(), conversationEvent);}

    /**
     * Get a BattleEvent from the NPC's map of battle events by event name.
     *
     * @param eventName the name of the event to get
     * @return the BattleEvent with the given name, or null if no such event exists
     */
    public BattleEvent getNpcBattleEvents(String eventName){
       return npcBattleEvents.get(eventName);
    }

    /**
     * Get a ConversationEvent from the NPC's map of introduction events by event name.
     *
     * @param eventName the name of the event to get
     * @return the ConversationEvent with the given name, or null if no such event exists
     */
    public ConversationEvent getIntroductionEvent(String eventName){
        return npcIntroductionEvents.get(eventName);
    }

    /**
     * Get a WorldEvent from the NPC's map of world events by event name.
     *
     * @param eventName the name of the event to get
     * @return the WorldEvent with the given name, or null if no such event exists
     */
    public WorldEvent getWorldEvent(String eventName){
        return npcWorldEvents.get(eventName);
    }


    /**
     * Find the last event that was added to the NPC's list of player events.
     *
     * @return the last event that was added to the NPC's list of player events, or null if the list is empty
     */
    public Events findNpcEvent(){
        if(!playerEvents.isEmpty()){
            return playerEvents.get(playerEvents.size()-1);
        }
        return null;
    }
}
