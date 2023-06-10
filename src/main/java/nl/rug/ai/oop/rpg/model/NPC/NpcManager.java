package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
public class NpcManager {

    public static void main(String[] args) {
        // Just a test
        Enemy Bob = new Enemy("Bob", 90,90,90,90);
        Bob.inititateInteraction("Intro", Interactions.InteractionFunction.INTRODUCTION);
        Bob.inititateInteraction("Bob battles player", Interactions.InteractionFunction.BATTLE);

    }

    public void playInteraction(Npc target, String interactionName){
        target.findNpcInteraction(interactionName);
    }
}
