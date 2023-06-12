package gui;


import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class RobotController {
    private final RobotModel robotModel;

    public RobotController(RobotModel robotModel) {
        this.robotModel = robotModel;
        Timer timer = new Timer("events generator", true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                onModelUpdateEvent();
            }
        }, 0, 10);
    }

    public int getTargetX() {
        return robotModel.getM_targetPositionX();
    }

    public int getTargetY() {
        return robotModel.getM_targetPositionY();
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

    public void setTargetPosition(Point p) {
        robotModel.setTargetPosition(p);
    }

    private void onModelUpdateEvent() {
        robotModel.updateRobotPosition();
    }

}
