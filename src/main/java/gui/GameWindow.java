package gui;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;

public class GameWindow extends JInternalFrame {
    public GameWindow(ResourceBundle bundle, GameVisualizer gameVisualizer, int width, int height) {
        super(bundle.getString("gameWindowHeader"), true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gameVisualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setSize(width, height);
    }
}
