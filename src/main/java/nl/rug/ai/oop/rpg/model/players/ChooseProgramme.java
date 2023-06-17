package nl.rug.ai.oop.rpg.model.players;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.controller.players.CreateCustomProgrammeController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.view.SetUp;
import nl.rug.ai.oop.rpg.view.players.ChooseProgrammeView;
import nl.rug.ai.oop.rpg.view.players.CreateCustomProgrammeView;

import javax.swing.*;
import java.awt.*;

public class ChooseProgramme {

    private String selectedLanguage;
    private JLabel customLabel;
    private int customIntelligence = 70;
    private int customSocial = 60;
    TextField customProgrammetextField;
    private ChooseProgrammeController listener;
    private Game game;
    LanguageManager chooseProgrammeLanguageManager;

    public ChooseProgramme(JFrame frame) {
        //ChooseProgrammeController chooseProgrammeController = new ChooseProgrammeController(this);
        //ChooseProgrammeView chooseProgrammeView = new ChooseProgrammeView(chooseProgrammeController, frame);
        //CreateCustomProgrammeController createCustomProgrammeController = new CreateCustomProgrammeController(this);
        //CreateCustomProgrammeView createCustomProgrammeView = new CreateCustomProgrammeView(createCustomProgrammeController, frame);
        //frame.setSize(800,400);
        //frame.add(chooseProgrammeView);
    }

    /**
     * @return The intelligence value of the custom programme.
     */
    public int getCustomIntelligence() {
        return customIntelligence;
    }

    /**
     * @return The social value of the custom programme.
     */
    public int getCustomSocial() {
        return customSocial;
    }

    /**
     * @return The name of the custom programme.
     */
    public String getCustomProgrammeName() {
        return customProgrammetextField.getText();
    }

    /**
     * Starts the setup process.
     */
    public void startSetup() {
        SetUp setUp = new SetUp();
        setUp.start(selectedLanguage, game);
    }
}
