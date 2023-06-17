package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.controller.players.CreateCustomProgrammeController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.LanguageManager;
import nl.rug.ai.oop.rpg.model.players.Player;
import nl.rug.ai.oop.rpg.view.players.ChooseProgrammeView;
import nl.rug.ai.oop.rpg.view.players.CreateCustomProgrammeView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
    private Runnable callback;
    private String selectedLanguage;
    private JLabel customLabel;
    private int customIntelligence = 70;
    private int customSocial = 60;
    TextField customProgrammetextField;
    private ChooseProgrammeController listener;
    private Game game;


    /**
     * Constructor for the Beginning class.
     * It initializes the frame and buttons.
     * @param game the game to start
     */
    public Beginning(Game game) {
        this.game = game;
        this.callback = callback;
        frame = new JFrame("RPG Game");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2,1));

        loadOldGameButton = new JButton("Load Old Game");
        loadOldGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                removeInitialButtons();
            }
        });
        frame.add(loadOldGameButton);

        startNewGameButton = new JButton("Start New Game");
        startNewGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeInitialButtons();
                showNewGameOptions(game);
            }
        });
        frame.add(startNewGameButton);
    }

    /**
     * This method removes the initial buttons
     * from the frame.
     */
    private void removeInitialButtons() {
        frame.getContentPane().removeAll();
        frame.setLayout(new GridLayout(2,1)); // Updated to accommodate an extra button
    }

    /**
     * This method shows new game options.
     * It adds a combo box for languages
     * and a button to start the game.
     * @author Victoria Polaka (only included room language)
     */
    private void showNewGameOptions(Game game) {
        String[] languages = {"english", "nederlands"};
        languageComboBox = new JComboBox<>(languages);
        frame.add(languageComboBox);
        selectedLanguage = "english";
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLanguage = (String)languageComboBox.getSelectedItem();
            }
        });

        startGameButton = new JButton("Choose Programme");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String language = (String) languageComboBox.getSelectedItem();
                Player player = Player.getInstance();
                player.setLanguage(language);
                game.chooseProgramme();
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

    /**
     * Presents the player with the option to choose a programme.
     *
     * @param controller the controller for choosing a programme
     */
    public void chooseProgramme(ChooseProgrammeController controller){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setSize(800,400);
        ChooseProgrammeView chooseProgrammeView = new ChooseProgrammeView(controller);
        frame.add(chooseProgrammeView);
    }

    /**
     * Presents the player with the option to create a custom programme.
     *
     * @param controller the controller for creating a custom programme
     */
    public void createCustomProgramme(CreateCustomProgrammeController controller){
        frame.getContentPane().removeAll();
        frame.repaint();
        CreateCustomProgrammeView createCustomProgrammeView = new CreateCustomProgrammeView(controller);
        frame.add(createCustomProgrammeView);
    }

    /**
     * Disposes of the current frame.
     */
    public void disposeFrame(){
        frame.dispose();
    }
}