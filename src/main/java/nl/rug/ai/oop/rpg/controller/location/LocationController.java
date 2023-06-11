package nl.rug.ai.oop.rpg.controller.location;
import nl.rug.ai.oop.rpg.model.location.LocationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LocationController implements ActionListener {
    private LocationManager model;
    public LocationController(LocationManager model) {
        this.model = model;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        switch (e.getActionCommand()) {
            case "Home":
                model.movePlayer(0);
                break;
            case "Outside":
                System.out.println("controller for outside");
                model.movePlayer(1);
                break;
            case "Aletta Jacobs Hall":
                model.movePlayer(2);
                break;
            case "Bernoulliborg":
                model.movePlayer(3);
                break;
            case "The BB canteen":
                model.movePlayer(4);
                break;
            case "Cover room":
                model.movePlayer(5);
                break;
        }
    }
}


