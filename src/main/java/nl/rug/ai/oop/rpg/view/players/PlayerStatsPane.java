package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;

public class PlayerStatsPane extends JPanel {

    Player player;
    public PlayerStatsPane(Player player) {
        this.player = player;
    }
}
