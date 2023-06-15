package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChooseProgramme extends JPanel {

    private JLabel Explanation;
    private JLabel choiceOne;
    private JLabel choiceTwo;
    private static JButton AIButton;
    private static JButton APButton;
    private static JButton CSButton;
    private JButton customButton;
    private JLabel AILabel;
    private JLabel APLabel;
    private JLabel CSLabel;
    private JLabel customLabel;
    private JSlider customSlider;
    private TextField textField;
    private int customIntelligence = 70;
    private int customSocial = 60;
    private String customProgrammeName;
    private ChooseProgrammeController listener;
    GridBagConstraints c;

    public ChooseProgramme() {
        this.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        Explanation = new JLabel("<html>Choose the programme you want to follow<br/>(Choose wisely the programme you choose will influence the game)<html>", SwingConstants.CENTER);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipady = 100;
        c.anchor = GridBagConstraints.PAGE_START;
        c.weightx = 0.5;
        c.gridwidth = 4;
        c.gridheight = 1;
        c.gridx = 0;
        c.gridy = 0;
        this.add(Explanation, c);

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2,4, 5, 0));

        AIButton = new JButton("Artificial Intelligence");
        AIButton.setActionCommand("Artificial Intelligence");
        AIButton.addActionListener(new ChooseProgrammeController(this));
        buttons.add(AIButton);

        APButton = new JButton("Applied Physics");
        APButton.setActionCommand("Applied Physics");
        APButton.addActionListener(new ChooseProgrammeController(this));
        buttons.add(APButton);

        CSButton = new JButton("Computing Science");
        CSButton.setActionCommand("Computing Science");
        CSButton.addActionListener(new ChooseProgrammeController(this));
        buttons.add(CSButton);

        customButton = new JButton("Custom");
        customButton.setActionCommand("Custom");
        customButton.addActionListener(new ChooseProgrammeController(this));
        buttons.add(customButton);

        AILabel = new JLabel("<html>Intelligence: 70<br/>Social: 60<html>", SwingConstants.CENTER);
        buttons.add(AILabel);

        APLabel = new JLabel("<html>Intelligence: 85<br/>Social: 40<html>", SwingConstants.CENTER);
        buttons.add(APLabel);

        CSLabel = new JLabel("<html>Intelligence: 100<br/>Social: 20<html>", SwingConstants.CENTER);
        buttons.add(CSLabel);

        c.gridy = 1;
        this.add(buttons, c);
    }

    public void createCustomProgramme(){
        Component[] components = this.getComponents();
        for (Component component : components){
            remove(component);
        }
        this.revalidate();
        this.repaint();
        this.setLayout(new GridBagLayout());

        choiceOne = new JLabel("<html>Choose the name for your custom programme<html>", SwingConstants.CENTER);
        c.weightx = 0.5;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridx = 1;
        c.gridy = 1;
        this.add(choiceOne, c);

        textField = new TextField();
        Dimension dimension = new Dimension(200, 30);
        textField.setMaximumSize(dimension);
        c.gridy = 2;
        this.add(textField, c);

        choiceTwo = new JLabel("<html>Choose the stats for your custom programme<html>", SwingConstants.CENTER);
        c.gridy = 3;
        this.add(choiceTwo, c);

        customLabel = new JLabel("<html>Intelligence: <html>" + customIntelligence + "<html><br/>Social: <html>" + customSocial, SwingConstants.CENTER);
        c.gridy = 5;
        this.add(customLabel, c);

        customSlider = new JSlider(JSlider.HORIZONTAL, 10, 120, customIntelligence);
        customSlider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                customIntelligence = ((JSlider)e.getSource()).getValue();
                customSocial = 150-customIntelligence;
                customLabel.setText("<html>Intelligence: <html>" + customIntelligence + "<html><br/>Social: <html>" + customSocial);
            }
        });
        customSlider.setPaintLabels(true);
        customSlider.setPaintTrack(true);
        customSlider.setPaintTicks(true);
        customSlider.setMajorTickSpacing(30);
        customSlider.setMinorTickSpacing(10);
        c.gridy = 4;
        c.gridx = 1;
        this.add(customSlider, c);



    }

    public int getCustomIntelligence() {
        return customIntelligence;
    }

    public int getCustomSocial() {
        return customSocial;
    }

    public String getCustomProgrammeName() {
        return customProgrammeName;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ChooseProgramme pane = new ChooseProgramme();
        frame.setSize(1000,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(pane);
        frame.setVisible(true);
    }
}
