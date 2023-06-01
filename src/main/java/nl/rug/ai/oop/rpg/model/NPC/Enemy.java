package nl.rug.ai.oop.rpg.model.NPC;

public class Enemy extends Npc{
    private int social;
    private int energy;
    private int wellbeing;
    public Enemy(String name, int money, int social, int energy, int wellbeing){
        super(name, money);
        this.social = social;
        this.energy = energy;
        this.wellbeing = wellbeing;
    }

}
