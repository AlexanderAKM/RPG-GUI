import nl.rug.ai.oop.rpg.model.inventory.Inventory;
import nl.rug.ai.oop.rpg.model.inventory.Item;
import java.util.ArrayList;
import java.util.List;

public class generalInventory implements Inventory {
    private List<Item> items;

    public generalInventory() {
        items = new ArrayList<>();
    }

    @Override
    public void addItem(Item item) {
        items.add(item);
    }

    @Override
    public void removeItem(Item item) {
        items.remove(item);
    }

    @Override
    public List<Item> getItems() {
        return items;
    }
}
