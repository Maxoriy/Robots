package gui;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;

public class PositionWindow extends JInternalFrame implements Observer {
    private final JLabel labelX;
    private final JLabel labelY;
    private final ModelRobot m_modelRobot;

    public PositionWindow(ModelRobot modelRobot, int width, int height) {
        super("Координаты робота", true, true, true, true);
        m_modelRobot = modelRobot;
        JPanel panel = new JPanel(new BorderLayout());
        m_modelRobot.addObserver(this);
        labelX = new JLabel("X: %f".formatted(m_modelRobot.getM_robotPositionX()));
        labelY = new JLabel("Y: %f".formatted(m_modelRobot.getM_robotPositionY()));
        panel.add(labelX, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(labelY, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setSize(width, height);
        setLocation(1600, 50);
    }

    @Override
    public void update(Observable o, Object arg) {
        EventQueue.invokeLater(this::updateCoords);
    }

    void updateCoords() {
        labelX.setText("X: %f".formatted(m_modelRobot.getM_robotPositionX()));
        labelY.setText("Y: %f".formatted(m_modelRobot.getM_robotPositionY()));
    }
}
