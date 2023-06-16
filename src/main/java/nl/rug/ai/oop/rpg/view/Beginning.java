package nl.rug.ai.oop.rpg.view;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;
import nl.rug.ai.oop.rpg.model.Game;
import nl.rug.ai.oop.rpg.model.location.languageManager;
import nl.rug.ai.oop.rpg.model.players.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JComboBox;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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
    private Runnable callback;  // Added this field
    private languageManager languageManager;
    private String selectedLanguage;
    private JButton customButton;
    private JLabel customLabel;
    private int customIntelligence = 70;
    private int customSocial = 60;
    private String customProgrammeName;
    private ChooseProgrammeController listener;
    private Game game;


    /**
     * Constructor for the Beginning class.
     * It initializes the frame and buttons.
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
        frame.remove(loadOldGameButton);
        frame.remove(startNewGameButton);
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
        languageManager = new languageManager();
        languageManager.loadLanguage("english", "roomTranslations.roomTranslations"); //sets default language
        // Add an ActionListener to the languageComboBox
        languageComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedLanguage = (String)languageComboBox.getSelectedItem();
                languageManager.loadLanguage((String)languageComboBox.getSelectedItem(), "roomTranslations.roomTranslations");
            }
        });

        startGameButton = new JButton("Choose Programme");
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize the player with the chosen language and student type
                String language = (String) languageComboBox.getSelectedItem();
                Player player = Player.getInstance();
                player.setLanguage(language);
                ChooseProgramme();
                //frame.dispose();
                // Code here to start the game with the new frame -> view.Main
                //SetUp setUp = new SetUp();
                //setUp.start(languageManager, game);
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

    /**
     * @author Robert Hielkema
     * This method gives different options to choose a programme to follow in university
     */
    public void ChooseProgramme() {
        frame.remove(languageComboBox);
        frame.remove(startGameButton);
        frame.setSize(800, 400);
        frame.setLayout(new GridLayout(0,1));
        listener = new ChooseProgrammeController(this);
        JLabel explanation = new JLabel("<html>Choose the programme you want to follow<br/>(Choose wisely the programme you choose will influence the game)<html>", SwingConstants.CENTER);
        frame.add(explanation);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,4, 5, 0));

        JButton AIButton = new JButton("Artificial Intelligence");
        AIButton.setActionCommand("Artificial Intelligence");
        AIButton.addActionListener(listener);
        buttons.add(AIButton);

        JButton APButton = new JButton("Applied Physics");
        APButton.setActionCommand("Applied Physics");
        APButton.addActionListener(listener);
        buttons.add(APButton);

        JButton CSButton = new JButton("Computing Science");
        CSButton.setActionCommand("Computing Science");
        CSButton.addActionListener(listener);
        buttons.add(CSButton);

        customButton = new JButton("Custom");
        customButton.setActionCommand("Custom");
        customButton.addActionListener(listener);
        buttons.add(customButton);

        JLabel AILabel = new JLabel("<html>Intelligence: 70<br/>Social: 60<html>", SwingConstants.CENTER);
        buttons.add(AILabel);

        JLabel APLabel = new JLabel("<html>Intelligence: 85<br/>Social: 40<html>", SwingConstants.CENTER);
        buttons.add(APLabel);

        JLabel CSLabel = new JLabel("<html>Intelligence: 100<br/>Social: 20<html>", SwingConstants.CENTER);
        buttons.add(CSLabel);

        frame.add(buttons);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @author Robert Hielkema
     * This method shows a textfield and a slider to choose the name and stats of a custom programme.
     */
    public void createCustomProgramme(){
        frame.getContentPane().removeAll();
        frame.repaint();
        frame.setLayout(new GridLayout(0,1));

        JLabel choiceOne = new JLabel("<html>Choose the name for your custom programme<html>", SwingConstants.CENTER);
        frame.add(choiceOne);

        TextField textField = new TextField();
        Dimension dimension = new Dimension(50, 30);
        textField.setMaximumSize(dimension);
        frame.add(textField);

        JLabel choiceTwo = new JLabel("<html>Choose the stats for your custom programme<html>", SwingConstants.CENTER);
        frame.add(choiceTwo);

        JSlider customSlider = new JSlider(JSlider.HORIZONTAL, 10, 120, customIntelligence);
        customSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                customIntelligence = ((JSlider)e.getSource()).getValue();
                customSocial = (int)(155.6662-(float)(customIntelligence*1.36));
                customLabel.setText("<html>Intelligence: <html>" + customIntelligence + "<html><br/>Social: <html>" + customSocial);
            }
        });
        customSlider.setPaintLabels(true);
        customSlider.setPaintTrack(true);
        customSlider.setPaintTicks(true);
        customSlider.setMajorTickSpacing(30);
        customSlider.setMinorTickSpacing(10);
        frame.add(customSlider);

        customLabel = new JLabel("<html>Intelligence: <html>" + customIntelligence + "<html><br/>Social: <html>" + customSocial, SwingConstants.CENTER);
        frame.add(customLabel);

        customButton = new JButton("Create programme");
        customButton.setActionCommand("create custom");
        customButton.addActionListener(listener);
        frame.add(customButton);
        frame.revalidate();
        frame.repaint();
    }

    /**
     * @author Robert Hielkema
     *
     */
    public int getCustomIntelligence() {
        return customIntelligence;
    }

    public int getCustomSocial() {
        return customSocial;
    }

    public String getCustomProgrammeName() {
        return customProgrammeName;
    }

    public void startSetup(){
        frame.dispose();
        SetUp setUp = new SetUp();
        setUp.start(languageManager, game);
    }
}

