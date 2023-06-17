package nl.rug.ai.oop.rpg.controller.players;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The ChooseProgrammeController class is a controller that handles user actions for choosing a program.
 * It implements the ActionListener interface.
 *
 * @author RobertHielkema
 */
public class ChooseProgrammeController implements ActionListener {

    Beginning beginning;

    /**
     * Constructs a ChooseProgrammeController object.
     *
     * @param beginning the Beginning object
     */
    public ChooseProgrammeController(Beginning beginning) {
        this.beginning = beginning;
    }

    /**
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Computing Science") || actionCommand.equals("Artificial Intelligence") || actionCommand.equals("Applied Physics")) {
            Player.getInstance().chooseProgramme(actionCommand);
            beginning.startSetup();
        } else if (actionCommand.equals("Custom")) {
            beginning.createCustomProgramme();
        } else if (actionCommand.equals("create custom")) {
            String programme = beginning.getCustomProgrammeName();
            int intelligence = beginning.getCustomIntelligence();
            int social = beginning.getCustomSocial();
            Player.getInstance().customProgramme(programme, intelligence, social);
            beginning.startSetup();
        }
    }
}
