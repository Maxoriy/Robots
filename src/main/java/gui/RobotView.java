package gui;

import java.awt.*;
import java.awt.geom.AffineTransform;

import static tools.DrawTools.*;
import static tools.MathTools.round;

public class RobotView {
    public void drawRobot(Graphics2D g, ModelRobot modelRobot) {
        int robotCenterX = round(modelRobot.getM_robotPositionX());
        int robotCenterY = round(modelRobot.getM_robotPositionY());
        AffineTransform t = AffineTransform.getRotateInstance(modelRobot.getM_robotDirection(), robotCenterX, robotCenterY);
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
