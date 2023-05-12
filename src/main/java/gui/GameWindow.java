package gui;

import java.awt.BorderLayout;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.util.ResourceBundle;

public class GameWindow extends JInternalFrame {
    public GameWindow(ResourceBundle bundle, int width, int height) {
        super(bundle.getString("gameWindowHeader"), true, true, true, true);
        GameVisualizer m_visualizer = new GameVisualizer();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(m_visualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setSize(width, height);
    }
}
