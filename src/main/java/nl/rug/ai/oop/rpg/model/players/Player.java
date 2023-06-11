package nl.rug.ai.oop.rpg.model.players;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.location.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author RobertHielkema
 */
public class Player implements Serializable {

    private int intelligence;
    private int social;
    private int wellbeing;
    private int money;
    private Room currentRoom;
    private Inventory inventory;
    private String programme;
    private String language;
    private static Player player;

    private Player() {
        intelligence = 0;
        social = 0;
        wellbeing = 0;
        money = 0;
        inventory = new Inventory();
    }

    public static Player getInstance(){
        if (player == null) {
            player = new Player();
        }
        return player;
    }

    public void chooseProgramme(String programme) {
        player.programme = programme;
        if (programme.equals("AI") || programme.equals("Artificial Intelligence")) {
            player.changeIntelligence(70);
            player.changeSocial(60);
            player.changeWellbeing(100);
            player.changeMoney(0);
        } else if (programme.equals("AP") || programme.equals("Applied Physics")) {
            player.changeIntelligence(85);
            player.changeSocial(40);
            player.changeWellbeing(100);
            player.changeMoney(0);
        } else if (programme.equals("CS") || programme.equals("Computing Science")) {
            player.changeIntelligence(100);
            player.changeSocial(20);
            player.changeWellbeing(100);
            player.changeMoney(0);
        }
    }

    public void save(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
        String filename = dtf.format(LocalDateTime.now());
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(filename))) {
            output.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
        SaveFiles.getInstance().saveFileNames(filename);
    }

    public void loadSaveFile(String filename){
        player = null;
        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(filename))) {
            player = (Player)input.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void changeIntelligence(int change){
        if (-change > this.intelligence){
            throw new ArithmeticException("Intelligence can't go lower than 0");
        }
        this.intelligence += change;
    }

    public void changeSocial(int change){
        if (-change > this.social){
            throw new ArithmeticException("Social can't go lower than 0");
        }
        this.social += change;
    }

    public void changeWellbeing(int change){
        if (-change > this.wellbeing){
            throw new ArithmeticException("Wellbeing can't go lower than 0");
        }
        this.wellbeing += change;
    }

    public void changeMoney(int change){
        if (-change > this.money){
            throw new ArithmeticException("Money can't go lower than 0");
        }
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

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public String getLanguage(){
        return language;
    }

    public void setLanguage(String language){
        this.language = language;
    }
}
