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
    final int STATHEIGHT = 80;
    final int STATWIDTH = 80;
    public PlayerStatsPane() {
        this.player = Player.getInstance();
        this.setLayout(new GridLayout(2,4, 5, 0));
        intelligenceIcon = new JLabel(resizeImage(new ImageIcon(PlayerStatsPane.class.getResource("/intelligence_stat.jpg"))));
        socialIcon = new JLabel(resizeImage(new ImageIcon(PlayerStatsPane.class.getResource("/social_stat.jpg"))));
        wellbeingIcon = new JLabel(resizeImage(new ImageIcon(PlayerStatsPane.class.getResource("/wellbeing_stat.png"))));
        moneyIcon = new JLabel(resizeImage(new ImageIcon(PlayerStatsPane.class.getResource("/money_stat.png"))));
        intelligence = new JLabel(String.valueOf(this.player.getIntelligence()), SwingConstants.CENTER);
        social = new JLabel(String.valueOf(this.player.getSocial()), SwingConstants.CENTER);
        wellbeing = new JLabel(String.valueOf(this.player.getWellbeing()), SwingConstants.CENTER);
        money = new JLabel(String.valueOf(this.player.getMoney()), SwingConstants.CENTER);
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
        Image scaledImage = image.getScaledInstance(STATWIDTH, STATHEIGHT,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(scaledImage);
    }

    @Override
    public void invalidate() {
        super.invalidate();
        intelligence.setText(String.valueOf(this.player.getIntelligence()));
        social.setText(String.valueOf(this.player.getSocial()));
        wellbeing.setText(String.valueOf(this.player.getWellbeing()));
        money.setText(String.valueOf(this.player.getMoney()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        PlayerStatsPane pane = new PlayerStatsPane();
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
