package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;

public class PlayerStatsPane extends JPanel {

    Player player;
    JLabel intelligence;
    JLabel social;
    JLabel wellbeing;
    JLabel money;
    public PlayerStatsPane(Player player) {
        this.player = player;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        intelligence = new JLabel();
        social = new JLabel();
        wellbeing = new JLabel();
        money = new JLabel();
    }
}
