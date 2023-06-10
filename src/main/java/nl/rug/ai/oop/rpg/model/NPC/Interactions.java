package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.players.Player;

public abstract class Interactions {

    public enum InteractionFunction {
        BATTLE,
        GIFT,
        SOCIALISE,

        INTRODUCTION,

        WORLDCHANGE
    }

    private String interactionName;
    private Npc npcSource;

    //private Player playerSource;

    private InteractionFunction chosenFunction;

    public Interactions(String interactionName, Npc npcSource, InteractionFunction function){
        this.interactionName = interactionName;
        this.npcSource = npcSource;
        this.chosenFunction = function;
        //this.playerSource = playerSource;
    }

    // Ideally an object that posses all text should be passed to below
    public void battleInteraction(){
        // The fighting stuff

    }

    // Ideally an object that posses all text should be passed to below
    public void giftInteraction(){
        // The gift stuff

    }

    // Ideally an object that posses all text should be passed to below
    public void socialInteraction(){
        // The social stuff

    }

    public void introductionInteraction(){
        // The social stuff
    }

    public InteractionFunction getFunction(){
        return this.chosenFunction;
    }

    public String getName(){
        return this.interactionName;
    }

}
