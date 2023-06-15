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
        super(parent, "Room Not Accessible", true);

        // Set the layout
        setLayout(new FlowLayout());

        // Create and add the message label
        messageLabel = new JLabel(message);
        add(messageLabel);

        // Create and add the close button
        closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        add(closeButton);

        // Set the size and position of the dialog
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
