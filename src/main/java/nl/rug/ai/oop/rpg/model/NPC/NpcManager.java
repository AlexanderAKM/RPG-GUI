package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
public class NpcManager {

    public static void main(String[] args) {
        LocationManager test = new LocationManager();
        // Just a test
        Enemy Bob = new Enemy("Bob", 90,90,90,90);
        test.addNpcs(Bob, test.map.get(0));
        Bob.inititateInteraction("Bob battles player", Interactions.InteractionFunction.BATTLE);
    }
}
