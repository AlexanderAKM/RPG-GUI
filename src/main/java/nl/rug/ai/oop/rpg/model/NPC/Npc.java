package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
import java.util.ArrayList;

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

    public void inititateInteraction(String interactionName, Interactions.InteractionFunction function){
        NpcInitiatedInteractions newInteraction = new NpcInitiatedInteractions(interactionName,this, function);
        npcInteractions.add(newInteraction);
        //npcInteractions
    }

    public void findNpcInteraction(String interactionName){
        for (NpcInitiatedInteractions interaction : npcInteractions){
            if(interaction.getName() == interactionName){
                interaction.doInteraction();
            }
        }
    }

    public void npcAct(){

    }
}
