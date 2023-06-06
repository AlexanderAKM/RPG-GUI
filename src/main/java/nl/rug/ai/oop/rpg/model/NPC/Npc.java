package nl.rug.ai.oop.rpg.model.NPC;
import nl.rug.ai.oop.rpg.model.players.Player;
import java.util.ArrayList;

public abstract class Npc {

    private String name;
    private int money;


    public Npc(String name, int money){
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

    public Interactions inititateInteraction(String interactionName, Interactions.InteractionFunction function){
        Interactions newInteraction = new NpcInitiatedInteractions(interactionName,this, function);
        return newInteraction;
    }

    public void npcAct(){

    }
}
