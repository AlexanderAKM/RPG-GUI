package nl.rug.ai.oop.rpg.model.players;

import nl.rug.ai.oop.rpg.model.inventory.*;
import nl.rug.ai.oop.rpg.model.inventory.inventories.generalInventory;
import nl.rug.ai.oop.rpg.model.location.*;

/**
 * @author RobertHielkema
 */
public class Player {

    private int intelligence;
    private int social;
    private int energy;
    private int wellbeing;
    private int money;
    private Room currentRoom;
    private generalInventory inventory;

    private static Player player;

    private Player() {
        intelligence = 0;
        social = 0;
        wellbeing = 0;
        money = 0;

    }

    public static Player getInstance(){
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public void chooseProgramme(String programme) {
        if (programme.equals("AI") || programme.equals("Artificial Intelligence")) {
            player.changeIntelligence(80);
            player.changeSocial(60);
            player.changeWellbeing(100);
            player.changeMoney(0);
        } else if (programme.equals("AP") || programme.equals("Applied Physics")) {
            player.changeIntelligence(90);
            player.changeSocial(50);
            player.changeWellbeing(100);
            player.changeMoney(0);
        } else if (programme.equals("CS") || programme.equals("Computing Science")) {
            player.changeIntelligence(100);
            player.changeSocial(40);
            player.changeWellbeing(100);
            player.changeMoney(0);
        }
    }

    public void changeIntelligence(int change){
        this.intelligence += change;
    }

    public void changeSocial(int change){
        this.social += change;
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
