package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class NpcManager {
    ArrayList<PropertyChangeListener> listeners = new ArrayList<>();

    // Fix this, shouldn't be public, should be encapsulated
    public ArrayList<Npc> allNpcs;
    //public ArrayList<Npc>

    public NpcManager() {
        allNpcs  = new ArrayList<Npc>();

        initialiseNpcs();
    }

    private void notifyListeners(PropertyChangeEvent payload) {
        Iterator<PropertyChangeListener> allListeners = listeners.iterator();

        while (allListeners.hasNext()) {
            allListeners.next().propertyChange(payload);
        }
    }

    public void initialiseNpcs(){
        // Turn this all into a builder type class

        // Move this to a new event manager that creates a bunch of pre-determined ones
        ArrayList<String> answers =  new ArrayList<>();
        answers.add("Cover");
        answers.add("1");
        answers.add("2");
        answers.add("3");

        Enemy Bob = new Enemy("Bob", 90,90,90,90);
        Enemy Harmen = new Enemy("Harmen", 90,90,90,90);
        Enemy Michael = new Enemy("Michael", 2, 2, 2, 2);
        Enemy EvilMan = new Enemy("Evil Man", 2, 2, 2, 2);

        Bob.inititateInteraction("Intro","Hehehehe I am the amazing " + Bob.getName() + ".\n Be scared. \n", NpcInitiatedInteractions.InteractionFunction.INTRODUCTION);
        Harmen.inititateInteraction("Intro","Yo yo yo yo I am the amazing " + Harmen.getName() + ".\n Be scared. \n", NpcInitiatedInteractions.InteractionFunction.INTRODUCTION);
        Michael.inititateInteraction("Intro", "I am " + Michael.getName() + "\n Yes?", NpcInitiatedInteractions.InteractionFunction.INTRODUCTION);

        EvilMan.inititateBattle("EvilBattle", "Death be upon you", NpcInitiatedInteractions.InteractionFunction.BATTLE, "What is the best study association?", answers, "Cover", "Damn you got it right.", "Damn I got it wrong.");



        allNpcs.add(Bob);
        allNpcs.add(Harmen);
        allNpcs.add(Michael);
        allNpcs.add(EvilMan);
    }

    public Npc getNpc(String npcName){
        for(Npc npc : allNpcs){
            if(Objects.equals(npc.getName(), npcName)){
                return npc;
            }
        }
        return null;
    }
    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public void npcInteraction(NpcButton target, String interactionName){
        Npc npc = target.getNpc();
        NpcInitiatedInteractions interaction = npc.findNpcInteraction();
        switch (interaction.getInteractionFunction()) {
            case BATTLE:
                playBattle(target, interaction);
                break;
            case INTRODUCTION:
                playInteraction(target, interaction);
                break;
        }
    }

    public void playInteraction(NpcButton target, NpcInitiatedInteractions interaction){
        // Change this to actually return a string
        Npc npc = target.getNpc();
        //NpcInitiatedInteractions interaction = npc.findNpcInteraction(interactionName);
        String speech = interaction.returnSpeechText();

        PropertyChangeEvent payload = new PropertyChangeEvent(this, "Speech", null, speech);
        notifyListeners(payload);
    }

    public void playBattle(NpcButton target, NpcInitiatedInteractions interaction){
        Npc npc = target.getNpc();
        //NpcInitiatedInteractions interaction = npc.findNpcInteraction(interactionName);

        ArrayList<String> answers = new ArrayList<String>();
        answers = interaction.returnPayload();

        String speech = interaction.returnSpeechText() + "\n" + interaction.returnBattleQuestions().getQuestion();

        PropertyChangeEvent payload = new PropertyChangeEvent(this, "Battle", speech, interaction);
        notifyListeners(payload);
    }

    public void checkAnswer(NpcButton target){
        Npc npc = target.getNpc();
        String interactionName = target.getInteractionName();

        NpcInitiatedInteractions interaction = npc.findNpcInteraction();
        BattleQuestions battleQuestions = interaction.returnBattleQuestions();
        // Check if it was the correct answer
        if(Objects.equals(target.getText(), battleQuestions.correctAnswer)){
            // Yes correct answer, appropriate effects
            // Reduce NPC stats
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Correct", battleQuestions.getVictoryText(), interaction);
            notifyListeners(payload);
        } else {
            // Wrong answer appropriate effects
            // Idk reduce player stats
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Wrong", null, battleQuestions.getLosingText());
            notifyListeners(payload);
        }

    }
}
