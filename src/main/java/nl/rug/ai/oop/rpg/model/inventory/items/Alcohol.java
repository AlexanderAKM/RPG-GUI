package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;

public class Alcohol extends Item {
    private int intelligenceDecrease;
    private int socialIncrease;

    public Alcohol(int initialIntelligenceDecrease, int initialSocialIncrease) {
        super("Alcohol");
        this.intelligenceDecrease = initialIntelligenceDecrease;
        this.socialIncrease = initialSocialIncrease;
    }

    public void changeIntelligence(int change) {
        intelligenceDecrease += change;
    }

    public void changeSocial(int change) {
        socialIncrease += change;
    }

    public void use(Player player) {
        player.changeIntelligence(intelligenceDecrease);
        player.changeSocial(socialIncrease);
    }
}

