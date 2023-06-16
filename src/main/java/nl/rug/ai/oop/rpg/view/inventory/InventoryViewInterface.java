package nl.rug.ai.oop.rpg.view.inventory;

import nl.rug.ai.oop.rpg.controller.inventory.ItemListener;
import nl.rug.ai.oop.rpg.model.location.languageManager;

public interface InventoryViewInterface {
    void setItemListener(ItemListener itemListener);
    void loadInventory(languageManager languageManager);
}
