package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JPanel;

import static tools.DrawTools.*;
import static tools.MathTools.*;


public class GameVisualizer extends JPanel {

    private static Timer initTimer() {
        return new Timer("events generator", true);
    }

    private volatile int m_targetPositionX = 150;
    private volatile int m_targetPositionY = 100;

    public static final double maxVelocity = 0.1;
    public static final double maxAngularVelocity = 0.001;
    private final RobotController robotController;

    public GameVisualizer(RobotController robotController) {
        this.robotController = robotController;
        Timer m_timer = initTimer();
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onRedrawEvent();
            }
        }, 0, 50);
        m_timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 10);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setTargetPosition(e.getPoint());
                repaint();
            }
        });
        setDoubleBuffered(true);
    }

    protected void setTargetPosition(Point p) {
        m_targetPositionX = p.x;
        m_targetPositionY = p.y;
    }

    protected void onRedrawEvent() {
        EventQueue.invokeLater(this::repaint);
    }


    protected void onModelUpdateEvent() {
        double m_robotPositionX = robotController.getPositionX();
        double m_robotPositionY = robotController.getPositionY();
        double m_robotDirection = robotController.getDirection();

        double distance = distance(m_targetPositionX, m_targetPositionY, m_robotPositionX, m_robotPositionY);
        if (distance < 0.5) return;

        double angleToTarget = angleTo(m_robotPositionX, m_robotPositionY, m_targetPositionX, m_targetPositionY);
        double angularVelocity = 0;

        if (angleToTarget > m_robotDirection + 0.01) angularVelocity = maxAngularVelocity;

        if (angleToTarget < m_robotDirection - 0.01) angularVelocity = -maxAngularVelocity;

        robotController.move(maxVelocity, angularVelocity, 10);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        robotController.draw(g2d);
        drawTarget(g2d, m_targetPositionX, m_targetPositionY);
    }


    private void drawTarget(Graphics2D g, int x, int y) {
        AffineTransform t = AffineTransform.getRotateInstance(0, 0, 0);
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }
}
