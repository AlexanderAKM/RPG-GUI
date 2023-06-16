package nl.rug.ai.oop.rpg.model.NPC;

import nl.rug.ai.oop.rpg.controller.NPC.NpcActionEvent;

import java.beans.PropertyChangeListener;
import java.util.EventListener;

public interface NpcPropertyChangeListener extends EventListener {


    void propertyChange(NpcPropertyEvent evt);
}
