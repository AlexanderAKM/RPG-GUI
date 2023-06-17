package nl.rug.ai.oop.rpg.model.listeners;

import java.util.EventListener;

/**
 * An interface that represents a listener for NPC property changes.
 *
 * @author Kyriakos Hjikakou
 */
public interface NpcPropertyChangeListener extends EventListener {

    /**
     * Invoked when an NPC property change event occurs.
     *
     * @param evt the NPC property change event
     */
    void propertyChange(NpcPropertyChangeEvent evt);
}
