package gui;

import java.awt.*;

public class RobotController {
    private final ModelRobot modelRobot;
    private final RobotView robotView;

    public RobotController(ModelRobot modelRobot, RobotView robotView) {
        this.modelRobot = modelRobot;
        this.robotView = robotView;
    }

    public double getPositionX() {
        return modelRobot.getM_robotPositionX();
    }

    public double getPositionY() {
        return modelRobot.getM_robotPositionY();
    }

    public double getDirection() {
        return modelRobot.getM_robotDirection();
    }

    public void move(double velocity, double angularVelocity, double duration) {
        modelRobot.moveRobot(velocity, angularVelocity, duration);
    }

    public void draw(Graphics2D g) {
        robotView.drawRobot(g, modelRobot);
    }
}
