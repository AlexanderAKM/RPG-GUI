package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.SetUp;

import javax.swing.*;
import java.awt.*;
/**
 * @author Robert Hielkema
 */
public class ChooseProgrammeView extends JPanel{
    private LanguageManager chooseProgrammeLanguageManager;

    /**
     * Displays different programmes options to choose from in university.
     *
     * @param listener the controller for this view
     */
    public ChooseProgrammeView(ChooseProgrammeController listener) {
        chooseProgrammeLanguageManager = new LanguageManager();
        chooseProgrammeLanguageManager.loadLanguage(Player.getInstance().getLanguage(), "chooseProgrammeTranslations.chooseProgrammeTranslations");
        this.setLayout(new GridLayout(0,1,0,40));
        JLabel explanation = new JLabel(chooseProgrammeLanguageManager.getTranslation("explanation"), SwingConstants.CENTER);
        this.add(explanation);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,4, 5, 20));

        JButton AIButton = new JButton(chooseProgrammeLanguageManager.getTranslation("AIButton"));
        AIButton.setActionCommand("Artificial Intelligence");
        AIButton.addActionListener(listener);
        buttons.add(AIButton);

        JButton APButton = new JButton(chooseProgrammeLanguageManager.getTranslation("APButton"));
        APButton.setActionCommand("Applied Physics");
        APButton.addActionListener(listener);
        buttons.add(APButton);

        JButton CSButton = new JButton(chooseProgrammeLanguageManager.getTranslation("CSButton"));
        CSButton.setActionCommand("Computing Science");
        CSButton.addActionListener(listener);
        buttons.add(CSButton);

        JButton customButton = new JButton(chooseProgrammeLanguageManager.getTranslation("customButton"));
        customButton.setActionCommand("Custom");
        customButton.addActionListener(listener);
        buttons.add(customButton);

        JLabel AILabel = new JLabel(chooseProgrammeLanguageManager.getTranslation("AILabel"), SwingConstants.CENTER);
        buttons.add(AILabel);

        JLabel APLabel = new JLabel(chooseProgrammeLanguageManager.getTranslation("APLabel"), SwingConstants.CENTER);
        buttons.add(APLabel);

        JLabel CSLabel = new JLabel(chooseProgrammeLanguageManager.getTranslation("CSLabel"), SwingConstants.CENTER);
        buttons.add(CSLabel);

        this.add(buttons);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }
}
