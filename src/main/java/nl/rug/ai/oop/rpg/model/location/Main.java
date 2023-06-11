package nl.rug.ai.oop.rpg.model.location;

import nl.rug.ai.oop.rpg.controller.location.LocationController;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.location.GamePanelGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        Player player = Player.getInstance();
        LocationManager manager = new LocationManager();
        LocationController controller = new LocationController(manager);
        GamePanelGUI gamePanel = new GamePanelGUI(manager, controller);
        gamePanel.frameSetUp();

    }
}
