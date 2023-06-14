package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.model.location.Room;
import nl.rug.ai.oop.rpg.model.location.RoomLanguageManager;
import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JComboBox;


/**
 * This is the class for the beginning of the RPG Game.
 * It includes a graphical user interface to either load an old game
 * or start a new one with different options.
 *
 * @author Alexander Müller
 * @author Robert Hielkema
 *
 */
public class Beginning {

    private JFrame frame;
    private JButton loadOldGameButton;
    private JButton startNewGameButton;
    private JButton startGameButton;
    private JComboBox<String> languageComboBox;
    private JComboBox<String> studentTypeComboBox;
    private Runnable callback;  // Added this field
    private RoomLanguageManager roomLanguageManager;
    private String selectedLanguage;



    /**
     * Constructor for the Beginning class.
     * It initializes the frame and buttons.
     */
    public Beginning() {
        this.callback = callback;
        //this.roomLanguageManager = roomLanguageManager;
        frame = new JFrame("RPG Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));

        loadOldGameButton = new JButton("Load Old Game");
        loadOldGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code here to load the old game
                removeInitialButtons();
            }
        });
        frame.add(loadOldGameButton);

        startNewGameButton = new JButton("Start New Game");
        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Add your code here to start a new game
                removeInitialButtons();
                showNewGameOptions();
            }
        });
        frame.add(startNewGameButton);
    }

    /**
     * This method removes the initial buttons
     * from the frame.
     */
    private void removeInitialButtons() {
        frame.remove(loadOldGameButton);
        frame.remove(startNewGameButton);
        frame.setLayout(new GridLayout(3,1)); // Updated to accommodate an extra button
    }

    /**
     * This method shows new game options.
     * It adds a combo box for languages and student types
     * and a button to start the game.
     */
    private void showNewGameOptions() {
        String[] languages = {"english", "nederlands"};
        languageComboBox = new JComboBox<>(languages);
        frame.add(languageComboBox);
        roomLanguageManager = new RoomLanguageManager();
        // Add an ActionListener to the languageComboBox
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLanguage = (String)languageComboBox.getSelectedItem();
                roomLanguageManager.loadLanguage((String)languageComboBox.getSelectedItem());

            }
        });


        String[] studentTypes = {"Artificial Intelligence", "Applied Physics", "Computing Science"};
        studentTypeComboBox = new JComboBox<>(studentTypes);
        frame.add(studentTypeComboBox);

        startGameButton = new JButton("Start Game");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize the player with the chosen language and student type
                String language = (String) languageComboBox.getSelectedItem();
                String studentType = (String) studentTypeComboBox.getSelectedItem();
                Player player = Player.getInstance();
                player.setLanguage(language);
                player.chooseProgramme(studentType);
                frame.dispose();
                // Code here to start the game with the new frame -> view.Main
                Setup setup = new Setup();
                setup.start(roomLanguageManager);
                //callback.run();
            }
        });
        frame.add(startGameButton);

        frame.validate();
        frame.repaint();
    }

    /**
     * Makes the frame visible.
     */
    public void show() {
        frame.setVisible(true);
    }
}

