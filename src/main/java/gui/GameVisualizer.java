package gui;

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

    private final GameController gameController;

    public GameVisualizer(GameController gameController, RobotModel robotModel) {
        this.gameController = gameController;
        robotModel.addObserver(this);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gameController.setTargetPosition(e.getPoint());
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
        EventQueue.invokeLater(this::onRedrawEvent);
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g.create();
        try {
            drawRobot(g2d, gameController);
            drawTarget(g2d, gameController.getTargetX(), gameController.getTargetY());
        } finally {
            g2d.dispose();
        }
    }

    private void drawTarget(Graphics2D g, int x, int y) {
        AffineTransform t = new AffineTransform();
        g.setTransform(t);
        g.setColor(Color.GREEN);
        fillOval(g, x, y, 5, 5);
        g.setColor(Color.BLACK);
        drawOval(g, x, y, 5, 5);
    }

    private void drawRobot(Graphics2D g, GameController gameController) {
        int robotCenterX = round(gameController.getRobotX());
        int robotCenterY = round(gameController.getRobotY());
        AffineTransform t = AffineTransform.getRotateInstance(gameController.getRobotDirection(), robotCenterX, robotCenterY);
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
