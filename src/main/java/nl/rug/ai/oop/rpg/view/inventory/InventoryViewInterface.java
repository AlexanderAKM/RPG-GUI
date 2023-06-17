package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.controller.inventory.ItemListener;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;

/**
 * This interface defines the methods that an InventoryView should implement.
 * It is used to ensure that all classes that implement it, such as InventoryView, provide these methods.
 *
 * @author Alexander Müller
 */
public interface InventoryViewInterface {

    /**
     * Sets the ItemListener for the InventoryView.
     *
     * @param itemListener the item listener to set
     */
    void setItemListener(ItemListener itemListener);

    /**
     * Loads items into the inventory.
     *
     * @param languageManager the language manager to use for translations
     */
    void loadInventory(LanguageManager languageManager);
}
