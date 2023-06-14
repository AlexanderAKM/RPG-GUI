package nl.rug.ai.oop.rpg.view.players;

import nl.rug.ai.oop.rpg.controller.players.ChooseProgrammeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseProgramme extends JPanel {

    private JLabel Explanation;
    private static JButton AIButton;
    private static JButton APButton;
    private static JButton CSButton;
    private JButton CustomButton;
    private JLabel AILabel;
    private JLabel APLabel;
    private JLabel CSLabel;
    private JLabel customLabel;
    private JSlider customSlider;
    private int customIntelligence = 70;
    private int customSocial = 60;
    private String customProgrammeName;
    private ChooseProgrammeController listener;

    public ChooseProgramme() {
        listener = new ChooseProgrammeController();
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
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

        AIButton = new JButton("Artificial Intelligence");
        AIButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.chosenProgramme("Artificial Intelligence");
            }
        });
        c.gridwidth = 1;
        c.gridy = 1;
        this.add(AIButton, c);

        APButton = new JButton("Applied Physics");
        APButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.chosenProgramme("Applied Physics");
            }
        });
        c.gridx = 1;
        this.add(APButton, c);

        CSButton = new JButton("Computing Science");
        CSButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.chosenProgramme("Computing Science");
            }
        });
        c.gridx = 2;
        this.add(CSButton, c);

        CustomButton = new JButton("Custom");
        CustomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createCustomProgramme();
            }
        });
        c.gridx = 3;
        this.add(CustomButton, c);

        AILabel = new JLabel("<html>Intelligence: 70<br/>Social: 60<html>", SwingConstants.CENTER);
        c.gridy = 2;
        c.gridx = 0;
        this.add(AILabel, c);

        APLabel = new JLabel("<html>Intelligence: 85<br/>Social: 40<html>", SwingConstants.CENTER);
        c.gridx = 1;
        this.add(APLabel, c);

        CSLabel = new JLabel("<html>Intelligence: 100<br/>Social: 20<html>", SwingConstants.CENTER);
        c.gridx = 2;
        this.add(CSLabel, c);

        customLabel = new JLabel("<html>Choose the stats for your custom programme<html>");
        c.gridx = 3;
        this.add(customLabel, c);

        customSlider = new JSlider(JSlider.HORIZONTAL, 10, 120, customIntelligence);
        this.add(customSlider, c);
    }

    public void createCustomProgramme(){
        Component[] components = this.getComponents();
        for (Component component : components){
            remove(component);
        }
        this.revalidate();


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
