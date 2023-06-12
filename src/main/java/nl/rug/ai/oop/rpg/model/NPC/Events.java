package nl.rug.ai.oop.rpg.model.NPC;

public abstract class Events {
    private String interactionName;
    private Npc npcSource;

    //private Player playerSource;

    //private InteractionFunction chosenFunction;

    public Events(String eventName, Npc npcSource){
        this.interactionName = eventName;
        this.npcSource = npcSource;
        //this.chosenFunction = function;
        //this.playerSource = playerSource;
    }

    // Ideally an object that posses all text should be passed to below
    public void battleInteraction(){
        // This should get the battle view to open
        // The fighting stuff

        // Multiple choice


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
        System.out.println("Hehehehehehe.");
        // The social stuff
    }

    /*public InteractionFunction getFunction(){
        return this.chosenFunction;
    }*/

    public String getName(){
        return this.interactionName;
    }

}
