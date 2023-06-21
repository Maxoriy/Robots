package gui;

import java.awt.BorderLayout;
import java.util.ResourceBundle;

import javax.swing.*;

public class GameWindow extends JInternalFrame implements Translatable{
    public GameWindow(GameVisualizer gameVisualizer, int width, int height) {
        super("Игровое поле", true, true, true, true);
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(gameVisualizer, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setSize(width, height);
    }

    @Override
    public void translate(ResourceBundle bundle) {
        setTitle(bundle.getString("gameWindowHeader"));
    }
}
