package nl.rug.ai.oop.rpg.view.location;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RoomPopup extends JDialog {
    private JLabel messageLabel;
    private JButton closeButton;

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
        setLocationRelativeTo(parent);
        setResizable(false);
    }

    public void showDialog() {
        setVisible(true);
    }
}
