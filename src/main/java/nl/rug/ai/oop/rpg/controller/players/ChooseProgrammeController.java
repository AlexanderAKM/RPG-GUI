package nl.rug.ai.oop.rpg.controller.players;

import nl.rug.ai.oop.rpg.model.players.Player;

public class ChooseProgrammeController {
    public ChooseProgrammeController() {
    }

    public void chosenProgramme(String programme){
        Player.getInstance().chooseProgramme(programme);
    }

    public void customProgramme(String programme, int intelligence, int social){
        Player.getInstance().chooseProgramme(programme);
    }
}
