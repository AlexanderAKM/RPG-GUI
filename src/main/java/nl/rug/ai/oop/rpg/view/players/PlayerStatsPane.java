package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The PlayerStatsPane class represents a panel that displays the player's statistics.
 * It extends JPanel and implements PropertyChangeListener.
 *
 * @author RobertHielkema
 */
public class PlayerStatsPane extends JPanel implements PropertyChangeListener {

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

    /**
     * Constructs a new PlayerStatsPane object.
     * Initializes the player, adds itself as a change listener to the player,
     * sets the layout, creates and initializes the stat labels and icons,
     * and adds them to the panel.
     */
    public PlayerStatsPane() {
        this.player = Player.getInstance();
        player.addChangeListener(this);
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

    /**
     * Resizes the given ImageIcon to the specified dimensions.
     *
     * @param imageIcon the ImageIcon to resize
     * @return the resized ImageIcon
     */
    private ImageIcon resizeImage(ImageIcon imageIcon){
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(STATWIDTH, STATHEIGHT,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaledImage);
    }

    /**
     * Invalidates the panel.
     * Updates the stat labels with the player's current statistics and repaints the panel.
     */
    @Override
    public void invalidate() {
        super.invalidate();
        intelligence.setText(String.valueOf(this.player.getIntelligence()));
        social.setText(String.valueOf(this.player.getSocial()));
        wellbeing.setText(String.valueOf(this.player.getWellbeing()));
        money.setText(String.valueOf(this.player.getMoney()));
        this.repaint();
    }

    /**
     * Listens for property change events.
     * If the property name is "playerstat", revalidates the panel.
     *
     * @param evt the PropertyChangeEvent object
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("playerstat")) {
            this.revalidate();
        }
    }
}
