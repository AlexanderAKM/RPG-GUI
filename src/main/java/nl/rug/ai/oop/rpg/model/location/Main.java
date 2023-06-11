package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.controller.NPC.NpcController;
import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.NPC.NpcManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.NPC.NpcView;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Player player = Player.getInstance();

        LocationManager manager = new LocationManager();
        LocationController controller = new LocationController(manager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller);

        NpcManager model = new NpcManager();
        NpcController npcController = new NpcController(model);

        NpcView npcPanel = new NpcView(model);
        npcPanel.setup(model, npcController);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);

        JPanel npcThing = npcPanel.returnNpcView();
        JPanel locationView = gamePanel.returnLocationView();

        gamePanel.setNpcPanel(npcThing);
        //frame.add(npcThing, BorderLayout.NORTH);
        frame.add(locationView, BorderLayout.CENTER); // adds the game panel
        frame.setVisible(true);

        gamePanel.frameSetUp();
        frame.revalidate();

    }
}
