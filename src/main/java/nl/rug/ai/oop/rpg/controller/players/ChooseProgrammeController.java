package nl.rug.ai.oop.rpg.controller.players;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.Beginning;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseProgrammeController implements ActionListener{

    Beginning beginning;

    public ChooseProgrammeController(Beginning beginning) {
        this.beginning = beginning;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Computing Science") || actionCommand.equals("Artificial Intelligence") || actionCommand.equals("Applied Science")){
            Player.getInstance().chooseProgramme(actionCommand);
            beginning.startSetup();
        } else if (actionCommand.equals("Custom")){
            beginning.createCustomProgramme();
        } else if (actionCommand.equals("create custom")){
            String programme = beginning.getCustomProgrammeName();
            int intelligence = beginning.getCustomIntelligence();
            int social = beginning.getCustomSocial();
            Player.getInstance().customProgramme(programme, intelligence, social);
            beginning.startSetup();
        }
    }
}
