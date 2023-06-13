package gui;

import models.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import static tools.DrawTools.*;
import static tools.MathTools.*;


public class GameVisualizer extends JPanel implements Observer {
    private final RobotModel robotModel;
    private final TargetModel targetModel;

    public GameVisualizer(RobotModel robotModel, TargetModel targetModel) {
        this.robotModel = robotModel;
        this.targetModel = targetModel;
        robotModel.addObserver(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                targetModel.setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }


    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof RobotModel) EventQueue.invokeLater(this::onRedrawEvent);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            drawRobot(g2d, robotModel);
            drawTarget(g2d, targetModel);
        } finally {
            g2d.dispose();
        }
    }

    private void drawTarget(Graphics2D g, TargetModel targetModel) {
        int targetX = targetModel.getTargetX();
        int targetY = targetModel.getTargetY();
        AffineTransform t = new AffineTransform();
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, targetX, targetY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, targetX, targetY, 5, 5);
    }

    private void drawRobot(Graphics2D g, RobotModel robotModel) {
        int robotCenterX = round(robotModel.getM_robotPositionX());
        int robotCenterY = round(robotModel.getM_robotPositionY());
        AffineTransform t = AffineTransform.getRotateInstance(robotModel.getM_robotDirection(), robotCenterX, robotCenterY);
        g.setTransform(t);
        g.setColor(Color.MAGENTA);
        fillOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX, robotCenterY, 30, 10);
        g.setColor(Color.WHITE);
        fillOval(g, robotCenterX + 10, robotCenterY, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, robotCenterX + 10, robotCenterY, 5, 5);
    }
}
