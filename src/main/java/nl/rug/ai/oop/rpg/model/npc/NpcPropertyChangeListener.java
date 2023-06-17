package nl.rug.ai.oop.rpg.model.npc;

import java.util.EventListener;

public interface NpcPropertyChangeListener extends EventListener {
    void propertyChange(NpcPropertyChangeEvent evt);
}
