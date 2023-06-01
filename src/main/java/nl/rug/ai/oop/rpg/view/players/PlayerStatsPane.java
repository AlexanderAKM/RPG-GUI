package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.model.players.students.AIStudent;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsPane extends JPanel {

    Player player;
    JLabel intelligence;
    JLabel social;
    JLabel wellbeing;
    JLabel money;
    String language;
    public PlayerStatsPane(Player player, String language) {
        this.player = player;
        this.language = language;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        if (this.language.equals("English")){
            intelligence = new JLabel("Intelligence: " + this.player.getIntelligence());
            social = new JLabel("Social: " + this.player.getSocial());
            wellbeing = new JLabel("Wellbeing: " + this.player.getWellbeing());
            money = new JLabel("Money: €" + this.player.getMoney());
        } else if (this.language.equals("Dutch")){
            intelligence = new JLabel("Intelligentie: " + this.player.getIntelligence());
            social = new JLabel("Sociaal: " + this.player.getSocial());
            wellbeing = new JLabel("Gezondheid: " + this.player.getWellbeing());
            money = new JLabel("Geld: €" + this.player.getMoney());
        }
        this.add(intelligence);
        this.add(social);
        this.add(wellbeing);
        this.add(money);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PlayerStatsPane pane = new PlayerStatsPane(new AIStudent(), new String("English"));
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setVisible(true);
    }
}
