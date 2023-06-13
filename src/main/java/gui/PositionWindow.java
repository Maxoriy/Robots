package gui;

import models.RobotModel;

import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import java.awt.*;

public class PositionWindow extends JInternalFrame implements Observer {
    private final JLabel labelX;
    private final JLabel labelY;
    private final RobotModel m_RobotModel;

    public PositionWindow(RobotModel robotModel, int width, int height) {
        super("Координаты робота", true, true, true, true);
        m_RobotModel = robotModel;
        JPanel panel = new JPanel(new BorderLayout());
        m_RobotModel.addObserver(this);
        labelX = new JLabel("X: %f".formatted(m_RobotModel.getM_robotPositionX()));
        labelY = new JLabel("Y: %f".formatted(m_RobotModel.getM_robotPositionY()));
        panel.add(labelX, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(labelY, BorderLayout.CENTER);
        getContentPane().add(panel);
        pack();
        setSize(width, height);
        setLocation(1600, 50);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof RobotModel) EventQueue.invokeLater(this::updateCoords);
    }

    void updateCoords() {
        labelX.setText("X: %f".formatted(m_RobotModel.getM_robotPositionX()));
        labelY.setText("Y: %f".formatted(m_RobotModel.getM_robotPositionY()));
    }
}
