package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

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
        Enemy Bob = new Enemy("Bob", 90,90,90,90);
        Enemy Harmen = new Enemy("Harmen", 90,90,90,90);
        Enemy Michael = new Enemy("Michael", 2, 2, 2, 2);

        Bob.inititateInteraction("Intro","Hehehehe I am the amazing " + Bob.getName() + ".\n Be scared. \n", Interactions.InteractionFunction.INTRODUCTION);
        Harmen.inititateInteraction("Intro","Yo yo yo yo I am the amazing " + Harmen.getName() + ".\n Be scared. \n", Interactions.InteractionFunction.INTRODUCTION);
        Michael.inititateInteraction("Intro", "I am " + Michael.getName() + "\n Yes?", Interactions.InteractionFunction.INTRODUCTION);


        Bob.inititateInteraction("Bob battles player", "I CHALLENGE U", Interactions.InteractionFunction.BATTLE);


        allNpcs.add(Bob);
        allNpcs.add(Harmen);
        allNpcs.add(Michael);
    }

    public Npc getNpc(String npcName){
        for(Npc npc : allNpcs){
            if(Objects.equals(npc.getName(), npcName)){
                return npc;
            }
        }
        return null;
    }
    public static void main(String[] args) {
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public void playInteraction(NpcButton target, String interactionName){
        // Change this to actually return a string
        Npc npc = target.getNpc();
        String speech = npc.findNpcInteraction(interactionName);
        PropertyChangeEvent payload = new PropertyChangeEvent(this, "Speech", null, speech);
        notifyListeners(payload);
    }
}
