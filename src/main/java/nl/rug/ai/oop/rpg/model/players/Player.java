package nl.rug.ai.oop.rpg.model.players;

import nl.rug.ai.oop.rpg.model.inventory.*;
import nl.rug.ai.oop.rpg.model.inventory.inventories.generalInventory;
import nl.rug.ai.oop.rpg.model.location.*;

public abstract class Player {

    private int intelligence;
    private int social;
    private int energy;
    private int wellbeing;
    private int money;
    //public LocationManager locationManager;
    private Room currentRoom;
    private generalInventory inventory;

    public Player(int intelligence, int social, int energy, int wellbeing, int money) {
        this.intelligence = intelligence;
        this.social = social;
        this.energy = energy;
        this.wellbeing = wellbeing;
        this.money = money;
        //this.currentRoom = locationManager.getPlayerRoom();
    }

    public void changeIntelligence(int change){
        this.intelligence += change;
    }

    public void changeSocial(int change){
        this.social += change;
    }

    public void changeEnergy(int change){
        this.energy += change;
    }

    public void changeWellbeing(int change){
        this.wellbeing += change;
    }

    public void changeMoney(int change){
        this.money += change;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public int getSocial() {
        return social;
    }

    public int getEnergy() {
        return energy;
    }

    public int getWellbeing() {
        return wellbeing;
    }

    public int getMoney() {
        return money;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }
}
