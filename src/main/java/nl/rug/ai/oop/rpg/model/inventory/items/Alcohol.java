package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;

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

    public void use() {
        intelligence.decrease(intelligenceDecrease);
        social.increase(socialIncrease);
    }
}

