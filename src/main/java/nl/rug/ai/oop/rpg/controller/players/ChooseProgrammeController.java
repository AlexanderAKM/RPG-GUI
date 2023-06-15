package nl.rug.ai.oop.rpg.controller.players;

import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.players.ChooseProgramme;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseProgrammeController implements ActionListener{

    ChooseProgramme panel;

    public ChooseProgrammeController(ChooseProgramme chooseProgramme) {
        this.panel = chooseProgramme;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (actionCommand.equals("Computing Science") || actionCommand.equals("Artificial Intelligence") || actionCommand.equals("Applied Science")){
            Player.getInstance().chooseProgramme(actionCommand);
        } else if (actionCommand.equals("Custom")){
            panel.createCustomProgramme();
        }
    }
}
