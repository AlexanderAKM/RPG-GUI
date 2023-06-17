package nl.rug.ai.oop.rpg.view.location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Represents a popup dialog for displaying a message when a room is not accessible.
 *
 * @author Victoria Polaka
 */
public class RoomPopup extends JDialog {
    private JLabel messageLabel;
    private JButton closeButton;

    /**
     * Constructs a new RoomPopup dialog.
     *
     * @param parent  the parent JFrame
     * @param message the message to be displayed in the dialog
     */
    public RoomPopup(JFrame parent, String message) {
        super(parent, "", true);
        setLayout(new FlowLayout());

        messageLabel = new JLabel(message);
        add(messageLabel);
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(closeButton);
        pack();
        setLocationRelativeTo(parent); // pops up in the middle
        setResizable(false);
    }

    /**
     * Displays the RoomPopup dialog.
     */
    public void showDialog() {
        setVisible(true);
    }
}
