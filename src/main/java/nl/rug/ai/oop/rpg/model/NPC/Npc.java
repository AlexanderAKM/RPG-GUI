package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Npc {

    private String name;
    private int money;

    private ArrayList<NpcInitiatedInteractions> npcInteractions;
    private ArrayList<Interactions> playerInteractions;


    public Npc(String name, int money){
        npcInteractions = new ArrayList<NpcInitiatedInteractions>();
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

    public void inititateInteraction(String interactionName, String speechText,  Interactions.InteractionFunction function){
        NpcInitiatedInteractions newInteraction = new NpcInitiatedInteractions(interactionName,this, function, speechText);
        npcInteractions.add(newInteraction);
        //npcInteractions
    }

    public String findNpcInteraction(String interactionName){
        String output = null;
        for (NpcInitiatedInteractions interaction : npcInteractions){
            if(Objects.equals(interaction.getName(), interactionName)){
                output = interaction.doInteraction();
            }
        }
        return output;
    }

    public void npcAct(){

    }
}
