package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.model.location.LocationManager;
import nl.rug.ai.oop.rpg.view.NPC.NpcButton;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;

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
        Bob.inititateInteraction("Intro", Interactions.InteractionFunction.INTRODUCTION);
        Bob.inititateInteraction("Bob battles player", Interactions.InteractionFunction.BATTLE);

        allNpcs.add(Bob);
    }
    public static void main(String[] args) {
    }

    public void addListener(PropertyChangeListener listener) {
        listeners.add(listener);
    }

    public void playInteraction(NpcButton target, String interactionName){
        Npc npc = target.getNpc();
        npc.findNpcInteraction(interactionName);
    }
}
