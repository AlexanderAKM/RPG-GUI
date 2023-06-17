package nl.rug.ai.oop.rpg.model.npc;
import nl.rug.ai.oop.rpg.model.npc.events.battles.BattleEvent;
import nl.rug.ai.oop.rpg.model.npc.events.conversations.ConversationEvent;
import nl.rug.ai.oop.rpg.model.npc.events.Events;
import nl.rug.ai.oop.rpg.model.npc.events.worldevents.WorldEvent;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

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

    private ArrayList<Events> playerEvents;
    HashMap<String, BattleEvent> npcBattleEvents;

    HashMap<String, WorldEvent> npcWorldEvents;

    HashMap<String, ConversationEvent> npcIntroductionEvents;


    public Npc(String name, int money){
        //npcInteractions = new ArrayList<NpcInitiatedInteractions>();
        playerEvents = new ArrayList<Events>();
        npcBattleEvents = new HashMap<String, BattleEvent>();
        npcWorldEvents = new HashMap<String, WorldEvent>();
        npcIntroductionEvents = new HashMap<String, ConversationEvent>();

        this.name = name;
        this.money = money;
    }

    public Npc(Npc original){
        this.playerEvents = original.playerEvents;
        this.npcBattleEvents = original.npcBattleEvents;
        this.npcWorldEvents = original.npcWorldEvents;
        this.npcIntroductionEvents = original.npcIntroductionEvents;

        this.name = original.name;
        this.money = original.money;
    }

    public String getName(){
        return name;
    }
    public int getMoney(){
        return money;
    }
    public void changeMoney(int amount){
        money += amount;
    }
    public void setEvent(Events newInteraction){
        playerEvents.add(newInteraction);
    }
    public void setNpcBattleEvents(BattleEvent battleEvent){
        npcBattleEvents.put(battleEvent.getName(), battleEvent);
    }
    public void setNpcWorldEvents(WorldEvent worldEvent){
        npcWorldEvents.put(worldEvent.getName(), worldEvent);
    }
    public void setNpcIntroductionEvents(ConversationEvent conversationEvent) { npcIntroductionEvents.put(conversationEvent.getName(), conversationEvent);}
    public BattleEvent getNpcBattleEvents(String eventName){
       return npcBattleEvents.get(eventName);
    }
    public ConversationEvent getIntroductionEvent(String eventName){
        return npcIntroductionEvents.get(eventName);
    }
    public WorldEvent getWorldEvent(String eventName){
        return npcWorldEvents.get(eventName);
    }

    public Events findNpcEvent(){
        if(!playerEvents.isEmpty()){
            return playerEvents.get(playerEvents.size()-1);
        }
        return null;
    }
}
