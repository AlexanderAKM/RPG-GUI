package nl.rug.ai.oop.rpg.controller.inventory;

import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.inventory.InventoryView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

public class RoomItemsController implements ItemListener{

    private final Inventory inventory;
    private final Player player;

    public RoomItemsController(Inventory inventory, GamePanelGUI gamePanelGUI, Player player) {
        this.inventory = inventory;
        this.player = player;
        gamePanelGUI.setItemListener(this);
    }
    public void onItemClicked(Item item) {
        // line to remove item from the room

        inventory.addItem(item);
    }
}
