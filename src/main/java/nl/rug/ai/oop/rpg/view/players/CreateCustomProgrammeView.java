package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.controller.players.CreateCustomProgrammeController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.SetUp;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
/**
 * @author Robert Hielkema
 */
public class CreateCustomProgrammeView extends JPanel{
    private JLabel customLabel;
    private int customIntelligence = 70;
    private int customSocial = 60;
    private TextField customProgrammetextField;
    private LanguageManager chooseProgrammeLanguageManager;


    /**
     * Displays a text field and a slider to choose the name and stats of a custom programmes.
     *
     * @param listener the controller for this view
     */
    public CreateCustomProgrammeView(CreateCustomProgrammeController listener){
        listener.setCreateCustomProgrammeView(this);
        chooseProgrammeLanguageManager = new LanguageManager();
        chooseProgrammeLanguageManager.loadLanguage(Player.getInstance().getLanguage(), "chooseProgrammeTranslations.chooseProgrammeTranslations");
        this.setSize(800,400);
        this.setLayout(new GridLayout(0,1));

        JLabel choiceOne = new JLabel(chooseProgrammeLanguageManager.getTranslation("choiceOne"), SwingConstants.CENTER);
        this.add(choiceOne);

        customProgrammetextField = new TextField();
        Dimension dimension = new Dimension(50, 30);
        customProgrammetextField.setMaximumSize(dimension);
        this.add(customProgrammetextField);

        JLabel choiceTwo = new JLabel(chooseProgrammeLanguageManager.getTranslation("choiceTwo"), SwingConstants.CENTER);
        this.add(choiceTwo);

        JSlider customSlider = new JSlider(JSlider.HORIZONTAL, 10, 120, customIntelligence);
        customSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                customIntelligence = ((JSlider)e.getSource()).getValue();
                customSocial = (int)(155.6662-(float)(customIntelligence*1.36));
                customLabel.setText(chooseProgrammeLanguageManager.getTranslation("customLabel1") + customIntelligence + chooseProgrammeLanguageManager.getTranslation("customLabel2") + customSocial);
            }
        });
        customSlider.setPaintLabels(true);
        customSlider.setPaintTrack(true);
        customSlider.setPaintTicks(true);
        customSlider.setMajorTickSpacing(30);
        customSlider.setMinorTickSpacing(10);
        this.add(customSlider);

        customLabel = new JLabel(chooseProgrammeLanguageManager.getTranslation("customLabel1") + customIntelligence + chooseProgrammeLanguageManager.getTranslation("customLabel2") + customSocial, SwingConstants.CENTER);
        this.add(customLabel);

        JButton createProgrammeButton = new JButton(chooseProgrammeLanguageManager.getTranslation("createProgrammeButton"));
        createProgrammeButton.setActionCommand("create");
        createProgrammeButton.addActionListener(listener);
        this.add(createProgrammeButton);
        this.revalidate();
        this.repaint();
        this.setVisible(true);
    }

    /**
     * Get the value of the custom intelligence stat
     */
    public int getCustomIntelligence() {
        return customIntelligence;
    }

    /**
     * Get the value of the custom social stat
     */
    public int getCustomSocial() {
        return customSocial;
    }

    /**
     * Get the name of the custom Programme
     */
    public String getCustomProgrammetextField() {
        return customProgrammetextField.getText();
    }
}
