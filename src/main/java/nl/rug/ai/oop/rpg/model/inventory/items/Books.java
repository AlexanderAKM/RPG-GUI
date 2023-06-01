package nl.rug.ai.oop.rpg.model.inventory.items;

import nl.rug.ai.oop.rpg.model.inventory.Item;

public class Books extends Item {
    private int intelligenceIncrease;
    private int wellbeingIncrease;

    public Books(int initialIntelligenceIncrease, int initialWellbeingIncrease) {
        super("Books");
        this.intelligenceIncrease = initialIntelligenceIncrease;
        this.wellbeingIncrease = initialWellbeingIncrease;
    }

    public void changeIntelligence(int change) {
        intelligenceIncrease += change;
    }

    public void changeWellbeing(int change) {
        wellbeingIncrease += change;
    }
    public void use() {
        intelligence.increase(intelligenceIncrease);
        wellbeing.increase(wellbeingIncrease);
    }
}

