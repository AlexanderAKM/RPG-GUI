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

        Npc Bob = new Npc("Bob", 90);
        Npc Harmen = new Npc("Harmen", 90);
        Npc Michael = new Npc("Michael",2);

        Npc humanMan = new Npc("Human man", 2);

        EventBuilder eventBuilder = new EventBuilder()
                .setInteractionName("interactionName")
                .setNpcSource(humanMan)
                .setSpeechText("speechText");

        BattleQuestions coverQuestion = new BattleQuestions("What is the best association?", answers, "Cover", "Yo congrats!", "Boo");
        BattleEvent worldEvent = eventBuilder.buildBattleEvent(coverQuestion);
        humanMan.setEvent(worldEvent);
        humanMan.setNpcBattleEvents(worldEvent);


        Npc EvilMan = new Npc("Evil Man", 2);
        EventBuilder coverBattle = new EventBuilder()
                .setInteractionName("EvilBattle")
                .setNpcSource(EvilMan)
                .setSpeechText("Death be upon you! \n");

        allNpcs.add(Bob);
        allNpcs.add(Harmen);
        allNpcs.add(Michael);
        allNpcs.add(EvilMan);
        allNpcs.add(humanMan);
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
        Events event = npc.findNpcEvent();


        switch (event.getEventType()) {
            case BATTLE:
                ArrayList<String> answers = new ArrayList<String>();
                BattleEvent battleEvent = npc.getNpcBattleEvents(event.getName());
                BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

                answers = battleQuestions.getAnswers();

                String speech = event.getSpeechText() + "\n" + battleQuestions.getQuestion();
                PropertyChangeEvent payload = new PropertyChangeEvent(this, "Battle", speech, battleEvent);
                notifyListeners(payload);
                break;
            case INTRODUCTION:
                //playInteraction(target, interaction);
                break;
        }
    }

    public void checkAnswer(NpcButton target){
        Npc npc = target.getNpc();
        Events event = npc.findNpcEvent();

        BattleEvent battleEvent = npc.getNpcBattleEvents(event.getName());
        BattleQuestions battleQuestions = battleEvent.getBattleQuestions();

        ArrayList<String> answers = battleQuestions.getAnswers();

        // Check if it was the correct answer
        if(Objects.equals(target.getText(), battleQuestions.correctAnswer)){
            // Yes correct answer, appropriate effects
            // Reduce NPC stats
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Correct", battleQuestions.getVictoryText(), battleEvent);
            notifyListeners(payload);
        } else {
            // Wrong answer appropriate effects
            // Idk reduce player stats
            PropertyChangeEvent payload = new PropertyChangeEvent(this, "Wrong", null, battleQuestions.getLosingText());
            notifyListeners(payload);
        }

    }
}
