package nl.rug.ai.oop.rpg.model.players;

public abstract class Player {

    private int intelligence;
    private int social;
    private int energy;
    private int wellbeing;
    private int money;

    public Player(int intelligence, int social, int energy, int wellbeing, int money) {
        this.intelligence = intelligence;
        this.social = social;
        this.energy = energy;
        this.wellbeing = wellbeing;
        this.money = money;
    }

    public void ChangeIntelligence(int change){
        this.intelligence += change;
    }

    public void ChangeSocial(int change){
        this.social += change;
    }

    public void ChangeEnergy(int change){
        this.energy += change;
    }

    public void ChangeWellbeing(int change){
        this.wellbeing += change;
    }

    public void ChangeMoney(int change){
        this.money += change;
    }
}
