package gui;

import java.awt.*;

public class RobotController {
    private final RobotModel robotModel;

    public RobotController(RobotModel robotModel) {
        this.robotModel = robotModel;
    }

    public double getPositionX() {
        return robotModel.getM_robotPositionX();
    }

    public double getPositionY() {
        return robotModel.getM_robotPositionY();
    }

    public double getDirection() {
        return robotModel.getM_robotDirection();
    }

    public void move(double velocity, double angularVelocity, double duration) {
        robotModel.moveRobot(velocity, angularVelocity, duration);
    }
}
