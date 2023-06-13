package nl.rug.ai.oop.rpg.model.NPC;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Npc {
    public enum EventType {
        BATTLE,
        SHOP,
        SOCIALISE,
        INTRODUCTION,
        WORLD_EVENT
    }
    private String name;
    private int money;



    //private ArrayList<NpcInitiatedInteractions> npcInteractions;
    private ArrayList<Events> playerEvents;
    HashMap<String, BattleEvent> npcBattleEvents;


    public Npc(String name, int money){
        //npcInteractions = new ArrayList<NpcInitiatedInteractions>();
        playerEvents = new ArrayList<Events>();
        npcBattleEvents = new HashMap<String, BattleEvent>();

        this.name = name;
        this.money = money;
    }

    public Npc(Npc original){
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

    public BattleEvent getNpcBattleEvents(String eventName){
       return npcBattleEvents.get(eventName);
    }

    /*public void inititateInteraction(String interactionName, String speechText,  NpcInitiatedInteractions.InteractionFunction function){
        // make a random ass event object for each one
        NpcInitiatedInteractions newInteraction = new NpcInitiatedInteractions(interactionName,this, function, speechText, null);
        npcInteractions.add(newInteraction);
        //npcInteractions
    }

    /*public void inititateBattle(String interactionName, String speechText,  NpcInitiatedInteractions.InteractionFunction function, String question, ArrayList<String> answers, String correctAnswer, String victoryText, String loseText){
        BattleQuestions battleQuestions = new BattleQuestions(question, answers, correctAnswer, victoryText, loseText);
        NpcInitiatedInteractions newInteraction = new NpcInitiatedInteractions(interactionName,this, function, speechText, battleQuestions);
        npcInteractions.add(newInteraction);
    }

    public NpcInitiatedInteractions findNpcInteraction(String interactionName){
        NpcInitiatedInteractions output = null;
        for (NpcInitiatedInteractions interaction : npcInteractions){
            if(Objects.equals(interaction.getName(), interactionName)){
                output = interaction.getInteractionBadly();
            }
        }
        return output;
    }*/

    public Events findNpcEvent(){
        if(!playerEvents.isEmpty()){
            return playerEvents.get(playerEvents.size()-1);
        }
        return null;
    }


    public void npcAct(){

    }
}
