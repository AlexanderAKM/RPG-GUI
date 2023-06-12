package nl.rug.ai.oop.rpg.model.NPC;
import java.util.ArrayList;
import java.util.Objects;

public abstract class Npc {

    private String name;
    private int money;

    private ArrayList<NpcInitiatedInteractions> npcInteractions;
    private ArrayList<Events> playerInteractions;


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

    public void inititateInteraction(String interactionName, String speechText,  NpcInitiatedInteractions.InteractionFunction function){
        // make a random ass event object for each one
        NpcInitiatedInteractions newInteraction = new NpcInitiatedInteractions(interactionName,this, function, speechText, null);
        npcInteractions.add(newInteraction);
        //npcInteractions
    }

    public void inititateBattle(String interactionName, String speechText,  NpcInitiatedInteractions.InteractionFunction function, String question, ArrayList<String> answers, String correctAnswer, String victoryText, String loseText){
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
    }

    public NpcInitiatedInteractions findNpcInteraction(){
        if(!npcInteractions.isEmpty()){
            return npcInteractions.get(npcInteractions.size()-1);
        }
        return null;
    }


    public void npcAct(){

    }
}
