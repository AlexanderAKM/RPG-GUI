package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;
import java.awt.*;

public class PlayerStatsPane extends JPanel{

    Player player;
    JLabel intelligenceIcon;
    JLabel intelligence;
    JLabel socialIcon;
    JLabel social;
    JLabel wellbeingIcon;
    JLabel wellbeing;
    JLabel moneyIcon;
    JLabel money;
    String languageIcon;
    String language;
    public PlayerStatsPane() {
        this.player = Player.getInstance();
        this.setLayout(new GridLayout(2,4, 50, 50));
        intelligenceIcon = new JLabel(resizeImage(new ImageIcon("/Alcohol.png")));
        socialIcon = new JLabel(resizeImage(new ImageIcon("/Alcohol.png")));
        wellbeingIcon = new JLabel(resizeImage(new ImageIcon("/Alcohol.png")));
        moneyIcon = new JLabel(resizeImage(new ImageIcon("/Alcohol.png")));
        intelligence = new JLabel(String.valueOf(this.player.getIntelligence()));
        social = new JLabel(String.valueOf(this.player.getSocial()));
        wellbeing = new JLabel(String.valueOf(this.player.getWellbeing()));
        money = new JLabel(String.valueOf(this.player.getMoney()));
        this.add(intelligenceIcon);
        this.add(socialIcon);
        this.add(wellbeingIcon);
        this.add(moneyIcon);
        this.add(intelligence);
        this.add(social);
        this.add(wellbeing);
        this.add(money);
        this.setVisible(true);
    }

    private ImageIcon resizeImage(ImageIcon imageIcon){
        Image image = imageIcon.getImage(); // transform it
        Image scaledImage = image.getScaledInstance(200, 200,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(scaledImage);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PlayerStatsPane pane = new PlayerStatsPane();
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setVisible(true);
    }
}
